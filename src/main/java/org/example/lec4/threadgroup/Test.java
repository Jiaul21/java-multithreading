package org.example.lec4.threadgroup;

public class Test extends Thread{

    Test(ThreadGroup tg, String tName){
        super(tg,tName);
    }
    Test(){}

    @Override
    public void run() {
        System.out.println("+++++++++++++++++++ started +++++++++++++++++++");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        ThreadGroup tg= new ThreadGroup("group 1");

        Test t1= new Test(tg,"t1");     // thread using class constructor
        Test t2= new Test(tg,"t2");

        Thread t3= new Thread(tg,new Test(),"t3");     // using Thread class

        t1.start();
        t2.start();
        t3.start();

        System.out.println("Name: "+tg.getName());
        System.out.println("Parent: "+tg.getParent());
        System.out.println("Max Priority "+tg.getMaxPriority());
        System.out.println("active count "+tg.activeCount());
        System.out.println("active group "+tg.activeGroupCount());

        System.out.println("main finished");

    }
}
