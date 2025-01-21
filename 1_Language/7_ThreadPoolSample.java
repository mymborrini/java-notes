/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main
{

  public static void main (String[]args) throws Exception
  {
        VolatileDemo.resolve ();
  }


}




// THREAD POOL EXAMPLE
class ThreadPoolExample
{

  private ExecutorService threadPool = Executors.newFixedThreadPool (10);
  
  private SimpleDateFormat df = new SimpleDateFormat ("yyyy-mm-dd");

  public void resolve () throws Exception
  {
    for (int i = 0; i < 1000; i++)
      {
	 AtomicInteger days = new AtomicInteger (i);
	  threadPool.submit (()->
			     {
			     String birthDay = birthDay (days.get());
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
    

    return df.format (birthDay);
  }
}


class SimpleThreadExample
{

  public void resolve () throws Exception
  {
    new Thread (()->
		{
		String birthDay = birthDay (100);
		System.out.println (birthDay);}).start ();

    new Thread (()->
		{
		String birthDay = birthDay (200);
		System.out.println (birthDay);}).start ();

    Thread.sleep (1000);
  }

  public String birthDay (int daysFromToday)
  {
    Date currentDay = new Date ();

    Calendar calendar = Calendar.getInstance ();
    calendar.setTime (currentDay);
    calendar.add (Calendar.DATE, daysFromToday);

    Date birthDay = calendar.getTime ();
    SimpleDateFormat df = new SimpleDateFormat ("yyyy-mm-dd");

    return df.format (birthDay);
  }

}
