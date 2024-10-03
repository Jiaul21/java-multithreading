package org.example.lec5.synchronize;

public class Test extends Thread{
    public Count count;
    Test(Count count){
        this.count=count;
    }

    @Override
    public void run() {
        for(int i=1;i<=1000;i++){
//            count.incrementA();
//            count.incrementB();
            count.incrementC();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Count count=new Count();

        Test t1= new Test(count);
        Test t2= new Test(count);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(count.a);
        System.out.println(count.b);
        System.out.println(count.c);
    }
}
