package hell0hd.gateway;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class Scheduler {

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    private static final ScheduledExecutorService SCHEDULER = Executors.newScheduledThreadPool(10, runnable -> new Thread(runnable, "Scheduler-" + COUNTER.getAndIncrement()));

    public static ScheduledFuture<?> schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
        return SCHEDULER.schedule(runnable, delay, timeUnit);
    }

    public static ScheduledFuture<?> schedule(Runnable runnable, long delay, long period, TimeUnit timeUnit) {
        return SCHEDULER.scheduleAtFixedRate(runnable, delay, period, timeUnit);
    }

}
