/******************************************************************************

ThreadLocal is also used to ensure context of a particular Thread.
Spring use a lot of ThreadLocal in many of its classes

LocalContextHolder
TransactionContextHolder
RequestContextHolder -> Holder class to expose the web request in the form of a thread-bound RequestAttributes object.
SecurityContextHolder -> Associates a SecurityContext with the currentExecution thread 
DatetimeContextHolder

At the end of the request we must sure to clean the object otherwise the next request 
could contains pieces of the previous request.

*******************************************************************************/
public class WebServerThreadLocal
{

  public static void resolve ()
  {
    new Service1 ().process ();
    new Service2 ().process ();
  }
}

class UserContextHolder
{
  public static ThreadLocal < User > holder = new ThreadLocal ();
}

class Service1
{
  public void process ()
  {
    // Get user from web request
    User user = new User ();
      user.username = "mattia";
      UserContextHolder.holder.set (user);
  }
}


class Service2
{
  public void process ()
  {
    User user = UserContextHolder.holder.get ();
      System.out.println ("In Service2 the user has username: " +
			  user.username);
    // process user;
  }
}

class Service3
{
  public void process ()
  {

    // the last service must clean up;
    UserContextHolder.holder.remove();
  }
}

class User
{
  public String username;



}
