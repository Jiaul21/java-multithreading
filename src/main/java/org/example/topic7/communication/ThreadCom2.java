package org.example.topic7.communication;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadCom2 {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();  // Condition object for communication between thread

    public void method1() {
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " started method1");

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (i == 3) {
                    System.out.println(Thread.currentThread().getName() + " waiting...");
                    try {
                        condition.await();  // Wait for a signal from method2
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            System.out.println(Thread.currentThread().getName() + " finished method1");
        } finally {
            lock.unlock();
        }
    }

    public void method2() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " started method2");

            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            System.out.println(Thread.currentThread().getName() + " signaling...");
            condition.signalAll();
            System.out.println(Thread.currentThread().getName() + " finished method2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadCom2 communication = new ThreadCom2();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                communication.method1();
            }
        };

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                communication.method2();
            }
        };

        Thread thread1 = new Thread(runnable1, "Thread 1");
        Thread thread2 = new Thread(runnable1, "Thread 2");
        Thread thread3 = new Thread(runnable2, "Thread 3");

        thread1.start();
        Thread.sleep(50);  // Ensure thread1 starts and waits before thread2 starts
        thread2.start();
        Thread.sleep(8000);  // Ensure thread3 starts after thread2 starts
        thread3.start();


        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Main thread finished");
    }
}
