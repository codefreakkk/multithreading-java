package FutureJava;

import java.util.List;

public class Task1 implements Runnable {

    List<Integer> list;
    public Task1(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println("Adding element in list");
        list.add(10);
    }
}
