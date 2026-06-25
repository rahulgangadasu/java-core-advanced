package java_code;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
public class CompletableFutureDemo {
    public static double toFahrenheit(int celsius){
        return (celsius * 1.8 + 32);
    }
    public static CompletableFuture <String> getUserEmailAsync(){
        return CompletableFuture.supplyAsync(() -> "email");
    }
    public static CompletableFuture <String> getUserPlaylistAsync(String email){
        return CompletableFuture.supplyAsync(() -> "playlist");
    }
    public static void main(String[] args) {
        
        Runnable task = () -> System.out.println("rahul");
        var result = CompletableFuture.runAsync(task); // runAsync is overloaded as (Runnable, Executor), 
                                         //by default it uses ForkJoinPool.commonPool() 
        System.out.println(result);
        Supplier<Integer> task2 = () -> 1;
        var result2 = CompletableFuture.supplyAsync(task2);
        try {
            System.out.println(result2.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        /*-------------------Asynchronous API----------------------------- */
        var service = new MailService();
        service.sendAsync();
        System.out.println("Hello World!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------------------------------");
        /*-------------------------Running code on completion------------------------------ */
        var future = CompletableFuture.supplyAsync(() -> 1);
        future.thenRun(() ->  //runs on main thread.
                            {
                                System.out.println(Thread.currentThread().getName());
                                System.out.println("Running..");
                            });
        System.out.println("--------------------------------------------");
        future.thenRunAsync(() ->  //runs on commonpool.
                            {
                                System.out.println(Thread.currentThread().getName());
                                System.out.println("Running..");
                            });
       future.thenAcceptAsync(result3 -> 
                                        {
                                            System.out.println(Thread.currentThread().getName());
                                            System.out.println(result3);
                                        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("-----------------------------------");

        /*---------------------------Transforming completable future--------------------- */

        var future3 = CompletableFuture.supplyAsync(() -> 19);
        future3.thenApply(CompletableFutureDemo :: toFahrenheit)
               .thenAccept(System.out :: println);

        // general implementation.
        // try {
        //     var future4 = future3.thenApply(t -> t*1.8 + 32).get();
        //     System.out.println("Temperature in Fahrenheit : "+ future4);
        // } catch (InterruptedException | ExecutionException e) {
        //     e.printStackTrace();
        // }
        System.out.println("-----------------------------");

        /*----------------------composing completable future----------------------- */
        getUserEmailAsync()
        .thenCompose(CompletableFutureDemo :: getUserPlaylistAsync)
        .thenAccept(System.out::println);
        // General composing of completablefuture.
        // CompletableFuture.supplyAsync(() -> "email")
        // .thenCompose(email -> CompletableFuture.supplyAsync(() -> "playlist"))
        // .thenAccept(System.out :: println);

        System.out.println("-----------------------------");

        var first = CompletableFuture.supplyAsync(() -> "36USD")
                    .thenApply(str -> {
                        var price = str.replace("USD", "");
                        return Integer.parseInt(price);
                    });
        var second = CompletableFuture.supplyAsync(() -> 1.6);
        first.thenCombine(second, (price, exchangeRate)-> price * exchangeRate)
             .thenAccept(System.out::println);
        System.out.println("-----------------------------");
        /*-----------------------------------waiting for many tasks----------------------- */
        var x = CompletableFuture.supplyAsync(() -> 1);
        var y = CompletableFuture.supplyAsync(() -> 2);
        var z = CompletableFuture.supplyAsync(() -> 3);

        var all = CompletableFuture.allOf(x, y, z);
        all.thenRun(()-> {
            try {
                var first1 = x.get();
                System.out.println(first1);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            
        });
        System.out.println("All tasks are completed.");
        System.out.println("------------------------------");
        /*------------------------------Any of the provides Tasks---------------- */

        var firstTask = CompletableFuture.supplyAsync(() -> {
                                                                LongTask.simulate();
                                                                return 28;
                                                            });
        var secondTask = CompletableFuture.supplyAsync(() -> 18);

        CompletableFuture.anyOf(firstTask, secondTask)
                         .thenAccept(System.out::println);
    }
}
