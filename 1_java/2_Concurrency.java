
import java.util.*;
import java.util.concurrent.*;

public class Concurrency {
    
    public Concurrency(){
        
    }
    
    public void run(){
        new ConcurrentHashMapExample().differentThreadConcurrent();
    }
    
}


/**
 * WHAT IS THE NEED OF CONCURRENT COLLECTION ?
 * 1 Tranditional collections are not thread safe. Only few classes like Vector, HashTable, are thread safe
 * 2 Collections provide some methods like synchronizedList, synchronizedMap, synchronizedSet, those provide thread
 * safety but problem is they capture lock on complete collection even for reading, that decreases performance
 * 3 In traditional collection if one thread iterates and another tries to modify structural change then ConcurrentModificationException is thrown
 */
class ConcurrentHashMapExample {
    
    
    public void ConcurrentModificationExceptionExample(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()){
            Integer key = it.next();
            System.out.println("Map value: " + map.get(key) );
            if (key.equals(2)){
                // This is a structural change (the map becomes of side of 4)
                // In this case java will raise a ConcurrentModificationException like this
                /**
                 *  Exception in thread "main" java.util.ConcurrentModificationException
                    at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1493)
                    at java.base/java.util.HashMap$KeyIterator.next(HashMap.java:1516)
                    at ConcurrentHashMapExample.run(ConcurrentIQ.java:33)
                    at ConcurrentIQ.run(ConcurrentIQ.java:18)
                    at Main.main(Main.java:26)
                 */
                map.put(4,4);
            }
        }
    }
    
     public void run(){
        /**
         * A ConcurrentHashMap helps me in this case becaouse allows me to create structural change 
         * while iterating over the map
         */
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        
        Iterator<Integer> it = map.keySet().iterator();
        while (it.hasNext()){
            Integer key = it.next();
            System.out.println("Map value: " + map.get(key) );
            if (key.equals(2)){
                // This is a structural change (the map becomes of side of 4)
                map.put(4,4);
            }
        }
    }
    
    /**
     * The same example of before but with threads
     */
    public void differentThreadConcurrent(){
         
        Map<Integer, Integer> map = new Hashtable<>();
        map.put(2, 2);
        map.put(3,3);
        map.put(5,5);
        
        new Thread (()->{
             System.out.println("Map iterator thread start");
             Iterator<Integer> it = map.keySet().iterator();
             while(it.hasNext()){
                 try {
	                System.out.println("Map iterator thread sleep");
	                Thread.sleep(2000);
	            } catch(Exception e) {
	            }
	            System.out.println(map.get(it.next()));
             }
	        

		    
		}).start();

        new Thread (()->
    		{
    		 try {
    		   System.out.println("Map putKey thread sleep");  
    	       Thread.sleep(3000);
    	            } catch(Exception e) {
    	            }
    	   System.out.println("Map putKey thread start");
    		map.put(3,6);
         }).start();
     }
}



/**
 * GIVE FEW CONCURRENT COLLECTIONS CLASSES?
 *  1 ConcurrentHashMap
 *  2 CopyOnWriteArrayList
 *  3 CopyOnWriteArraySet
 * 
 * WHY PERFORMANCE OF CONCURRENTHASHMAP IS BETTER THAN HASHTABLE AND synchronizedMap?
 * In HashTable and synchronizedMap lock is acquired on complete collection so only single thread can capture lock at a time while in ConcurrentHashMap lock is acquired on
 * entry level so at a time multiple thread can capture lock on different entry.
 * 
 * WHAT IS CONCURRENCY LEVEL IN CONCURRENTHASHMAP?
 * Concurrency level defines how many thread are allowed to get lock. As by default ConcurrentHashMap has 16 bucket and concurrency level also has 16 value. It means 16 thread 
 * can take lock at a time each per bucket. You can configure this as well, so if you select a concurrency level of 8, only 8 thread can access the map at the same time.
 * That define the concept of segment. If the concurrency level is 16 than each segment has it's own bucket, If the concurrency level is 8 each segment has 2 buckets.
 * 
 *     S0       S1      S2    
 *  ________  ______. ______
 * ------------------------------------------------------------------------------
 * |    |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 * ------------------------------------------------------------------------------
 * 
 * CAN MULTIPLE THREADS READ FROM CONCURRENTHASHMAP SIMULTANEOUSLY?
 * Read doesn't need any lock, So no segment with lock will be created.
 * 
 * CAN MULTIPLE THREADS READ AND WRITE FROM CONCURRENTHASHMAP SIMULTANEOUSLY?
 * No, write operation needs lock. If thread 1 (T1) and thread 2 (T2) are accessing the first segment because T1 wants to write in 
 * B0 and T2 wants to read from B1. T2 has to wait for S0 to release the lock before reading the value
 * 
 *     S0       S1      S2    
 *  ________  ______. ______
 * ------------------------------------------------------------------------------
 *  | B0 | B1 |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |   |
 * ------------------------------------------------------------------------------
 *  T1    T2    
 * 
 * WHY NULL NOT ALLOWED IN CONCURRENTHASHMAP WHILE ALLOWED IN HASHMAP?
 * Suppose the following code:
 * 
    if (concurrentHM.containsKey(k)){
        return concurrentHM.get(k);
    }
    
    Suppose we have two threads T1 and T2. T1 runs "if condition" and checks if key is present in ConcurrentHashMap, it goes to fetch
    value but in the mean tume second thread t2 deleted the key from HM.
    
    So now T1 gets the value as null.
    Now he thinks value stored for key k is null or there is no key (whether the key excplicitly maps to null vs the key isn't mapped),but that's not the case here.
    Hence to remove ambiguity, null is not allowed.
    
    
 */
 
 class MapNullValuePossible {
     
    public void HashMapNullValue(){
         
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        map.put(2, 2);
        
        new Thread (()->{
             System.out.println("Map containsKey thread start");
             
             
	        if (map.containsKey(2)){
	            try {
	                System.out.println("Map containsKey thread sleep");
	                Thread.sleep(5000);
	            } catch(Exception e) {
	            }
	            
	            System.out.println(map.get(2));
	        }
		    
		}).start();

        new Thread (()->
    		{
    		 try {
    		   System.out.println("Map removeKey thread sleep");  
    	       Thread.sleep(3000);
    	            } catch(Exception e) {
    	            }
    	   System.out.println("Map removeKey thread remove");
    		map.remove(2);
         }).start();
     }
     
}

/**
 * INTERNAL WORKING OF CONCURRNET HASHMAP?
 * 
 * As no concurrency level has been set excplicitly, the ConcurrentHashMap gets divided into 16 segments. And each segment acts as independent HashMap. During write 
 * operation the Lock is obtained on this particular segment and not on the entire HashMap.
 * 
 * Hence it has multiple Segment level locks. it uses a Locking technique name REENTRANTLOCK.
 * 
 * 1 So 2 Threads operating on different segments can acquire lock on those segments without interferring with each other and can proceed simultaneously as they are working on 
 *   separate segment locks.
 * 
 * 2 Read/Get Operation :- Two Threads T1 nd T2 can read data from the same or different segment of ConcurrentHashMap at the same time without blocking each other.
 * 
 * 3 Write/Put Operation :- Two Threads T1 and T2 can write data on different segment at the same time without blocking the other.
 *   But two threads can't write data on the same segments at the same time. One has to wait for other to complete operation.
 * 
 * 4 Read-Write Operation :- Two threads can read and write data on different segments at the same time without blocking each other.
 * 
 * PUT:
 * Based on segment index, HashEntry<K,V> (node) is placed in particular segment then Based on the hashIndex, HashEntry<K,V>(node) is placed in array inside the segment which 
 * is similarly like how nodes are placed inside the bucket in HashMap
 * 
 * GET:
 * Based on segment index, we figure out particular segment then Based on the hashindex, we will get values from the array inside the segment which is similarly like how we 
 * will get values from the array inside the segment which is similarly like how we will get values from the bucket in HashMap
 * 
 */
 
 
 /**
  * INTERNAL WORKING OF COPYONWRITEARRAYLIST 
  * 
  * It is thread sage version of List and are kept in concurrent package.
  * 
  * For every write operation a separate clone copy is created and JVM will synchronize the clone and actual copy.
  * 
  * We get java.util.ConcurrentModificationException as soon as the ArrayList is modified.
  * It happens because ArrayList iterator is fail-fast by design. What it means is that once the iterator is created, if the ArrayList is modified,
  * it throws a ConcurrentModificationException.
  * 
  */
  
class ConcurrentArrayList {
    
    public void ConcurrentModificationExceptionExample(){
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        
        Iterator<Integer> it = l.iterator();
        while (it.hasNext()){
            Integer key = it.next();
            System.out.println("List value: " + key );
            if (key.equals(2)){
                // This is a structural change (the list becomes of side of 4)
                // In this case java will raise a ConcurrentModificationException like this
                /**
                 * Exception in thread "main" java.util.ConcurrentModificationException
                    at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1042)
                    at java.base/java.util.ArrayList$Itr.next(ArrayList.java:996)
                    at ConcurrentArrayList.run(ConcurrentIQ.java:259)
                    at ConcurrentIQ.run(ConcurrentIQ.java:12)
                    at Main.main(Main.java:26)
                             */
                l.add(4);
            }
        }
    }
    
     public void ConcurrentModificationWithCopyOnWriteArrayList(){
        /**
         * A CopyOnWriteArrayList helps me in this case becaouse allows me to create structural change 
         * while iterating over the map. In this case since it creates a clone of the array when writing (there are no segments or anything else)
         * The iterator object is not changed so the first cycle will not display the 4 number. The second cycle yes.
         */
        List<Integer> l = new CopyOnWriteArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        
        Iterator<Integer> it = l.iterator();
        System.out.println("First cycle");
        while (it.hasNext()){
            Integer key = it.next();
            System.out.println("List value: " + key );
            if (key.equals(2)){
                System.out.println("Adding 4");
                it.remove();
            }
        }
        
        
        it = l.iterator();
        System.out.println("Second cycle");
        while (it.hasNext()){
            Integer key = it.next();
            System.out.println("List value: " + key );
        }
    }
    
    /**
     * The same example of before but with threads. The Result is the one expected with
     * ArrayList it will raise a .ConcurrentModificationException
     * With CopyOnWriteArrayList the iterator will not show the 4 number
     */
    public void run(){
         
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(2);
        l.add(3);
        
        new Thread (()->{
             System.out.println("List iterator thread start");
             Iterator<Integer> it = l.iterator();
             while(it.hasNext()){
                 try {
	                System.out.println("list iterator thread sleep");
	                Thread.sleep(2000);
	            } catch(Exception e) {
	            }
	            System.out.println(it.next());
             }
	        

		    
		}).start();

        new Thread (()->
    		{
    		 try {
    		   System.out.println("list add thread sleep");  
    	       Thread.sleep(3000);
    	            } catch(Exception e) {
    	            }
    	   System.out.println("list add thread start");
    		l.add(4);
         }).start();
     }
    
}

/**
 * 
 * CAN CONCCURRENT MODIFICATION EXCEPTION OCCURS ONLY IN MULTITHREAD ENVIRONMENT?
 *  
 * NO, ConcurrentModificationException can also come in a single-threaded environment.
 * 
 * On looping onver a list for loop and try to remove one lelement, you will get ConcurrentModificationException.
 * You broke the rule of not modifying a Collection during iteration.
 * 
 * How does java know to throw ConcurrentModificationException? It uses a transient variable called modCount, which keeps track 
 * of how many times a list is modified structurally. structural modification are those that change the size of the list, which may 
 * affect the progress of iteration and may yield incorrect results.
 * 
 * Both iterator and Listiterator use this field to detect unxpected change. Other methods of List which structurally modify List also use this field
 * add, remove ecc...
 * 
 * Cause: The real cause of ConcurrentModificationException is inconsisted modCount. When you are iterating over ArrayList then Iterator's next() method 
 * keep track of modCount. If you modify the collection by adding or removing elelements then modCount will change and it will not match with the 
 * expected modCount, hence iterator will throw ConcurrentModificationException.
 * 
 * Solution: use concurrent collection classes e.g. CopyOnWriteArrayList to remove elelements while you are looping over it, this will work both on single-threaded and multi-threaded
 * 
 * 
 * WHAT IS MODCOUNT IN ARRAYLIST?
 * Here modCount is the Arraylist variable that holds the modifiction count and every time we use add,remove it increments.
 * ExpectedModCount is the iterator variable that is initialised when we create iterator with the same value as modCount. This 
 * explains why we don't get exception if we use set method to replace any existing element.
 * 
 * So basically iterator throws ConcurrentModificationException if list size is changed.
 * This modCount is used to initialize ExpectedModCount for that iterator instance.
 * 
 */ 
 
 /**
  * 
  * HASHMAP -> CONCURRENTHASHMAP
  * HashMap is thread unsafe -> ConcurrentHashMap is thread safe
  * Performance is high -> Performance is low
  * HashMap can conain null key -> ConcurrentHashMap cannot contain null key
  * 
  * It's fail fast, if one thread is iterating map and another thread tries to make STRUCTURAL CHANGES (if it changes a value no exception will raise) 
  * then we get ConcurrentModificationException -> 
  * 
  * ConcurrentHashMap if fail safe, if one thread is iterating map then another can update the same map
  * 
  * ARRAYLIST -> CopyOnWriteArrayList
  * Arraylist is thread unsafe -> CopyOnWriteArrayList is thread safe
  * Performance is heigh -> Performance is low
  * 
  * It is fail fast, if one thread is iterating Arraylist and another One
  * tries to do structural change then we get ConcurrentModificationException ->
  * 
  * CopyOnWriteArrayList is fail safe, if one thread is iterating List and another tries to update List
  * then new clonse will be created and later JVM will synchronize the clone and the actual copy.
  * 
  * HASTABLE -> CONCURRENTHASHMAP 
  * Concurrent hash map applies locks only at bucket level called fragment while adding or updating the map. 
  * So, a concurrent hash map allows concurrent read and write operations to the map. 
  * ->
  * Hashtable It is a base implementation of Map interface. It doesn't allow null keys and values. 
  * It is synchronized in nature so two different threads can’t access simultaneously. Hashtable does not maintain any order.
  * (It does not contain any complex Lock logic, simply put,get,remove are synchronized)
  * 
  */ 
  
/**
 * WHAT IS MULTI THREADING?
 *  - It means executing several tasks simultaneously where each task is separate independent part of the same program (called Thread).
 *  - For instance, Junit uses Thread to run test cases in parallel. As an application, you can have computer   
 */ 
  