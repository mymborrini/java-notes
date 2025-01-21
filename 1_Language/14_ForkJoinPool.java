
import java.util.*;
import java.util.concurrent.*;

/******************************************************************************

ForkJoinPool is really similar to ExecutorService
Its different because Task produce its own sub-task (fork join)
And this could be done recursively so sub-task produce sub-task as well and so on.

Another diference is that here every thread has its own deque in order to store the sub-tasks
And if a thread finish all the task from the mainQueue and its deque, but another thread has 
a lot of tasks in its deque, then the first thread whih has already finish them can "steal"
the task from the deque of the busy thread.

You have to be sure:
Avoid synchronized
Avoid shared variables
Don't perform blocking IO operations
Are pure functions
Are isolateds

It can be use:
Sorting
Matrix multiplication
Best move finder for a game 
Tree traversal

*******************************************************************************/
public class ForkJoinPoolDemo {
    
    public String solve(Task task){
        // split t into smaller sub-task
        
        // foreach of these sub-task -> solve(sub-task)
        
        // wait for all tasks to complete
        
        // join all individual results
        
        // return results
        new Fibonacci(56);
    }
    
    private static class Fibonacci extends RecursiveTask<Integer> {
        
        final int n;
        Fibonacci(int n){
            this.n = n;
        }
        
        public Integer compute(){
            if (n <= 1) return n;
            
            Fibonacci f1 = new Fibonacci(n-1);
            f1.fork();
            Fibonacci f2 = new Fibonacci(n-2);
            f2.fork();
            
            return f2.join() + f1.join();
        }
        
    }
    
    
    
}

class Task {
    String name;
}