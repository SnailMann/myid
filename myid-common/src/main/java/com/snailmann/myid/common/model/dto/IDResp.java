package com.snailmann.myid.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * @author liwenjie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IDResp {

    public static final int OK = 10000;

    public static final int ERROR = 99999;

    private int code;

    private int batchSize;

    private List<Long> ids;

    public static IDResp ok(int batchSize, List<Long> ids) {
        return IDResp.builder()
                .code(OK).batchSize(batchSize).ids(ids)
                .build();
    }

    public static IDResp error(int batchSize) {
        return IDResp.builder()
                .code(ERROR).batchSize(batchSize).ids(Collections.emptyList())
                .build();
    }


}
