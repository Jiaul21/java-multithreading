package org.example.topic9.executor;

import java.util.concurrent.*;

public class ExecutorExm3 {

    public void print(int n) {
        System.out.println(Thread.currentThread().getName() + " is started... ");
        for (int i = 1; i <= n; i++) {
            System.out.println(Thread.currentThread().getName() + " printing: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + " is finished... ");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorExm3 obj = new ExecutorExm3();

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
            }
        };
        Callable<String> callable = new Callable() {
            @Override
            public Object call() throws Exception {     // return Object type
                return "done";
            }
        };

        Future<?> future1 =executorService.submit(() -> {        // submit method get Runnable implementation that we can not return from inside thread
                                                                // future returned by method
            obj.print(5);
        });


        Future<?> future2 =executorService.submit(() -> {        // submit method get Callable implementation automatically that we can return manually
                                                                // future returned by method
            obj.print(5);
            return 10;
        });

        Future<?> future3 =executorService.submit(() -> {
            // future returned by method
            obj.print(5);
        },"hello");     // this result "hello" will return to the future, It also call Runnable interface


        // in future2.get() method, main thread wait for result that return from submit() method
        System.out.println(future1.get());      // it return null because runnable do not return
        System.out.println(future2.get());      // it get the return value
        System.out.println(future3.get());      // it get the return value

        System.out.println(future2.get(2,TimeUnit.SECONDS));     // wait for given time only




        executorService.shutdown();

        System.out.println("++++++++++ main finished +++++++++++");
    }
}