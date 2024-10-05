package org.example.topic10.schedule.executor;

import java.util.concurrent.*;

public class ScheduleExecutor1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

        ScheduledFuture<String> future=scheduler.schedule(()->{
            System.out.println(Thread.currentThread().getName()+" started...");
            return "done....";
        },5, TimeUnit.SECONDS);     // start after 5 seconds
        System.out.println(future.get());


        scheduler.scheduleAtFixedRate(() -> {       // this methods stop after shutdown
            System.out.println(Thread.currentThread().getName() + " started... fixed rate");
        }, 3, 2, TimeUnit.SECONDS);

        scheduler.scheduleWithFixedDelay(()->{       // this methods stop after shutdown
            System.out.println(Thread.currentThread().getName()+" started... fixed delay");
        },3,2,TimeUnit.SECONDS);


        scheduler.schedule(() -> {
            scheduler.shutdown();       // shutdown 10 second later, after main thread finished
        },10,TimeUnit.SECONDS);

        System.out.println("++++++ main finished +++++++");
    }
}
