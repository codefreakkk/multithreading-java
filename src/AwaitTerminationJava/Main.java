package AwaitTerminationJava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        executor.submit(() -> {
            try {
                System.out.println("Task 1 executing " + Thread.currentThread().getName());
                Thread.sleep(2000);
                System.out.println("Task 1 completed " + Thread.currentThread().getName());
            } catch (Exception e) {
                // handle exception
            }
        });

        executor.shutdown(); // stop accepting new task

        System.out.println("Main thread executing");
        try {
            boolean allTaskCompleted = executor.awaitTermination(5, TimeUnit.SECONDS);
            if (allTaskCompleted) {
                System.out.println("All Tasks COMPLETED in 5 SECONDS");
            } else {
                System.out.println("Timeout : Some task's not finished in 5 SECONDS");
            }
        } catch (InterruptedException e) {
            // handle exception
        }
        System.out.println("Main Thread completed");
    }
}
