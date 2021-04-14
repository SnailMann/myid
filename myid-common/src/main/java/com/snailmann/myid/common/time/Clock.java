package com.snailmann.myid.common.time;

/**
 * @author liwenjie
 */
public interface Clock {

    long currentTime();

    void sleep(long millis) throws InterruptedException;
}
