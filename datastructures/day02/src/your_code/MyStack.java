package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {
    // Queue and Stack are for when you know you only want that functionality and that you do not want more funcitonality than you need

    private LinkedList<Integer> ll;
    private LinkedList<Integer> llmax;
    private int maxVal;


    public MyStack() {
        ll = new LinkedList<>();
        llmax = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        if(llmax.isEmpty() || e > maxVal){
            maxVal = e;
        }
        ll.addFirst(e);
        llmax.addFirst(maxVal);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        llmax.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return llmax.peek();
    }
}
