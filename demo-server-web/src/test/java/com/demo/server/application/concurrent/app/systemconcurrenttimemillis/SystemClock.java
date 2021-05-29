package com.demo.server.application.concurrent.app.systemconcurrenttimemillis;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class SystemClock {

    private static final String THREAD_NAME = "SystemClock";
    private static final AtomicLong NOW = new AtomicLong(System.currentTimeMillis());
    private static final long PRECISION = 1;
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    static {
        Executors.newSingleThreadScheduledExecutor(runnable -> {
            Thread thread = new Thread(runnable, THREAD_NAME);
            thread.setDaemon(true);
            return thread;
        }).scheduleAtFixedRate(
                () -> NOW.set(System.currentTimeMillis()), PRECISION, PRECISION, TIME_UNIT
        );
    }

    public static long now() {
        return NOW.get();
    }
}
