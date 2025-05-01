package ExecutorFrameworkJava;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        // create thread pool
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                4, 5,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                new CustomThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy()
        );

        for (int i = 0; i < 7; i++) {

            // submit tasks
            executor.submit(() -> {
               try {
                   System.out.println("Executed by - " + Thread.currentThread().getName());
                   Thread.sleep(4000);
               } catch (Exception e) {
                   // handle exception
                   System.out.println("Error - " + e.getMessage());
               }
            });
        }
    }
}
