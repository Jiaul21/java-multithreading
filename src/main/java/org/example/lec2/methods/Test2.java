package org.example.lec2.methods;

public class Test2 extends Thread{
    @Override
    public void run() {
        System.out.println("++++++++++++++++++++++++++++++");

        for(int i=1;i<=20;i++){
            System.out.println(currentThread().getName()+": "+i);

            Thread.yield(); // provide opportunity to other thread to get execution but this current thread will not stop
        }

        if(currentThread().isInterrupted()) System.out.println(currentThread().getName()+": is interrupted");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        Test2 t1=new Test2();
        Test2 t2=new Test2();

        t1.start();
        t2.start();
        t1.interrupt();// interrupt t1 but it works


    }
}
