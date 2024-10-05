package org.example.topic11.countdown;

import java.util.concurrent.*;

public class CyclicBarrierExample implements Callable<String> {

    private CyclicBarrier barrier;
    CyclicBarrierExample(CyclicBarrier barrier){
        this.barrier=barrier;
    }
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+" started....");

        Thread.sleep(3000);

        // all thread of this method when reached in this point then barrier release all thread together
        // if 1 thread reach first then it will wait to reach all
        barrier.await();

        System.out.println(Thread.currentThread().getName()+" finished....");

        return "done";
    }

    public static void main(String[] args) {

        int totalService=3;
        ExecutorService executorService= Executors.newFixedThreadPool(totalService);
        CyclicBarrier barrier= new CyclicBarrier(totalService);

        executorService.submit(new CyclicBarrierExample(barrier));
        executorService.submit(new CyclicBarrierExample(barrier));
        executorService.submit(new CyclicBarrierExample(barrier));

        System.out.println("******* main finished ******");
        executorService.shutdown();
    }
}
