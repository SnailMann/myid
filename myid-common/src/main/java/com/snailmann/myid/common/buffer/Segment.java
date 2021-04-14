package com.snailmann.myid.common.buffer;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * id section [min,max)
 *
 * @author liwenjie
 */
@Data
@NoArgsConstructor
public class Segment {

    /**
     * offset
     */
    private AtomicLong offset;

    /**
     * start id
     */
    private Long sId;

    /**
     * min id
     */
    private Long min;

    /**
     * max id (no contains)
     */
    private Long max;

    /**
     * step
     */
    private Integer step;

    /**
     * consumed or uninitialized
     */
    private boolean ready = false;

    /**
     * idgen version
     */
    private String version;

    /**
     * update time of segment
     */
    private Date updateTime;

    /**
     * get id
     *
     * @return id
     */
    public Long offsetAndIncr() {
        return offset.getAndIncrement();
    }

    /**
     * score20
     */
    public Long offsetOfScore20() {
        return this.min + this.getStep() / 4;
    }

    /**
     * idle size
     */
    public Integer idle() {
        return (int) (this.max - this.min);
    }

    @Override
    public String toString() {
        return "Segment{" +
                "offset=" + offset +
                ", min=" + min +
                ", max=" + max +
                ", ready=" + ready +
                ", v=" + version +
                '}';
    }
}
