package org.example.topic6.lock;

public class IntrinsicLock {

    public synchronized void check() {       // after finishing 1 thread, another will start executing otherwise will wait
                                            // because we use synchronized(intrinsic or internal) to lock total method body

        System.out.println(Thread.currentThread().getName()+" running");
        try {
            Thread.sleep(5000);             //  doing some work
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" finished");
    }

    public static void main(String[] args) throws InterruptedException {

        IntrinsicLock l=new IntrinsicLock();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                l.check();
            }
        };

        Thread t1= new Thread(runnable, "t1");
        Thread t2= new Thread(runnable, "t2");
        t1.start();
        t2.start();
        System.out.println("all thread started");

        t1.join();
        t2.join();

        System.out.println("main finished");
    }
}
