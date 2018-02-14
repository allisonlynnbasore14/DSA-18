
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     *
     * Best-case runtime: O(N)
     * Worst-case runtime: O(N)^2
     * Average-case runtime: O(N)^2
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        // for each value in the array check if it is bigger than the previous
        // switch if it is
        // check until it isn't smaller than the next one
        if(array.length == 0 || array.length == 1){
            return array;
        }
        for ( int i = 1; i < array.length ; i++){
            int b = i;
            int c = i-1;
            while(c >= 0 && array[c] > array[b]) {
                array[c + 1] = array[c];
                c = c - 1;
            }
            array[c + 1] = array[b];
        }
        return array;

    }


}
