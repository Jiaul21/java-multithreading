package org.example.lec6.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExm {

    private final ReadWriteLock readWriteLock= new ReentrantReadWriteLock(true);
    private final Lock readLock= readWriteLock.readLock();      // read lock does not block other threads, they can execute together
    private final Lock writeLock= readWriteLock.writeLock();       // write lock block other thread like ReentrantLock

    private int count=0;


    public void increment(){
        writeLock.lock();               // we can use tryLock() or other methods
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count++;
        System.out.println(Thread.currentThread().getName()+" incrementing <<<<: "+count);
        writeLock.unlock();
    }

    public void getCount(){
        readLock.lock();                // we can use tryLock() or other methods
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+" reading count: >>>"+count);

        readLock.unlock();
    }


    public static void main(String[] args) throws InterruptedException {

        ReadWriteLockExm r=new ReadWriteLockExm();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    r.increment();
                }
            }
        };
        Runnable runnable2=new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<5;i++){
                    r.getCount();
                }
            }
        };

        Thread thread1= new Thread(runnable,"write thread 1");
        Thread thread2= new Thread(runnable,"write thread 2");

        Thread thread3= new Thread(runnable2,"read thread 3");
        Thread thread4= new Thread(runnable2,"read thread 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();


        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println("main finished");
    }
}
