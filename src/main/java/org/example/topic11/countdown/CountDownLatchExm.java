package org.example.topic11.countdown;

import java.util.concurrent.*;

public class CountDownLatchExm implements Callable<String> {

    private CountDownLatch latch;
    CountDownLatchExm(CountDownLatch latch){
        this.latch=latch;
    }
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" started....");
        try {
            Thread.sleep(1000);
        }finally {
            latch.countDown();      /// it is used to count total number of latch by decrementing
//            System.out.println(latch.getCount());
        }
        System.out.println(Thread.currentThread().getName()+" finished....");
        return "done";
    }

    public static void main(String[] args) throws InterruptedException {

        int totalService=3;
        ExecutorService executorService= Executors.newFixedThreadPool(totalService);
        CountDownLatch latch= new CountDownLatch(totalService);

        executorService.submit(new CountDownLatchExm(latch));
        executorService.submit(new CountDownLatchExm(latch));
        executorService.submit(new CountDownLatchExm(latch));

        latch.await();  //  , it stop main thread till latch countdown complete
//        latch.await(5, TimeUnit.SECONDS) // wait for 5 seconds
        System.out.println("*************");

        executorService.shutdown();

        System.out.println("main finished");

    }


}
