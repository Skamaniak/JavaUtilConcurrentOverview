package cz.jskrabal.example;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Jan Skrabal skrabalja@gmail.com
 */
public class ReentrantLockExample {
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        new ReentrantLockExample().test();
    }

    private void test(){
        new Thread(new Task(), "T1").start();
        new Thread(new Task(), "T2").start();
    }

    public class Task implements Runnable {

        @Override
        public void run() {
            recursiveFunction(0);
        }

        void recursiveFunction(int nestIndex) {
            if(nestIndex != 5)  {
                try{
                    reentrantLock.lock();
                    System.out.println(Thread.currentThread().getName() + " - " + reentrantLock.getHoldCount());
                    recursiveFunction(nestIndex + 1);
                } finally {
                    reentrantLock.unlock();
                }
            }
        }
    }
}
