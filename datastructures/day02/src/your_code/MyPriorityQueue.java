package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    // create a construcotr to initalize linked list

    // two cases
    // if empty aedd the item
    // other wise a while loop that checks for each element

    private LinkedList<Integer> ll;
    private int size;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
        size = 0;
    }


    public void enqueue(int item) {
        if(size == 0){
            ll.addFirst(item);
        }else{
            int i = size - 1;
            while(item > ll.get(i)){
                i --;
                if(i <= -1){
                    break;
                }
            }
            ll.add(i+1, item);

        }
        size ++;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        size --;
        return ll.removeFirst();
    }

}