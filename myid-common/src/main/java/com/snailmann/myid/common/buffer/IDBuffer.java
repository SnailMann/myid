package com.snailmann.myid.common.buffer;

import com.snailmann.myid.common.buffer.factory.IDBFFactory;
import com.snailmann.myid.common.model.bo.IDBean;
import com.snailmann.myid.common.model.bo.IDResult;
import com.snailmann.myid.common.util.InstanceUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liwenjie
 */
@Slf4j
public class IDBuffer {

    private String tag;

    private final Segment[] segments = new Segment[]{new Segment(), new Segment()};

    private volatile int cpos = 0;

    private IDBFFactory idbfFactory;

    private AtomicLong count = new AtomicLong(0);

    private volatile boolean ready = false;

    public IDBuffer(String tag, IDBFFactory idbfFactory) {
        log.info("build id buffer, tag: {}", tag);
        this.idbfFactory = idbfFactory;
        this.tag = tag;
        allocSegment(this.cpos);
        this.ready = true;
    }

    private synchronized void allocSegment(int pos) {
        allocSegment(this.tag, pos);
    }

    private synchronized void allocSegment(String tag, int pos) {
        IDBean bean = idbfFactory.alloc(tag);

        // alloc new segment object
        Long min = bean.firstUse() ?
                bean.getSid() : bean.minOfSegment();
        final Segment segment = new Segment();
        segment.setSId(bean.getSid());
        segment.setOffset(new AtomicLong(min));
        segment.setMin(min);
        segment.setMax(bean.getMid());
        segment.setStep(bean.getStep());
        segment.setReady(true);
        segment.setUpdateTime(new Date());
        segment.setVersion(bean.getVersion());

        this.segments[pos] = segment;
        this.count.incrementAndGet();
    }

    public Segment segment() {
        return this.segments[this.cpos];
    }

    public Segment nextSegment() {
        return this.segments[nextPos()];
    }

    public Segment avaliableSegment() {
        Segment segment = segment();

        // alloc segment if current segment no ready
        // under the synchronization mechanism, this problem will not occur
        if (!segment.isReady()) {

            // next segment also no ready, alloc current segment
            if (!nextSegment().isReady()) {
                allocSegment(this.cpos);
            } else { // or switch next segment
                switchPos();
            }
            segment = avaliableSegment();
        }

        return segment;
    }

    public IDResult nextId() {
        while (true) {
            final Segment segment = avaliableSegment();
            Long offset = segment.offsetAndIncr();
            Long max = segment.getMax();
            int pos = this.cpos;
            String v = segment.getVersion();

            // double check
            Long score20 = segment.offsetOfScore20();
            if (!nextSegment().isReady() && offset >= score20) {
                synchronized (this) {
                    if (!nextSegment().isReady()) {
                        allocSegment(nextPos());
                    }
                }
            }

            //log();
            if (offset < max) {
                return IDResult.builder().id(offset).max(max).pos(pos).tag(tag).v(v)
                        .threadName(Thread.currentThread().getName())
                        .instanse(InstanceUtils.instance)
                        .createDate(System.currentTimeMillis())
                        .build();
            } else {
                // id resources are exhausted
                segment.setReady(false);
            }
        }
    }


    private synchronized void switchPos() {
        this.cpos = nextPos();
    }

    private int nextPos() {
        return cpos == 0 ? 1 : 0;
    }

    public boolean isReady() {
        return this.ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }


}
