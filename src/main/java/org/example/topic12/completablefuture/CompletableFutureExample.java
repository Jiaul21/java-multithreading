package org.example.topic12.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // CompletableFuture is by default Daemon thread, so it automatically stop after main thread finished
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+" started...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName()+" finished...");
            return "done";
        });
//        System.out.println(future1.get());
        future1.join();


        ExecutorService executorService= Executors.newFixedThreadPool(5);

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()+" started...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(Thread.currentThread().getName()+" finished...");
            return "done";

        },executorService); // CompletableFuture will use executorService threads

        executorService.shutdown();
        System.out.println("**************** main finished *****************");
    }
}
