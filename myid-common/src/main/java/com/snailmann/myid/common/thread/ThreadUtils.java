package com.snailmann.myid.common.thread;

import com.snailmann.myid.common.time.Clock;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liwenjie
 */
@Slf4j
public class ThreadUtils {

    public static void sleepQuietly(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }

    public static void sleepQuietly(Clock clock, long time) {
        try {
            clock.sleep(time);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
