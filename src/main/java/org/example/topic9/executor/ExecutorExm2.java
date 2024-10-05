package org.example.topic9.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorExm2 {
    public void print(int n){
        System.out.println(Thread.currentThread().getName()+" is started... ");
        for(int i=1;i<=n;i++){
            System.out.println(Thread.currentThread().getName()+" printing: "+i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName()+" is finished... ");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorExm2 obj=new ExecutorExm2();


        // ExecutorService automatic named thread as thread-1, thread-2...
        // newFixedThreadPool create a pool that have n threads
        ExecutorService executorService= Executors.newFixedThreadPool(5);


        executorService.submit(()->{        // direct create runnable and start
            obj.print(5);
        });
        executorService.submit(()->{        // direct create runnable and start
            obj.print(5);
        });

        // after finishing all thread it will shutdown executor service to stop program
        // it does not wait for all thread get finished
        executorService.shutdown();

//        executorService.shutdownNow(); // it shutdown all thread immediately

        executorService.awaitTermination(4, TimeUnit.SECONDS);  // it block main thread for some time

//        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)){ // block main thread for unlimited time
//            System.out.println("------- waiting for all thread finished ------");
//        }

        System.out.println("++++++++++ main finished +++++++++++");


    }
}
