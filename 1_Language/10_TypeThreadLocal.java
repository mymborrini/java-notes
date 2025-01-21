
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class TypeThreadPools {
    
    
    
}

class FixedThreadPoolExample {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    
    public void resolve(){
        // ....
    }
    
}

/*
Every time a task is processed if no threads are free it generates a new one 
by default if a thread is not completed in 60 seconds this threadpool will kill
that thread 
*/
class CachedThreadPoolExample {
    ExecutorService executor = Executors.newCachedThreadPool();
    
    public void resolve(){
        // ....
    }
    
}


/*
A pool created to execute task after an amount of time or after a delaz from the completion 
of the prvious task
*/
class ScheduleThreadPoolExample {
    // ExecutorService executor = Executors.newScheduledThreadPool();
    
    public void resolve(){
        
        // task to run after 10 seconds delay
        
        // task to run repeately after 10 seconds
        
        // task to run repeately after 10 sceonds the previous task complete
        // ....
    }
    
}

/*
    the last one is the single thread task executor 
*/
