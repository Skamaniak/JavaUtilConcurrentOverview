package cz.jskrabal.example;

import java.util.concurrent.locks.ReentrantLock;


public class ReentrantLockExample {
    private static final ReentrantLock REENTRANT_LOCK = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new Task(), "T2").start();
        new Thread(new Task(), "T1").start();
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            recursiveFunction(0);
        }
        void recursiveFunction(int nestIndex) {
            if(nestIndex != 5)  {
                try{
                    REENTRANT_LOCK.lock();
                    System.out.println(Thread.currentThread().getName() + " - " +
                            REENTRANT_LOCK.getHoldCount());
                    recursiveFunction(nestIndex + 1);
                } finally {
                    REENTRANT_LOCK.unlock();
                }
            }
        }
    }
}
