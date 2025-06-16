/******************************************************************************

ThreadLocal is used for memory efficient and thread safety since you don't need 
synchronized anything between threads.
By doing this SimpleDateFormat class is used by all threads, the main problem with this approach 
is that this is not thread safe, since all the thread use the same instace .

1 So if you put a lock of SimpleDateFormat this will slow down performance too much
2 If you create a SimpleDateFormat for task this will be to expensive in memory 
3 If you simply create a GLobal object this will not be thread safe.

One way to resolve this is to have variables specific for singular thread 
using  ThreadLocal class 
This concept of having variables specific for a particular Thread is called ThreadLocal.
Since all this thread can access a single SimpleDateFormat per thread this method is Thread-safe

*******************************************************************************/

import java.util.*;
import java.util.concurrent.*;
import java.text.SimpleDateFormat;
import java.util.concurrent.atomic.AtomicInteger;

class TheadLocalExample
{

  private ExecutorService threadPool = Executors.newFixedThreadPool (10);


  public void resolve () throws Exception
  {
    for (int i = 0; i < 1000; i++)
      {
	AtomicInteger days = new AtomicInteger (i);
	  threadPool.submit (()->
			     {
			     String birthDay = birthDay (days.get ());
			     System.out.println (birthDay);
			     });

      }
    Thread.sleep (1000);
  }

  public String birthDay (int daysFromToday)
  {
    Date currentDay = new Date ();

    Calendar calendar = Calendar.getInstance ();
    calendar.setTime (currentDay);
    calendar.add (Calendar.DATE, daysFromToday);

    Date birthDay = calendar.getTime ();

    // Each thread will get its own copy
    final SimpleDateFormat df = ThreadSafeFormatter.dateFormatter_2.get ();

    return df.format (birthDay);
  }
}


class ThreadSafeFormatter
{

  // With java 8
  public static ThreadLocal < SimpleDateFormat > dateFormatter_2 =
    ThreadLocal.withInitial (()->new SimpleDateFormat ("yyyy-mm-dd"));

  // With java < 8
  public static ThreadLocal < SimpleDateFormat > dateFormatter =
    new ThreadLocal < SimpleDateFormat > (){

    /*
       Called once for each thread 
     */
    @Override protected SimpleDateFormat initialValue (){
							 return new
							 SimpleDateFormat
							 ("yyyy-mm-dd");
							 }

							 /*
							    1st call = initialValue()
							    Subsequent calls will return same initialized value
							  */
							 @Override
							 public
							 SimpleDateFormat
							 get ()
							 {
							 return super.get ();
							 }

							 };
							 }
