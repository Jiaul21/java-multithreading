package org.example.topic5.synchronize;

public class Count {
    public static int a;
    public int b;
    public int c;

    Count(){
        a=0;
        b=0;
        c=0;
    }

    public static synchronized void incrementA() {
        a++;
    } // static synchronized
    public synchronized void incrementB() {     // synchronized keyword lock method till this thread finished
        b++;
    }
    public void incrementC() {
        synchronized (this){        // make synchronized only this block  current (this) instance
            c++;
        }
    }
}
