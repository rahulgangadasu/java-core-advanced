package java_code;

import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class BestPriceFinder {
    public static void main(String[] args) {
        var start = LocalTime.now();
        var service = new FlightService();
        var futures = service.getQuotes()
               .map(future -> future.thenAccept(System.out :: println))
               .collect(Collectors.toList());
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                         .thenRun(() ->{
                                            var end = LocalTime.now();
                                            Duration duration = Duration.between(start, end);
                                            System.out.println("Retrived all quotes in : " + 
                                                                duration.toMillis() + " msec.");
                                       });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        

        // var service = new FlightService();
        // service.getQuote("site1")
        //        .thenAccept(System.out::println);
    }
}
