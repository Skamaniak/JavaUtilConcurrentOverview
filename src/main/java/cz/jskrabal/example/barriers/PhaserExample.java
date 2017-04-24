package cz.jskrabal.example.barriers;


import cz.jskrabal.ExampleUtils;

import java.util.concurrent.Phaser;

/**
 * Created by Jan Skrabal skrabalja@gmail.com
 */
public class PhaserExample {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(0);
        phaser.register();
        //Two lines above are the equivalent to just Phaser phaser = new Phaser(1);

        System.out.println("Main thread created Phaser and registered");
        for (int i = 1; i <= 5; i++) {
            new Thread(new Task(phaser), "T" + i).start();
        }

        System.out.println("MAIN thread finished phase " +  phaser.arriveAndAwaitAdvance());
        //All threads finished phase 0 at this point
        System.out.println("MAIN thread finished phase " +  phaser.arriveAndAwaitAdvance());
        //All threads finished phase 1 at this point
        System.out.println("MAIN thread finished phase " +  phaser.arriveAndAwaitAdvance());
        //All threads finished phase 2 at this point
        System.out.println("MAIN thread finished phase " +  phaser.arriveAndAwaitAdvance());
        //All threads finished phase 3 at this point

        System.out.println("Phase " + phaser.arriveAndDeregister() + " finished. Main thread will terminate");

    }



    public static class Task implements Runnable {

        private final Phaser phaser;

        public Task(Phaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            int register = phaser.register();
            System.out.println("Thread " + Thread.currentThread().getName() + " registered at phase " + register);

            ExampleUtils.sleep(500);

            System.out.println("Thread " + Thread.currentThread().getName() + " finished phase " +
                    phaser.arriveAndAwaitAdvance());

            ExampleUtils.sleep(500);

            System.out.println("Thread " + Thread.currentThread().getName() + " finished phase " +
                    phaser.arriveAndAwaitAdvance());

            ExampleUtils.sleep(500);

            System.out.println("Thread " + Thread.currentThread().getName() + " finished phase " +
                    phaser.arriveAndAwaitAdvance() + " and deregistered");
            phaser.arriveAndDeregister();
        }


    }
}
