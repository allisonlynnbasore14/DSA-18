

public class MyArrayList {
    public static void main(String[] args) {
        MyArrayList test = new MyArrayList();
        System.out.println(test.size());
    }

    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        // By default, setting the capacity to 10
        // If no capacity is specific it will default to this constructor
        elems = (Cow[]) new Object[10];
        size = 0;
    }

    // Runtime: O(1))
    public MyArrayList(int capacity) {
        // setting the capacity of the array
        // Have to typecast it because you cannot initialize an array using a type parameter
        elems = (Cow[]) new Object[capacity];
        size = 0;
    }

    // Runtime: O(1)
    public void add(Cow c) {
        elems[size] = c;
        // Using the size variable to keep track of what to place the cows in the array
        // Adding the c cow to the array
        size ++;
    }

    // Runtime: O(1)
    public int size() {
        // Get the size variable and returns it
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        try{
            return elems[index];
        } catch(IndexOutOfBoundsException error){
            throw new IndexOutOfBoundsException();
        }

    }

    // Runtime: O(N)
    public Cow remove(int index) {
        Cow target = null;
        try{
            // finding
            target = get(index);
        } catch(IndexOutOfBoundsException error){
            // Trowing an error if the index is out of bounds
            throw new IndexOutOfBoundsException();
        }
        // shifting everything at the end over
        for (int i = index; i<size; i++){
            elems[i] = elems[i+1];
        }
        // taking one off the size variable
        size --;
        // returning the Cow
        return target;
    }

    // Runtime: O(N)
    public void add(int index, Cow c) {
        if(index == elems.length) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = size; i>index; i--){
            elems[i+1] = elems[i];
        }
        try{
            elems[index] = c;
        } catch(IndexOutOfBoundsException error){
            throw new IndexOutOfBoundsException();
        }
        // Using the size variable to keep track of what to place the cows in the array
        // Adding the c cow to the array
        size ++;
    }
}


