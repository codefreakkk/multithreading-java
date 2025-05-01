package FutureJava;


import java.util.ArrayList;
import java.util.List;
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

        // type 1
        Future<?> futureObj1 = executor.submit(() -> {
            try {
                System.out.println("Task 1 is running");
                Thread.sleep(5000);
                System.out.println("Task 1 completed");
            } catch (Exception e) {
                // handle exception
            }
        });

        try {
            System.out.println("Main thread running " + Thread.currentThread().getName());
            Object object = futureObj1.get(); // blocking call
            System.out.println(object == null); // this will print true as Runnable will return nothing
        } catch (Exception e) {
            // handle exception
        }
        System.out.println("Main thread is running " + Thread.currentThread().getName()); // this will not be printed until above thread completes execution

        // type 2
        List<Integer> list = new ArrayList<>();
        Future<List<Integer>> futureObj2 = executor.submit(new Task1(list), list);

        try {
            List<Integer> outputList1 = futureObj2.get();
            System.out.println("Element from list = " + outputList1.get(0));
        } catch (Exception e) {
            // handle exception
        }

        // type 3 (Callable)
        Future<List<Integer>> futureObj3 = executor.submit(() -> {
            System.out.println("Task 3 Callable");
            return List.of(20);
        });

        try {
            List<Integer> outputList2 = futureObj3.get();
            System.out.println("Element from list = " + outputList2.get(0));
        } catch (Exception e) {
            // handle exception
        }
    }
}
