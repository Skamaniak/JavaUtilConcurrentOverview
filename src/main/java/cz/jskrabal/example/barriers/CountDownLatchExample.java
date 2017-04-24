package cz.jskrabal.example.barriers;

import cz.jskrabal.ExampleUtils;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Jan Skrabal skrabalja@gmail.com
 */
public class CountDownLatchExample {
    private static final String[] COMPONENT_NAMES = new String[]{"Logging", "Monitoring", "Core", "ApiModule", "Networking"};
    private static final int ASYNC_SYSTEM_COMPONENTS = 5;

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(ASYNC_SYSTEM_COMPONENTS);

        for (int i = 0; i < ASYNC_SYSTEM_COMPONENTS; i++) {
            new Thread(new StartTask(COMPONENT_NAMES[i], countDownLatch)).start();
        }

        try {
            System.out.println("Awaiting async system components to start");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("\nAll components started");

    }

    public static class StartTask implements Runnable {
        private final CountDownLatch latch;
        private final String systemComponentName;

        StartTask(String systemComponentName, CountDownLatch latch) {
            this.latch = latch;
            this.systemComponentName = systemComponentName;
        }

        @Override
        public void run() {
            System.out.println("Starting " + systemComponentName + "...");
            long startTime = System.currentTimeMillis();
            ExampleUtils.sleep((int) (Math.random() * 1000) + 250);
            System.out.println(systemComponentName + " started in " + (System.currentTimeMillis() - startTime) + " ms");
            latch.countDown();
        }
    }
}
