package ShutdownNotJava;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // submit task1 for execution
        executor.submit(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("Task 1 completed");
            } catch (Exception e) {
                // handle exception
                System.out.println("Task 1 was interrupted");
            }
        });

        // submit task2 for execution
        executor.submit(() -> System.out.println("Task 2 completed"));

        List<Runnable> tasks = executor.shutdownNow();
        System.out.println("Number of unfinished tasks are = " + tasks.size());
    }
}
