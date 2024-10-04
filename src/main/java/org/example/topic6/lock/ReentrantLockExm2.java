package org.example.topic6.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExm2 {

    private final Lock lock=new ReentrantLock(true);     // fair true means which thread come first they served first
                                                            // every thread must change sequentially
                                                            // no change of starvation

    public  void lockType1() {
        System.out.println(Thread.currentThread().getName()+" running lockType1 method");
        lock.lock();
        System.out.println(Thread.currentThread().getName()+" ********* locked ********");
        method1();
        System.out.println(Thread.currentThread().getName()+" finished lockType1 method");
        lock.unlock();
    }
    public void method1(){

        // ReentrantLock can re-lock based on number of lock and unlock used
        // so, it does not occur deadlock
        lock.lock();
        System.out.println(Thread.currentThread().getName()+" -------- inside new method --------");
        try {
            Thread.sleep(5000);             //  doing some work
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lock.unlock();
    }

    public void lockType4(){
        System.out.println(Thread.currentThread().getName()+" running lockType2 method");

        // lockInterruptibly() method same as lock() method
        // if it is already locked, it waits as (WAITING) state, not (TIMED_WAITING) state
        try {
            lock.lockInterruptibly();

            System.out.println(Thread.currentThread().getName()+" ********* locked ********");
            try {
                Thread.sleep(5000);             //  doing some work
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName()+" finished lockType2 method");

            lock.unlock();      // unlock() method used to unlock, after unlock another thread can execute
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ReentrantLockExm2 obj=new ReentrantLockExm2();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
//                obj.lockType1();
                obj.lockType4();
            }
        };

        Thread t1= new Thread(runnable, "thread 1");
        Thread t2= new Thread(runnable, "thread 2");
        t1.start();
        Thread.sleep(100); // make sure t1 reached first to check lockInterruptibly() method
        t2.start();

        Thread.sleep(100); // wait for t2 reached to lockInterruptibly() method
        System.out.println(t2.getName()+" state is =>>>> "+t2.getState());

        t1.join();
        t2.join();

        System.out.println("main finished");
    }
}
