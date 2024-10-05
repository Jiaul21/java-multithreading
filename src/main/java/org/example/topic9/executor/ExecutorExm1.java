package org.example.topic9.executor;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExm1 {

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
        ExecutorExm1 obj=new ExecutorExm1();


        // ExecutorService automatic named thread as thread-1, thread-2...
        ExecutorService executorService= Executors.newFixedThreadPool(5);   // create fixed n number of thread
//        ExecutorService executorService1= Executors.newCachedThreadPool();      // it create thread based on requirement,re-use thread and automatically remove
//        ExecutorService executorService2= Executors.newSingleThreadExecutor();      // it create 1 thread only

        Thread thread1= new Thread(()->{        // lambda exp of runnable
            obj.print(5);
        });

        executorService.submit(thread1);        // used to start thread using executor

        System.out.println("thread 1 status: "+thread1.getState());// thread is not started but thread of pool is started

        executorService.submit(()->{        // direct create runnable and start
            obj.print(5);
        });

        for(int i=3;i<4;i++){           // creating lots of thread
            executorService.submit(()->{
                obj.print(5);
            });
        }

        executorService.shutdown();

    }
}
