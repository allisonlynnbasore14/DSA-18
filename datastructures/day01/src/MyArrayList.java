

public class MyArrayList {
    //public static void main(String[] args) {
        //MyArrayList test = new MyArrayList();
        //Cow b = new Cow("Sam",20,"blue");
        //test.add(b);
        //Cow c = test.get(0);
        //System.out.println(test.size());
    //}

    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        // By default, setting the capacity to 10
        // If no capacity is specific it will default to this constructor
        elems = new Cow[10];
        size = 0;
    }

    // Runtime: O(1))
    public MyArrayList(int capacity) {
        // setting the capacity of the array
        // Have to typecast it because you cannot initialize an array using a type parameter
        elems = new Cow[capacity];

        size = 0;
    }

    // Runtime: O(1)
    public void add(Cow c) {

        if(size >= elems.length){
            grow();
        }
        elems[size] = c;

        // Using the size variable to keep track of what to place the cows in the array
        // Adding the c cow to the array
        size ++;
    }


    public void grow() {
        Cow[] newHolder = new Cow[elems.length * 2];
        System.arraycopy(elems, 0, newHolder,0, size);
        elems = newHolder;
    }

    public void shrink() {
        Cow[] newHolder = new Cow[elems.length / 2];
        System.arraycopy(elems, 0, newHolder,0, size);
        elems = newHolder;
    }

    // Runtime: O(1)
    public int size() {

        // Get the size variable and returns it
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {

        if(index >= size){
            throw new IndexOutOfBoundsException();
        }else{
            return elems[index];
        }


    }

    // Runtime: O(N)
    public Cow remove(int index) {
        Cow target = null;
        if(index >= size){

            throw new IndexOutOfBoundsException();
        }

        target = get(index);
        if(size < 0.25 * elems.length){
            shrink();
        }

        // shifting everything at the end over
        for (int i = index; i<size-1; i++){
            elems[i] = elems[i+1];
        }
        // taking one off the size variable
        size --;
        // returning the Cow
        return target;
    }

    // Runtime: O(N)
    public void add(int index, Cow c) {
        if(size >= elems.length){
            grow();
        }
        if(index > size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = size-1; i>index-1; i--){
            elems[i+1] = elems[i];

        }
        elems[index] = c;
        // Using the size variable to keep track of what to place the cows in the array
        // Adding the c cow to the array
        size ++;

    }
}


