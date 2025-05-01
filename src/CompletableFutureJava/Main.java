package CompletableFutureJava;

import java.util.concurrent.*;

public class Main {

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
    }

}
