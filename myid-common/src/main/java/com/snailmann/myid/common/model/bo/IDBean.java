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
public class IDBean {

    private Long id;

    private String tag;

    private Integer step;

    private Long sid;

    private Long mid;

    private String version;

    public Long minOfSegment() {
        return this.mid - this.step;
    }

    public boolean firstUse() {
        return this.sid.equals(this.mid - this.step);
    }

}
