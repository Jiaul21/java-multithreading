package org.example.topic1.create;

public class Test2 implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }



            // using Runnable interface
    public static void main(String[] args) {

        Test2 t=new Test2();
        Thread thread= new Thread(t);
        thread.start();

        Test2 t2=new Test2();

        Thread thread2= new Thread(t2,"second thread");
        thread2.start();
    }
}
