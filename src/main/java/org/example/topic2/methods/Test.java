package org.example.topic2.methods;

public class Test extends Thread{
    @Override
    public void run() {
        System.out.println("thread name: "+Thread.currentThread().getName());
        try {
            Thread.sleep(3000);     //wait in millisecond
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" thread state: "+Thread.currentThread().getState());
        try {
            Thread.sleep(3000,50);     //wait 3000 millisecond + 50 nanosecond
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" thread id: "+Thread.currentThread().getId());
        System.out.println(Thread.currentThread().getName()+" thread priority: "+Thread.currentThread().getPriority());
        System.out.println(Thread.currentThread().getName()+" thread stackTrace: "+Thread.currentThread().getStackTrace());


        System.out.println(Thread.currentThread().getName()+" thread alive: "+Thread.currentThread().isAlive());
        System.out.println(Thread.currentThread().getName()+" thread demon: "+Thread.currentThread().isDaemon());
        System.out.println(Thread.currentThread().getName()+" thread interupted: "+Thread.currentThread().isInterrupted());

    }

    public static void main(String[] args) throws InterruptedException {
        Test t=new Test();
        Test t2=new Test();

        System.out.println("State: "+t.getState());

        t.setName("first Thread");
        t2.setName("Second Thread");
        t.setPriority(10);
        t2.setPriority(10);
//        t.setDaemon(true);        // after nonDaemon main/all thread closed daemon thread are automatically  closed
//        t2.setDaemon(true);
        t.start();
        t2.start();

        System.out.println("thread started");

        t.join();       /// wait main thread until t thread finished
        t2.join();       /// wait main thread until t thread finished

//        t.join(1000);       /// wait main thread 1000 millisecond
//        t.join(1000,50);       /// wait main thread 1000 millisecond + 50 nanosecond

        System.out.println("main thread finished");
    }
}
