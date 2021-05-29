package com.demo.server.web.config;

import com.demo.base.common.utils.LogUtils;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author Vince Yuan
 * @date 04/20/2021
 */
@Slf4j
@Configuration
public class ThreadPoolConfig {

    @Bean("ioIntensiveThreadPool")
    public ExecutorService getIoIntensiveThreadPool() {
        return new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactoryBuilder()
                .setNameFormat("single_thread_pool-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> log.error(LogUtils.getLogMessage("ThreadPool " + thread + " got exception"), throwable))
                .build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Bean("cpuIntensiveThreadPool")
    public ExecutorService getCpuIntensiveThreadPool() {
        return new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactoryBuilder()
                .setNameFormat("single_thread_pool-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> log.error(LogUtils.getLogMessage("ThreadPool " + thread + " got exception"), throwable))
                .build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Bean
    public ExecutorService customerThreadPool1() {
        return new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactoryBuilder()
                .setNameFormat("single_thread_pool-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> log.error(LogUtils.getLogMessage("ThreadPool " + thread + " got exception"), throwable))
                .build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Bean
    public ExecutorService customerThreadPool2() {
        return new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactoryBuilder()
                .setNameFormat("single_thread_pool-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> log.error(LogUtils.getLogMessage("ThreadPool " + thread + " got exception"), throwable))
                .build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Bean("customerThreadPool3")
    public ExecutorService customerThreadPool3() {
        return new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new ThreadFactoryBuilder()
                .setNameFormat("single_thread_pool-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> log.error(LogUtils.getLogMessage("ThreadPool " + thread + " got exception"), throwable))
                .build(),
                new ThreadPoolExecutor.AbortPolicy());
    }

    @Bean("customerThreadPool4")
    public ExecutorService customerThreadPool4() {
        return new ThreadPoolExecutor(1, 1, 0L,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(1000), new ThreadFactoryBuilder()
                .setNameFormat("single_thread_pool-%d")
                .setUncaughtExceptionHandler((thread, throwable) -> log.error(LogUtils.getLogMessage("ThreadPool " + thread + " got exception"), throwable))
                .build(),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
