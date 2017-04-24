package cz.jskrabal.example.barriers;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Jan Skrabal skrabalja@gmail.com
 */
public class CyclicBarrierExample {
    private static final int BARRIER_PARTIES = 5;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(BARRIER_PARTIES, () -> System.out.println("Barrier released"));

        for (int i = 1; i <= 5; i++) {
            new Thread(new BarrierRunner(cyclicBarrier), "T" + i).start();
        }
    }

    public static class BarrierRunner implements Runnable {
        private final CyclicBarrier barrier;

        public BarrierRunner(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("Thread " + Thread.currentThread().getName() + " waiting on barrier");
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("Thread " + Thread.currentThread().getName() + " can continue");

        }
    }
}
