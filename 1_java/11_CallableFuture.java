
import java.util.*;
import java.util.concurrent.*;


public class CallableFuture
{

  public static void resolve () throws Exception
  {
    ExecutorService executor = Executors.newFixedThreadPool (10);

    // Future is a placeholder of what maybe it comes in the future.
      List < Future < Integer >> promises = new ArrayList <> ();

    for (int i = 0; i < 20; i++)
      {
	promises.add (executor.submit (new Task ()));
      }
    // I have 100 placeholder with 100 Future


    // perform some unrelated operations
    for (int i = 0; i < 20; i++)
      {
	Future < Integer > promise = promises.get (i);
	// If you call get it means wait until you came up with a result.
	Integer result = promise.get ();

	/* 
	   try {
	   Integer result = promise.get(1, TimeUnits.SECOND). In this case you' re saying 
	   } catch(ExecuteExeption e) {}
	 */
	// wait until one second and if zou don't get the result raise an Exception.
	  System.out.println ("result of future# " + i + " = " + result);
      }

  }

}

class Task implements Callable < Integer >
{

  @Override public Integer call () throws Exception
  {
    Thread.sleep (3000);
    return new Random ().nextInt ();
  }

}
