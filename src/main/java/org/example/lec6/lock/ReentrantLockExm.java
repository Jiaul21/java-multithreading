package org.example.lec6.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExm {
    private final Lock lock=new ReentrantLock();
    public  void lockType1() {
        System.out.println(Thread.currentThread().getName()+" running lockType1 method");


        // this lock work as synchronized block (after finishing 1 thread, another will start executing otherwise will wait)
        // lock() method lock below part of the code of lock() method, above code part  can be executed by all thread
        lock.lock();

        System.out.println(Thread.currentThread().getName()+" ********* locked ********");
        try {
            Thread.sleep(20000);             //  doing some work
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" finished lockType1 method");

        lock.unlock();      // unlock() method used to unlock, after unlock another thread can execute
    }

    public void lockType2(){
        System.out.println(Thread.currentThread().getName()+" running lockType2 method");

        // tryLock() method check it is available or not, if available then it lock and start execution
        // if it is already locked by another thread, it returns false but do not wait anymore
        if(lock.tryLock()){

            System.out.println(Thread.currentThread().getName()+" ********* locked ********");
            try {
                Thread.sleep(5000);             //  doing some work
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName()+" finished lockType2 method");

            lock.unlock();      // unlock() method used to unlock, after unlock another thread can execute
        }
        else {
            System.out.println(Thread.currentThread().getName()+" failed to lock");
        }
    }

    public void lockType3(){
        System.out.println(Thread.currentThread().getName()+" running lockType2 method");

        try {

            // tryLock(time) method check it is available or not, if available then it lock and start execution
            // if it is already locked by another thread, it waits some time to get available
            // after time wait if not available then returns false
            if(lock.tryLock(5000, TimeUnit.MILLISECONDS)){

                System.out.println(Thread.currentThread().getName()+" ********* locked ********");
                try {
                    Thread.sleep(4000);             //  doing some work
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println(Thread.currentThread().getName()+" finished lockType2 method");

                lock.unlock();      // unlock() method used to unlock, after unlock another thread can execute
            }
            else {
                System.out.println(Thread.currentThread().getName()+" failed to lock");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




    public static void main(String[] args) throws InterruptedException {

        ReentrantLockExm obj=new ReentrantLockExm();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                obj.lockType1();
//                obj.lockType2();
//                obj.lockType3();
            }
        };

        Thread t1= new Thread(runnable, "thread 1");
        Thread t2= new Thread(runnable, "thread 2");
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("main finished");
    }
}
