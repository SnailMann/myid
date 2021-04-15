package com.snailmann.myid.client.template.impl;

import com.snailmann.myid.client.proxy.IDProxy;
import com.snailmann.myid.client.template.IDTemplate;
import com.snailmann.myid.client.template.exception.HttpRetryException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author liwenjie
 */
@Slf4j
public class RpcIDTemplate implements IDTemplate {

    private int retryMax = 2;

    private int retryIntervalMil = 100;

    private final IDProxy proxy;

    public RpcIDTemplate(IDProxy idProxy) {
        this.proxy = idProxy;
    }

    public RpcIDTemplate(int retryMax, int retryIntervalMil,
                         IDProxy proxy) {
        this.retryMax = retryMax;
        this.retryIntervalMil = retryIntervalMil;
        this.proxy = proxy;
    }

    @Override
    public Long nextId(String tag) {
        Throwable throwable = null;
        for (int i = 0; i <= retryMax; i++) {
            try {
                if (i >= 1) {
                    log.warn("retry nextId, count: {}", i);
                }
                return proxy.nextId(tag);
            } catch (Exception e) {
                throwable = e;
                retryInterval();
            }
        }
        throw new HttpRetryException(String.format("retry over %s", retryMax), throwable);
    }

    @Override
    public List<Long> nextId(int batchSize, String tag) {
        Throwable throwable = null;
        for (int i = 0; i <= retryMax; i++) {
            try {
                if (i >= 1) {
                    log.warn("retry batch nextId, count: {}", i);
                }
                return proxy.nextId(batchSize, tag);
            } catch (Exception e) {
                throwable = e;
                retryInterval();
            }
        }
        throw new HttpRetryException(String.format("retry over %s", retryMax), throwable);
    }

    @SneakyThrows
    private void retryInterval() {
        TimeUnit.MILLISECONDS.sleep(retryIntervalMil);
    }

}
