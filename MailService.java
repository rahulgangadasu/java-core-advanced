package java;

import java.util.concurrent.CompletableFuture;

public class MailService {
    public void sendMail(){
        LongTask.simulate();
        System.out.println("Mail was sent.");
    }
    
    public CompletableFuture<Void> sendAsync(){
        return CompletableFuture.runAsync(() -> sendMail());
    }
}
