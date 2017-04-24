package cz.jskrabal.example;

import cz.jskrabal.ExampleUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * Created by Jan Skrabal skrabalja@gmail.com
 */
public class StampedLockExample {

    //Optimistic locking example - http://winterbe.com/posts/2015/04/30/java8-concurrency-tutorial-synchronized-locks-examples/

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                ExampleUtils.sleep(1);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                ExampleUtils.sleep(2);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            } finally {
                lock.unlock(stamp);
            }
        });

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                ExampleUtils.sleep(2);
            } finally {
                lock.unlock(stamp);
                System.out.println("Write done");
            }
        });

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
