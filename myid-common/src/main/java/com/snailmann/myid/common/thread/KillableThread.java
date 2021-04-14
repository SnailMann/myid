/**
 * @(#)KillableThread.java, Aug 28, 2018.
 * <p>
 * Copyright 2018 lillard. All rights reserved. Use is subject to license terms.
 */
package com.snailmann.myid.common.thread;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;


/**
 * @author liwenjie
 */
@Slf4j
public class KillableThread extends Thread {

    protected volatile boolean killed = false;

    public KillableThread() {
        super();
        init();
    }

    public KillableThread(Runnable runnable) {
        super(runnable);
        init();
    }

    protected void init() {
        String threadName = threadName();
        if (!StringUtils.isBlank(threadName)) {
            setName(threadName);
        }
    }

    public boolean kill() {
        return kill(true);
    }

    public boolean kill(boolean wait) {
        if (getState() == State.NEW) {
            throw new IllegalStateException("thread is not started");
        }
        if (getState() == State.TERMINATED) {
            log.warn("Thread already terminated");
            return false;
        }
        killed = true;
        this.interrupt();
        if (wait) {
            try {
                this.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    protected String threadName() {
        return getClass().getSimpleName();
    }
}
