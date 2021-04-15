package com.snailmann.myid.client.template;

import java.util.List;

/**
 * @author liwenjie
 */
public interface IDTemplate {

    Long nextId(String tag);

    List<Long> nextId(int batchSize, String tag);

}
