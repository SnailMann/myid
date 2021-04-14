package com.snailmann.myid.common.model.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liwenjie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IDResult {

    private Long id;

    private String instanse;

    private String threadName;

    private String v;

    private Long max;

    private String tag;

    private int pos;

    private Long createDate;

}
