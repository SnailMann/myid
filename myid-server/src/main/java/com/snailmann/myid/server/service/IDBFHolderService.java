package com.snailmann.myid.server.service;

import com.snailmann.myid.common.buffer.IDBuffer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

/**
 * @author liwenjie
 */
public interface IDBFHolderService {

    ThreadPoolExecutor EXECUTOR = executor();

    void refresh();

    IDBuffer getBuffer(String tag);

    static ThreadPoolExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(-1);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("buffer-thread-");
        executor.setRejectedExecutionHandler(new CallerRunsPolicy());
        executor.initialize();
        return executor.getThreadPoolExecutor();
    }
}
