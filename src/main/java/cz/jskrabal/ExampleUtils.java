package cz.jskrabal;

/**
 * Created by Jan Skrabal skrabalja@gmail.com
 */
public class ExampleUtils {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
