/* HashTableChained.java */

package dict;
import list.*;


/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  protected List[] buckets;
  protected int a = 2;
  protected int b = 3;
  protected int bigprime = 15485867;
  protected int entry_num;
  public int collision_num;



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/
 public static boolean isPrime(int i) {
    int divisor = 2;
    while (divisor * divisor <= i) {
      if (i % divisor == 0) return false;
      divisor++;
    }
    return true;
  }

  public HashTableChained(int sizeEstimate) {
    int temp = sizeEstimate;
    while(temp<=2*sizeEstimate){
      if(isPrime(temp))
        break;
      temp++;
    }
    buckets = new DList[temp];
    for(int i = 0; i<temp;i++){
      buckets[i] = new DList();
    }
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    this(101);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.

    int result = ((a*code+b)%bigprime)%buckets.length;
    if(result<0)
      return result+buckets.length;
    else
      return result;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return entry_num;
  }
  public int bucket(){
    return buckets.length;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return entry_num == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    int hash = compFunction(key.hashCode());
    Entry x = new Entry();
    x.key = key;
    x.value = value;
    if(buckets[hash].length() != 0){
      collision_num++;
    }
    buckets[hash].insertBack(x);
    entry_num++;
    return x;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    int hash = compFunction(key.hashCode());
    ListNode temp = buckets[hash].front();
    try{
      while(temp.next() != null){
        if(((Entry)temp.item()).key.equals(key)){
          return (Entry)(temp.item());
        }
        temp = temp.next();
      }
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int hash = compFunction(key.hashCode());
    ListNode temp = buckets[hash].front();
    try{
      while(temp.next()!=null){
        if(((Entry)temp.item()).key.equals(key)){
          Entry x = (Entry)temp.item();
          temp.remove();
          entry_num--;
          return x;
        }
        temp = temp.next();
      }
    }catch(InvalidNodeException e){
      System.out.println(e);
    }
    return null;

  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for(int i=0; i<buckets.length;i++){
      buckets[i] = new DList();
    }
    entry_num = 0;
  }

  public String toString(){
    String result = "";
    for(int i=0; i<buckets.length; i++){
      result += "[" + buckets[i].length() + "]";
    }
    return result;

  }

  public String number_of_collision(){
    String result = "";
    for(int i=0; i<buckets.length; i++){
      if(buckets[i].length()==0){
        result += "[0]";
      }
      else{
      result += "[" + (buckets[i].length()-1)+"]";
      }
    }
    return result;
  }



}