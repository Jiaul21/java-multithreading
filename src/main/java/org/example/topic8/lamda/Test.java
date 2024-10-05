package org.example.topic8.lamda;

public class Test {


    public static void main(String[] args) {

        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                System.out.println("anonymous class");
            }
        };
        // Runnable interface(Functional Interface) have only one abstract method
        // so no need to write method name
        Runnable runnable1= () -> {
            System.out.println("Lambda 1");
        };

        Runnable runnable2= () -> System.out.println("Lambda 2");   // as it contains only one statement

        Thread thread=new Thread(runnable);
        Thread thread1=new Thread(runnable1);
        Thread thread2=new Thread(runnable2);
        Thread thread3=new Thread(() -> System.out.println("Lambda 3"));    // making it more easy

        Thread thread4= new Thread(()->{        // more standard
            System.out.println("standard");
        });

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }
}
