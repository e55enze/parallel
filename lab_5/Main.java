package lab_5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static final int THREADS = 8;

    public static void main(String[] args) {
        MySemaphore semaphore = new MySemaphore(2);
        runTask(semaphore);
//        runTask(new Semaphore(2));
    }

    private static void runTask(Semaphore semaphore){
        ExecutorService es = Executors.newFixedThreadPool(THREADS);
        List<Callable<String>> tasks =  new ArrayList<>();
        List<Future<String>> results =  new ArrayList<>();

        for(int i = 0; i < THREADS; i++) {
            tasks.add(() -> {
                String threadName  = Thread.currentThread().getName();
//                    todo
                System.out.println("Before: Thread" + threadName +  " done");
                semaphore.acquire();
                Thread.sleep(1000);
                System.out.println("Thread" + threadName +  " done");
                semaphore.release();
                System.out.println("After: Thread" + threadName +  " done");
                return "Thread " + threadName + " done";
            });
        }
        try {
            results = es.invokeAll(tasks);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdown();
    }
}