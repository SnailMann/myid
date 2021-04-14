package com.snailmann.myid.server.service;

import java.util.List;

/**
 * @author liwenjie
 */
public interface IDService {

    Long next(String tag);

    List<Long> next(int batchSize, String tag);
}
