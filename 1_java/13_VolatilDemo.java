
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class VolatileDemo {
    
    static ExecutorService asyncBound = Executors.newFixedThreadPool(10);
    
    
    private static class CreateFIle implements Callable<Integer> {
        
        @Override
        public Integer call() throws Exception {
            Thread.sleep(4900);
            Integer result = 10;
            return result;
            
        }
        
    }


    
    public static void resolve() throws Exception {
        Future<Integer> promise = asyncBound.submit(new CreateFIle());
        try {
            Integer result = promise.get(5, TimeUnit.SECONDS);
            System.out.println("I get the result: " + result);
        } catch(TimeoutException  ignored) {
            System.out.println("The execution is taking too long, it will be done in background");
            CompletableFuture
            .supplyAsync(() -> {
                try {
                    return saveInFileRepository(promise);
                } catch(Exception e) {
                    sendEmail(e.getMessage());
                    return null;
                }}, asyncBound)
            .thenAccept(result -> {
                if (result != null){
                    sendEmail("The result is " + result);
                }
                
                });
                
            System.out.println("Return running in background");
        }
        
    }
    
    
    public static Integer saveInFileRepository(Future<Integer> promise) throws Exception {
        System.out.println("Trying getting the result now for saving in file Repository");
        Integer result = promise.get();
        
        /*if (true){
            throw new RuntimeException("An Exception ");
        } */
        System.out.println("I get the result: " + result + " and save in File Repository");
        return result;
         
    }
    
    public static void sendEmail(String message){
        System.out.println("I'm sending an email: " + message);
    }
    
    
    
    

    
    
    
} 