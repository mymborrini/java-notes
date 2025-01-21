
import java.util.*;
import java.util.concurrent.*;


public class IterablesInterfaces {
    
    public IterablesInterfaces(){
        
    }
    
    public void run(){
        
    }
    
}

/**
 * DIFFERENCE BETWEEN ARRAY AND ARRAYLIST 
 * 
 *  Array -> ArrayList
 * Array is static -> ArrayList is Dynamic, it can be resized when needed
 * An array can store both objects and primitive type -> We cannot store primitive types in ArrayList, it automatically inboxed into object.
 * Array generally is faster in performs.
 * We use for loop to iterate over an array -> We can use an iterator also to iterate over arraylist
 * Array can be multidimensional -> arraylist is always single-dimensional
 * Generics are not compatible with Arrays -> ArrayList allow the use of Generics.
 * 
 * 
 * DIFFERENCE BETWEEN ARRAYLIST AND LINKEDLIST 
 *  
 *  ArrayList  ->  LinkedList 
 * ArrayList internally use Dynamic array to store its elements -> LinkedList uses Double Linked List to store its elements
 * Manipulation with LinkedList is faster than ArrayList because it uses a doubly-linked list, so no bit shifiting is required in memory.
 * ArrayList is faster in storing and accessing data as internally Array is used which has random access -> LinkedList is slower than ArrayList in storing and accessing data as access requires node by node traversal
 * there is no descendingIterator in ArrayList -> LinkedList can be iterated in reverse direction using descendingIterator()
 * If the constructor is not overloaded, the ArrayList creates an empty list of initial capacity 10 -> There is no case of default capacity in a LinkedList, Hence an empty list is created when a LinkedList is initialized
 * 
 * In the end:
 * Hence if there is a requirement of frequesnt addition and deletion in application then LinkedList is a best choice if more search operations requirement, ArrayList would be your best bet.
 */ 


/**
 * EXPLAIN COLLECTION HIERARCHY
 * 
 * Collection -> is the root of the java collection framework and most of the collections in java inherited from this interface except Map interface
 * 
 * List, Queue, Set -> List: contains ordered elements, may include duplicates, supports the index based search, random access but elements can be easily inserted 
 *                           irrespectivity of the position
 *                     Set: doesn't define an order for the elements hence index-based search is not supporte, does not contain duplicates     
 *                     Queue: follows a FIFO principle (First in First out)
 * 
 * List interface:
 * ---------------
 *  - ArrayList: Dynamic resizing - 50% of original size | Non synchronized
 * 
 *  - LinkedList: implements List and Deque interfaces | maintains the insertion order | Not synchronized | does not support access elements randomly 
 * 
 *. - Vector : Is synchronized | maintains the insertion order | is Thread safe | Vector icreases its size by doubling the array size 
 *      - Stack : LIFO (Last in First out) | the elements are addedd as well as removed from the rear end | extends Vector
 * 
 * Set interface:
 * ---------------
 *  - HashSet: It implicity implements a hashtable | contains only unique elements | only one null element can be addedd | its unordered set 
 *  - LinkedHashSet: ordered version of HashSet which maintains a doubly-linked List across all elements | it preserves the insertion order  
 * 
 * SortedSet extends Set interface:
 * ---------------
 * All elements of a SortedSet must implement the Comparable interfaces | It's a set sorted in ascending order 
 *  - TreeSet: Uses a Tree for storage (seld balancing binary search tree like Red-Black Tree) | Objects in a TreeSet are stored in a sorted and ascending order
 * 
 * Queue:
 * ---------------
 *  - PriorityQueue: is a queue with priority associated with each element | high priority element is served before a low priority element irrespective of their insertion order
 * 
 * Deque extends Queue
 * ---------------
 * Deque refers to doubly-ended Queue | elements can added and removed from either end or start
 *  - ArrayDeQue: way to apply resizable-array in addition to the implement of Deque interface, no capacity restriction
 * 
 */

/**
 * EXPLAIN MAP HIERARCHY
 *
 * Map interface
 * ---------------
 *  - HashMap: Not synchronized nature | Allows only one null key but multipl
 *  - Hashtable: It is synchronized in nature | Doesn't allow any null key or value 
 * 
 * SortedMap extends Map
 * ---------------------
 * Entries are maintained in an ascending order 
 *  - TreeMap: implicity implements the Red-Black Tree Implementation | Cannot store any null key
 * 
 * 
 * WHY MAP DOESN'T EXTENDS COLLECTION INTERFACE.
 * 
 * - The map interface in Java follows a key/value pair structure whereas the Collection interface is a collection of Objects which are 
 *   stored in a structural manner with a specified access mechanism
 * - The main reason Map doesn't extend the collection interface is that the add (E e) method of the collection interface doesn't support the key-value pair lik Map interface
 *   put(K,V) method
 * -  It might not extend the collection interface but still is an integral part of the Java collection framework
 * 
 * 
 * DIFERENCE BETWEEN FAIL-FAST AND FAIL-SAFE ITERATORS
 *  - Fail-fast Iterators throws ConcurrentModificationExceptio when on Thread is iterating over collection object and another Thread structurally modify
 *   collection either by adding,removing on underlying collection. They are called fail-fast because they try to immediatly throw Exception when they encounter failure
 *  - Fail-safe iterator doesn't throw any exception if collection is modified structurally while one thread is iterating over it because they work on clone of collection 
 *   instead original collection and that's why they are called as Fail-safe iterator     
 * 
 * WHAT DO YOU UNDERESTAND BY BLOCKINGQUEUE?
 *  - Java BlockingQueue interface, represents a queue which is thread safe to put elements into and take elements out of from. In other words, mutliple threads can be inserting 
 *   and taking elements concurrently from a Java BlockingQueue, without any concurrency issue arising (It's like synchronized queue)
 * - It's capable of blocking the threads that try to insert ot take elements from the Queue.
 *   For instance, if a thread tries to take an element and there are none left 
 * 
 * DIFFERENCE BETWEEN SYNCHRONIZED COLLECTION AND CONCURRENT COLLECTION?
 *  - Both synchronized and concurrent collections classes provide thread-safety
 *  - The differences between them comes in performance, scalability and how they anchieve thread-safety
 *  - synchronized collections like synchronized HashMap are much slower than their concurrent conuterparts e.g. ConcurrentHashMap, Main reason for this slowness is locking.
 *  - Concurrent Collections introduced segments concept if we want to make a parallellism:
 *.    - In synchronized collection all the segmentS is locked by this thread and other threads have to wait in synchronized collection
 *.    - In concurrent colleection only 1 segment is locked for read or write while all other segments are open for other threads. -> it's called LOCK STRIPPING
 * 
 * INTERNAL WORKING OF HASH MAP 
 *  - HashMap in Java works on hashing principle where hash functions are used to link key and value in HashMap, Objects(Map.Entry -> contains both key and value object) are stored
 *   by calling put(key,value) method of HashMap and retrieving by calling get(key)
 *  - When we call put method, hashcode() method of the key object is called which calculates an index of the bucket location where we can store the value object.
 *  - To retrieve, you call the get() method and again pass the key obejct, which lands up at same index or bucket and you retrieve the value object
 *  - If two different keys returns same hash index then collision occurs, in this case a linked list is formed at that bucket location and a new entry is stored as next node 
 *  - Now put method will just append the objects nodes in the linked list
 *  - But in case of get if we have a linked list at that index then we need an extra check to search correct value, this is done by equals() method. It checks every key of every node 
 *    and if equals() returns true then Map return that corresponding value from the linked list 
 * 
 *  
 */ 
 
/**
  * 
  * WHICH SORTING IS USED BY COLECTIONS?
  * If elements are less than 7 Insertion Sort otherwise Merge Sort.
  * 
  */
   
/**
 * GROUPING COLLECTORS
 * Given a stream of objects, there are scenarios where these objects need to be grouped based on certain features they posses. 
 * Collectors.groupingBy takes two paramenters, the first one is a lambda which returns a property which will be the key of the Map. The second paramenters is a collectors in order 
 * to customize how these information is saved. So Collectors.toSet(), Collectors.toList() ecc...
 * 
 * So something like this:
 * stream().collect(Collectors.groupingBy( e -> e.property, Collectors.toSet())
 * 
 * 
 */ 