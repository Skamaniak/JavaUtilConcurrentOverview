package cz.jskrabal.example;

import cz.jskrabal.ExampleUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by Jan Skrabal skrabalja@gmail.com
 */
public class CompletableFutureExample {
    public static void main(String[] args) {

        CompletableFuture.supplyAsync(() -> "results.txt")
                .thenApplyAsync(CompletableFutureExample::readFileContent)
                .thenApplyAsync(CompletableFutureExample::parseFileContent)
                .thenApplyAsync(CompletableFutureExample::sum)
                .thenAcceptAsync(result -> System.out.println("Result: " + result))
                .exceptionally(e -> {
                    System.out.println("Something went wrong " + e.getMessage());
                    return null; //Void
                });

        ExampleUtils.sleep(1000);
    }

    private static String readFileContent(String fileName) {
        System.out.println("Thread " + Thread.currentThread().getName() + " - reading file " + fileName);
        InputStream is = CompletableFutureExample.class.getClassLoader().getResourceAsStream(fileName);
        try {
            return IOUtils.toString(is, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Can't read file " + fileName, e);
        }
    }

    private static Collection<Integer> parseFileContent(String content) {
        System.out.println("Thread " + Thread.currentThread().getName() + " - parsing file content");
        List<String> lines = Arrays.asList(content.split("\\r?\\n"));

        return lines.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private static int sum(Collection<Integer> ints) {
        System.out.println("Thread " + Thread.currentThread().getName() + " - summing up results");

        return ints.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
