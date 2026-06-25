package java_code;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ExecutorsDemo {
    public static void main(String[] args) {
        var executor = Executors.newFixedThreadPool(10);
        try {
            var future = executor.submit(() -> {
                LongTask.simulate();
                return 1;
            });
            System.out.println("Do more work."); 
            try {
                var result = future.get();
                System.out.println(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
         finally {
            executor.shutdown();
        }
    }
}
