/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 10000000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
    LinkedQueue result = new LinkedQueue();
    try{
        while(!q.isEmpty()){
        LinkedQueue tmp = new LinkedQueue();
        tmp.enqueue(q.front());
        result.enqueue(tmp);
        q.dequeue();
        }
    } catch(QueueEmptyException s){
      System.out.println(s);
    }
    return result;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    LinkedQueue result = new LinkedQueue();
    //Merge while q1 and q2 are not empty
    try{
      while((!q1.isEmpty()) && (!q2.isEmpty())){
        //int tmp = ((Comparable)(q1.front()).compareTo((Comparable)(q2.front())));
        int tmp = ((Comparable)(q1.front())).compareTo(((Comparable)(q2.front())));
        if(tmp>0){
          result.enqueue(q2.front());
          q2.dequeue();
        } else if(tmp<0){
          result.enqueue(q1.front());
          q1.dequeue();
        } else{
          result.enqueue(q1.front());
          result.enqueue(q1.front());
          q1.dequeue(); q2.dequeue();
        }
      }
      //If q1 or q2 is empty
      if(q1.isEmpty()){
        while(!q2.isEmpty()){
          result.enqueue(q2.front());
          q2.dequeue();
        }
      } else{
        while(!q1.isEmpty()){
          result.enqueue(q1.front());
          q1.dequeue();
        }
      }
    } catch(QueueEmptyException s){
      System.out.println(s);
    }
    return result;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    try{
      while(!qIn.isEmpty()){
        Object obj = qIn.front();
        int tmp = pivot.compareTo((Comparable)obj);
        if(tmp>0){
          qSmall.enqueue(obj);
        } else if(tmp<0){
          qLarge.enqueue(obj);
        } else{
          qEquals.enqueue(obj);
        }
        qIn.dequeue();
      }
    } catch(QueueEmptyException s){
      System.out.println(s);
    } 
  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    try{
      LinkedQueue tmp = makeQueueOfQueues(q);
      while(tmp.size()>1){
        LinkedQueue q1 = (LinkedQueue)tmp.front(); tmp.dequeue();
        LinkedQueue q2 = (LinkedQueue)tmp.front(); tmp.dequeue();   //not stable, since if the smallest item in q1,q2 are equal, 
                                                                    //then they will be both added to the result.
        LinkedQueue queue = mergeSortedQueues(q1,q2);
        tmp.enqueue(queue);
      }
      q.append((LinkedQueue)tmp.front());
    } catch(QueueEmptyException s){
      System.out.println(s);
    }
  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    LinkedQueue qsmall = new LinkedQueue();
    LinkedQueue qequal = new LinkedQueue();
    LinkedQueue qlarge = new LinkedQueue();
    int random = (int)(q.size() * Math.random());
    int pivot = (int)q.nth(random);
    partition(q,(Comparable)pivot,qsmall,qequal,qlarge); // stable, since qequal is used to store the node with the same key.
    if(qsmall.size()>1){
      quickSort(qsmall);
    }
    if(qlarge.size()>1){
      quickSort(qlarge);
    }
    q.append(qsmall);
    q.append(qequal);
    q.append(qlarge);
  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {
    // LinkedQueue s1 = new LinkedQueue();
    // LinkedQueue s2 = new LinkedQueue();
    // s1.enqueue(1); s1.enqueue(3); s1.enqueue(5);
    // s2.enqueue(2); s2.enqueue(4); s2.enqueue(6); s1.enqueue(8);
    // LinkedQueue tmp = mergeSortedQueues(s1,s2);
    // System.out.println("Prepare for it, sucker!" + tmp);
    // System.out.println("s1 should be empty:"+s1.isEmpty());
    // System.out.println("s2 should be empty:"+s2.isEmpty());

    // LinkedQueue p1 = new LinkedQueue();
    // LinkedQueue psmall = new LinkedQueue(); LinkedQueue pequal = new LinkedQueue(); LinkedQueue plarge = new LinkedQueue();
    // p1.enqueue(1); p1.enqueue(2); p1.enqueue(3);p1.enqueue(3); p1.enqueue(4); p1.enqueue(5);
    // partition(p1,3,psmall,pequal,plarge);
    // System.out.println(p1);
    // System.out.println(psmall);
    // System.out.println(pequal);
    // System.out.println(plarge);
    System.out.println("Mergesort!");
    LinkedQueue q = makeRandom(10);
    System.out.println(q.toString());
    mergeSort(q);
    System.out.println(q.toString());
    System.out.println("Special case for Mergesort!");
    LinkedQueue t = new LinkedQueue();
    System.out.println(t);
    t.enqueue(3);
    System.out.println(t);
    System.out.println("quickSort!");
    q = makeRandom(10);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    System.out.println("Special case for quickSort!");
    LinkedQueue n = new LinkedQueue();
    n.enqueue(5);
    System.out.println(n);

    System.out.println("Part 3");
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    
  }

}
