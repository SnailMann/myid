package com.snailmann.myid.client.proxy;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * @author liwenjie
 */
public class BaseProxy extends Proxy {

    @Value("${myid.server.proxy.connection-timeout:300}")
    private int connectionTimeOut = 500;

    @Value("${myid.server.proxy.read-timeout:300}")
    private int readTimeOut = 500;

    @Value("${myid.server.proxy.httpclient.max-conn-total:200}")
    private int maxConnTotal = 200;

    @Value("${myid.server.proxy.httpclient.max-conn-per-route:200}")
    private int maxConnPerRoute = 200;

    @Value("${myid.server.proxy.base-url}")
    protected String baseUrl;

    @PostConstruct
    public void baseinit() {
        super.setCustomRequestFactory(connectionTimeOut, readTimeOut, maxConnTotal, maxConnPerRoute);
    }


}
