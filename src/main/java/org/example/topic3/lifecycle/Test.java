package org.example.topic3.lifecycle;

public class Test extends Thread{
    Test(String name){super(name);}

    @Override
    public void run() {
        System.out.println(currentThread().getName()+" "+currentThread().getState());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws InterruptedException {

        Test t1=new Test("thread 1");
//        Test t2=new Test("thread 1");

        System.out.println("main: "+Thread.currentThread().getState()); // RUNNABLE
        System.out.println("t1: "+t1.getState());                          // NEW
//        System.out.println("t2: "+t2.getState());

        t1.start();
        Thread.sleep(1000);
        System.out.println("t1 after 1s: "+t1.getState());      // TIMED_WAIT

        t1.join();
        System.out.println("t1: "+t1.getState());           // TERMINATED
    }
}
