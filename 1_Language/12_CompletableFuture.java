
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;


public class CompletableFutureDemo
{
    /*
    A a second parameter of supplyAsync, thenApplyAsync you can specify your own threadpool executor
    otherwise thenApply will use the previous thread.
    */
  public static void resolve () throws Exception
  {
      ExecutorService cpuBound = Executors.newFixedThreadPool(4);
      ExecutorService ioBound = Executors.newCachedThreadPool();
      
      for (int i = 0; i < 10 ; i ++ ){
          AtomicInteger orderCount = new AtomicInteger(i);
           CompletableFuture.supplyAsync(() -> getOrder(orderCount.get()), ioBound)
                .thenApplyAsync(order -> enrichOrder(order), cpuBound)
                .thenApply(order -> performPayment(order))
                // .exceptionally(e -> orderFailed(e))
                .thenApply(order -> dispatch(order))
                .thenAccept(order -> sendEmail(order));
      } 
   

  }
  
  public static Order getOrder(Integer count){
      System.out.println("Getting order");
      Order order = new Order();
      order.id = 1;
      order.name = "order_" + count;
      order.customerId = 5;
      order.description = "test order";
      return order;
  }
  
  public static Order dispatch(Order order){
      System.out.println("Dispatch Order: " + order.name);
      return order;
  }
  
  public static  Order performPayment(Order order){
      System.out.println("Perform payment: " + order.name);
      return order;
  }
  
  public static Order enrichOrder(Order order){
      System.out.println("Enrich Order: " + order.name);
      return order;
  }
  
  public static void sendEmail(Order order){
      System.out.println("Sending email: " + order.name);
  }
  

  
  
}




class Order {
    Integer id;
    String name;
    Integer customerId;
    String description;
}