package com.snailmann.myid.common.model.dto;

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
public class IDGenDTO {

    private Long id;

    private String tag;

    private Integer step;

    private Long sid;

    private Long mid;

    private Long version;

    private String editor;

    private Long creation;

    private Long modification;

}
