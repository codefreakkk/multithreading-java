package ExecutorFrameworkJava;

import java.util.concurrent.ThreadFactory;

public class CustomThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setName("Thread " + t.getId());
        return t;
    }
}
