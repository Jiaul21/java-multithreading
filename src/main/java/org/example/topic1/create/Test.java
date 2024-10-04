package org.example.topic1.create;

public class Test extends Thread{

//    Test(String name){        //new Test("second thread"); will set thread name
//        super(name);
//    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }




            // using Thread class

    public static void main(String[] args) {
        Test test=new Test();
        test.start();

    }
}
