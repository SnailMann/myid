package com.snailmann.myid.client.template.impl;

import com.snailmann.myid.client.template.IDTemplate;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * [test phrase] local id template, no use in prod environment
 *
 * @author liwenjie
 */
@Slf4j
public class LocalIDTemplate implements IDTemplate {

    @Override
    public Long nextId(String tag) {
        // TODO
        return null;
    }

    @Override
    public List<Long> nextId(int batchSize, String tag) {
        // TODO
        return null;
    }
}
