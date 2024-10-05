package org.example.topic9.executor;

import java.util.concurrent.*;

public class ExecutorExm5 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executorService= Executors.newFixedThreadPool(5);

        Future<String> future= executorService.submit(()->{
            System.out.println(Thread.currentThread().getName()+" is running...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+" is done...");
            return " done...";
        });

//        System.out.println("is done :" +future.isDone());
//        System.out.println("is cancelled :" +future.isCancelled());

//        future.cancel(true);    // it will cancel thread execution

        Thread.sleep(1000); // give change to start thread

        // it will cancel execution if thread is not started yet
        // if thread started then it do not stop thread
        future.cancel(false);



        System.out.println("is done :" +future.isDone());
        System.out.println("is cancelled :" +future.isCancelled());

        executorService.shutdown();
    }
}
