package CompletableFutureJava;

import java.util.concurrent.*;

public class Main {

    static CompletableFuture<String> getUserId() {
        return CompletableFuture.completedFuture("123");
    }

    static CompletableFuture<String> getProfile(String userId) {
        return CompletableFuture.completedFuture("Profile of " + userId);
    }

    public static void main(String[] args) {

        // create thread pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                4,
                6, 5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        // thenApplyAsync
        CompletableFuture<String> asyncTask1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Thread name = " + Thread.currentThread().getName());
            return "Harsh";
        }, executor).thenApplyAsync((String val) -> {
            System.out.println("Thread name = " + Thread.currentThread().getName());
            return val + " Said";
        }, executor);

        try {
            String name = asyncTask1.get(); // non-blocking call
            System.out.println("Full Name = " + name);
        } catch (Exception e) {
            // handle exception
        }

        // thenCompose
        CompletableFuture<String> asyncTask2 = getUserId()
                .thenComposeAsync((String val) -> getProfile(val));

        try {
            String name = asyncTask2.get();
            System.out.println("Compose Async Task Name = " + name);
        } catch (Exception e) {
            // handle exception
        }

        // thenCombine
        CompletableFuture<String> output = asyncTask1.thenCombineAsync(asyncTask2, (result1, result2) -> result2 + " " + result1);
        try {
            String name = output.get();
            System.out.println("Combined name is = " + name); // Profile of 123 Harsh Said
        } catch (Exception e) {
            // handle exception
        }
    }
}
