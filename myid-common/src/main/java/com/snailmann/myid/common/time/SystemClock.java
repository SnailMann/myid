package com.snailmann.myid.common.time;


/**
 * @author liwenjie
 */
public class SystemClock implements Clock {

    public static final SystemClock INSTANCE = new SystemClock();

    private SystemClock() {
    }

    @Override
    public long currentTime() {
        return System.currentTimeMillis();
    }

    @Override
    public void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
