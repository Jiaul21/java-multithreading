package org.example.topic9.executor;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorExm4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService=Executors.newFixedThreadPool(5);

        Callable<String> callable1= new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName()+" is executing");
                return Thread.currentThread().getName()+" is done";
            }
        };
        Callable<String> callable2= new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName()+" is executing");
                return Thread.currentThread().getName()+" is done";
            }
        };
        Callable<String> callable3= new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+" is executing");
                return Thread.currentThread().getName()+" is done";
            }
        };

//        List<Callable<String>> list= Arrays.asList(callable1, callable2, callable3);
//        List<Future<String>> futures = executorService.invokeAll(list);

        List<Future<String>> futures = executorService.invokeAll(List.of(callable1, callable2, callable3)); // main thread wait until all thread return

        System.out.println("invoked finished");


//        List<Future<String>> futures2 = null;
//        try {
//            // will wait given time after that throws exception
//            // if 2 task is done and 1 failed, then return 2 result with 1 exception
//            futures2 = executorService.invokeAll(List.of(callable1, callable2, callable3),1,TimeUnit.SECONDS);
//        }catch (Exception e){}
//        System.out.println("size: "+futures2.size());
//        for(Future f: futures2){
//            try {
//                System.out.println("future result: "+f.get());
//            } catch (InterruptedException e) {}
//        }

//        try {
//            // will return 1 task which one will complete first
//            String future3= executorService.invokeAny(List.of(callable1, callable2, callable3));
//        } catch (InterruptedException e) {
//        }

//        try{
//            // it wait till given time
//            String future4= executorService.invokeAny(List.of(callable1, callable2, callable3),2,TimeUnit.SECONDS);
//        } catch(TimeoutException | InterruptedException e) {
//        }


        executorService.shutdown();
        System.out.println("main finished");
    }
}
