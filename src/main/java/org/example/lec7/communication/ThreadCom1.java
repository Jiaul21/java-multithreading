package org.example.lec7.communication;

public class ThreadCom1 {
    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + " started method1");
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (i == 3) {
                try {
                    System.out.println(Thread.currentThread().getName() + " waiting...");
                    wait();  // Waiting for notification from method2
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " finished method1");
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + " started method2");
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(Thread.currentThread().getName() + " notifying...");
        notify();
        System.out.println(Thread.currentThread().getName() + " finished method2");
    }

    public static void main(String[] args) throws InterruptedException {

        ThreadCom1 communication = new ThreadCom1();

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
        Thread thread2 = new Thread(runnable2, "Thread 2");

        thread1.start();
        Thread.sleep(50);  // Ensure thread1 starts and waits before thread2 starts
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Main thread finished");
    }


}
