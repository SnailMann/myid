package com.snailmann.myid.server.ticker;

import com.snailmann.myid.common.thread.KillableIntervalThread;
import com.snailmann.myid.server.service.IDBFHolderService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author liwenjie
 */
@Slf4j
@Component
public class IDBFHolderTicker implements ApplicationListener<ApplicationReadyEvent> {

    private final IDBFHolderService idbfHolderService;

    private volatile boolean initialized = false;

    public IDBFHolderTicker(IDBFHolderService idbfHolderService) {
        this.idbfHolderService = idbfHolderService;
    }

    public void run() {
        idbfHolderService.refresh();
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // avoid multiple ready
        if (initialized) {
            return;
        }
        initialized = true;
        idbfHolderService.refresh();
        log.info("refreshing holder");

        // start ticker after x second
        TimeUnit.SECONDS.sleep(60);
        TaskThread taskThread = new TaskThread();
        taskThread.setName("holder-");
        taskThread.start();
    }


    private class TaskThread extends KillableIntervalThread {

        @Override
        protected long getInterval() {
            return TimeUnit.SECONDS.toMillis(60);
        }

        @Override
        protected void doWork() {
            IDBFHolderTicker.this.run();
        }

    }
}
