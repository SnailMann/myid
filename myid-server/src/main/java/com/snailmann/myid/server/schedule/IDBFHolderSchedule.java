package com.snailmann.myid.server.schedule;

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
public class IDBFHolderSchedule implements ApplicationListener<ApplicationReadyEvent> {

    private final IDBFHolderService idbfHolderService;

    private boolean isInit = false;

    public IDBFHolderSchedule(IDBFHolderService idbfHolderService) {
        this.idbfHolderService = idbfHolderService;
    }

    public void schedule() {
        log.info("start schedule refresh holder");
        idbfHolderService.refresh();
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // avoid multiple ready
        if (isInit) {
            return;
        }

        log.info("first time refresh holder ");
        isInit = true;
        idbfHolderService.refresh();

        // start schedule after x minute
        TimeUnit.SECONDS.sleep(60);
        TaskThread taskThread = new TaskThread();
        taskThread.setName("holder-refresh-thread-");
        taskThread.start();
    }


    private class TaskThread extends KillableIntervalThread {

        @Override
        protected long getInterval() {
            return TimeUnit.SECONDS.toMillis(60);
        }

        @Override
        protected void doWork() {
            schedule();
        }

    }
}
