/**
 * @(#)KillableIntervalThread.java, Aug 28, 2018.
 * <p>
 * Copyright 2018 lillard. All rights reserved. Use is subject to license terms.
 */
package com.snailmann.myid.common.thread;


import com.snailmann.myid.common.time.Clock;
import com.snailmann.myid.common.time.SystemClock;
import lombok.extern.slf4j.Slf4j;


/**
 * @author liwenjie
 */
@Slf4j
public abstract class KillableIntervalThread extends KillableThread {

    private Clock clock = SystemClock.INSTANCE;

    public KillableIntervalThread() {
        super();
    }

    public KillableIntervalThread(Runnable runnable) {
        super(runnable);
    }

    public void setClock(SystemClock clock) {
        this.clock = clock;
    }

    @Override
    public final void run() {
        while (!killed) {
            try {
                doWork();
            } catch (Exception e) {
                log.error("", e);
            }
            ThreadUtils.sleepQuietly(clock, getInterval());
        }
    }

    protected abstract long getInterval();

    protected abstract void doWork() throws Exception;
}
