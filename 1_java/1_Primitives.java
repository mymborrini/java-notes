/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
import java.util.*;
import java.util.stream.*;
import java.io.*;
import java.text.*;

public class Primitives
{
	public static void main(String[] args) throws Exception {
	    
	    //List<Integer> in1 = List.of(77,9,8,7,4,5,6,12,14,54,65,43);
	    //List<Integer> in2 = List.of(77,9,8,7,5,6,12,1);
	    //int[] in1 = {77,9,8,7,4,5,6,12,14,54,65,43};
	    //int[] res = new MergedSort().sort(in1);
	    //System.out.println(Arrays.toString(res));
	    //Map<String, Integer> m1 = Map.of("first", 1, "second", 3, "third", 2, "fourth", -1);
	    //RemoveAllOccurrencesFromString.distictCharAndCount("Hello my darling");
	    //new PyramidPattern().printPattern5(9);
	    new GeneralIQ().run();
	   
	}
	
	public static void foo(Object o) {
		System.out.println("Object argument");
	}
	public static void foo(String s) {
		System.out.println("String argument");
	}
}

class ReverseString {
    
    public static void firstWay(String str){
       String res = new StringBuilder(str).reverse().toString();
       System.out.println(res);
        
    }
    
    public static void secondWay(String str){
        StringBuilder sb = new StringBuilder();
        char[] charList = str.toCharArray();
        
        
        for (int i = str.length() - 1 ; i >=0 ; i--){
            sb.append(charList[i]);
        }
        
        System.out.println(sb.toString());
        
    }
    
}

// Without using a third variables
class SwapIntegers {
    
    public static void swapFirstWay(int first, int second){
        first = first + second;
        second = first - second;
        first = first - second;
        System.out.println(first);
        System.out.println(second);
    }
    
}


class CheckVowels {
    
    public static void checkFirstWay(String in){
        boolean res = in.toLowerCase().matches(".*[aeiou].*");
        System.out.println(res);
    }
    
}

class PrimeNumberCheck {
    
    
    public static void checFirstWay(int in){
        boolean res = true;
        if (in == 0 || in == 1) {
            res = true;
            System.out.println(res);
            return;
        }
        
        
        if (in == 2){
            res = false;
            System.out.println(res);
            return;
        }
        
        
        for (int i = 2; i <= in/2 ; i++){
            if (in % i == 0){
                res = false;
                break;
            }
        }

        System.out.println(res);
    }
    
}


class FibonacciSeries {
    
    
    public static void fibonacciFromCounter(int n){
        
        int res = fibonacci(n);
        System.out.println(res);
    }
    
    private static int fibonacci(int n){
        if (n <= 1){
            return n;
        }
        
       return fibonacci(n - 1) + fibonacci(n - 2);
        
    }
    
}

class OddListNumbers {
    
    public static void checkOnlyOdd(Collection<Integer> numbers){
        
        boolean res = numbers.stream().noneMatch(n -> n % 2 == 0);
        System.out.println(res);
        
    }
    
}


class Palindrome {
    
    public static void isPalindrome(String in){
        
        String reverse = new StringBuilder(in).reverse().toString();
        
        if (in.equals(reverse)){
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        
    }
    
    public static void isPalindrome(Integer in){
        String straight = String.valueOf(in);
        String reverse = new StringBuilder(straight).reverse().toString();
        
        if (straight.equals(reverse)){
             System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
    
    public static void isPalindromeSecondWay(String in){
        boolean res = true;
        
        int stringSize = in.length();
        for (int i = 0; i <= stringSize / 2; i ++){
            if (in.charAt(i) != in.charAt(stringSize - 1 - i)){
                res = false;
            }
        }
        
        System.out.println(res);
        
    }
    
}

class RemoveWitheSpaces {
    
    public static void removeAll(String in){
        String res = in.replaceAll("\\s+","");
        System.out.println(res);
    }  
    
    public static void removeLeadAndTrail(String in){
        String res = in.replaceAll("^\\s+", "").replaceAll("\\s+$", "");
        System.out.println(res);
    }
    
}

class SortArray {
    
    public static void sortFirstWay(int[] in){
        
        Arrays.sort(in);
        System.out.println(Arrays.toString(in));
    }
    
}

class Deadlock {
    
    // Todo In the future to refresh basic concurrency problem
}


class Factorial {
    
    
    public static void factorialFirstWay(int in){
       System.out.println(String.valueOf(factor(in)));
    }
    
    private static int factor(int in){
         if (in == 0 || in == 1){
            return 1;
        }
        
        return in * factor(in - 1);
    }
}


class LinkedListImp {
    
    /*
        LinkedList<Integer> ll = new LinkedList();
	    ll.add(1);
	    ll.add(5);
	    ll.add(6);
    */
    public static void reverseFirstWay(LinkedList in) {
        Collections.reverse(in);
        System.out.println(in);
    }
    
    public static void reverseSecondWay(LinkedList in){
        LinkedList out = new LinkedList();
        in.descendingIterator().forEachRemaining(out::add);
        System.out.println(out);
    }
    
}


class BinarySearch {
    
    public static void searchFirstWay(int[] arr, int key){
        Arrays.sort(arr);
        System.out.println(String.valueOf(search(arr,0, arr.length - 1, key)));
    }
    
    private static int search(int[] arr, int low, int high, int key){
        
        while (low < high){
            int mid = (low + high) / 2;
            if (arr[mid] == key){
                return mid;
            }
            
            if (arr[mid] < key){
                low++;
            } else {
                high--;
            }
        }
        return -1;
        
    }
    
}


class TwoArraySameElement {
    
    public static void compute(Integer[] in1, Integer[] in2){
        
        Set<Integer> s1 = new HashSet(Arrays.asList(in1));
        Set<Integer> s2 = new HashSet(Arrays.asList(in2));
        
        if (!(s1.size() == s2.size())){
            System.out.println(false);
            return;
        }
        
        boolean res = s1.stream().noneMatch(e1 -> !s2.contains(e1));
        System.out.println(res);
        
    }
    
}

class SumAllElementInArray {
    
    public static void sum(Integer[] in){
        
        int res = Arrays.asList(in).stream().reduce(0, (currentInteger, accumulator) -> accumulator + currentInteger);
        System.out.println(res);
        
    }
    
    
    
}

class SecondLargestNumberInArray {
    
    public static void compute(Integer[] in) {
        Arrays.sort(in);
        Integer res = in[in.length - 2];
        System.out.println(res);
    }
    
}


class ShuffleArray {
    
    public static void compute(Integer[] in){
        Random rand = new Random();
        
        for (int i = 0; i < in.length - 1; i++){
            int randIndex = rand.nextInt(in.length - 1);
            Integer tmp = in[randIndex];
            in[randIndex] = in[i];
            in[i] = tmp;
        }
        
        System.out.println(Arrays.toString(in));
        
    }
    
}


class StringInATextFile {
    
    public static void find(String val) throws FileNotFoundException {
        String filePath = "";
        File file = new File(filePath);
        
        Scanner scan = new Scanner(file);
        
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            if (line.contains(val)){
                System.out.println(true);
                scan.close();
                return;
            }
        }
        
        System.out.println(false);
        
    }
    
}

class PrintDateInSpecificFormat {
    
    public static void print(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(date);
        System.out.println(dateStr);
    }
    
}

class MergeTwoList {
    
    public static void merge(List<Integer> in1, List<Integer> in2){
        List<Integer> mergedList = new ArrayList<>();
        int i = 0;
        for (; i < in1.size() && i < in2.size(); i++){
            mergedList.add(in1.get(i));
            mergedList.add(in2.get(i));
        } 
        
        if (i < in1.size() - 1){
            mergedList.addAll(in1.subList(i, in1.size()));
        } else if (i < in2.size() - 1){
            mergedList.addAll(in2.subList(i, in2.size()));
        }
        
        System.out.println(mergedList);
        
    }
    
}

class SortHashMapByValues {
    
    public static void sort(Map<String, Integer> in){
        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        
        List<Map.Entry<String,Integer>> sortedList = new ArrayList<>(in.entrySet());
        
        sortedList.sort((x,y) -> x.getValue().compareTo(y.getValue()));
        
        for (Map.Entry<String, Integer> entry: sortedList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        System.out.println(sortedMap);
        
    }
    
}

class RemoveAllOccurrencesFromString {
    
    public static void remove(String content, String key){
        String purgedContent = content.replaceAll(key, "");
        System.out.println(purgedContent);
    }
    
    public static void distictCharAndCount(String content){
        char[] charArr = content.toCharArray();
        Map<Character, Integer> res = new HashMap<>();
        for (char c : charArr){
            if (res.containsKey(c)){
                Integer v = res.get(c);
                res.put(c, v + 1 );
            } else {
                res.put(c, 1);
            }
            
        }
        
        System.out.println(res);
        
    }
    
}


class PyramidPattern {
    
    public void printPattern1(int rows){
        
        for (int count = 1; count <= rows; count++){
            print(" " , rows - count);
            print(count + " ", count);
            System.out.println("");
        }
        
    }
    
    public void printPattern2(int rows){
        
        for (int count = 1; count <= rows ; count++ ){
            print(" ", rows - count);
            for (int j = 1; j <= count ; j++ ) {
                System.out.print(j + " ");
            }
            System.out.println("");
        } 
        
    }
    
    public void printPattern3(int rows){
        
        for (int i = 1; i <= rows ; i++ ){
            print(" ",(rows-i)*2);
            
            for (int j = 1; j < i; j++){
                System.out.print(j + " ");
            }
            
            for (int j = i; j >= 1; j--){
                System.out.print(j + " ");
            }
            
            System.out.println("");
            
        } 
        
    }
    
    public void printPattern4(int rows){
        
        for (int i = rows; i >=1 ; i-- ){
            print(" ", (i - 1) * 2);
            
            for (int j = i; j < rows ; j++ ){
                System.out.print(j + " ");
            }
            
            for (int j = rows; j >= i; j--){
                System.out.print(j + " ");
            }
            System.out.println("");
        } 
        
    }
    
    public void printPattern5(int rows){
        
        for (int i = rows; i >= 1 ; i -- ){
            print(" ", rows - i + 1);
            print(i + " ", i);
            System.out.println("");
        } 
        
    }
    
    private void print(String c, int count){
        for (int i =0;i < count ; i++ ){
            System.out.print(c);
        } 
    }
    
}

class MergedSort {
    

    
    public int[] sort(int[] in1){
        if (in1.length == 1 || in1.length == 0){
            return in1;
        }
        
        return merge(sort(Arrays.copyOfRange(in1, 0, in1.length /2)), sort(Arrays.copyOfRange(in1, in1.length/2, in1.length)));
    }
    
    private int[] merge(int[] in1, int[] in2){
        
        int[] mergedArray = new int[in1.length + in2.length];
        int k = 0;
        int i = 0;
        int j = 0;
        while (i < in1.length && j < in2.length){
            
            if (in1[i] > in2[j]){
                mergedArray[k] = in2[j];
                k++;
                j++;
            } else if (in1[i] < in2[j]){
                mergedArray[k] = in1[i];
                k++;
                i++;
            } else {
                mergedArray[k++] = in1[i];
                mergedArray[k++] = in2[j];
                i++;
                j++;
            }
            
        }
        
        while (i < in1.length){
            mergedArray[k++] = in1[i++];
        }
        
        while (j < in2.length){
            mergedArray[k++] = in2[j++];
        }
        return mergedArray;
    }
    
}