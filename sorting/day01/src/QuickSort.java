import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Arrays;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: O(N log N), becuase it goes through the same process no matter what
     * Worst-case runtime: O(N log N) (with randomization)
     * Average-case runtime: O(N log N)
     *
     * Space-complexity: O(N log N)
     * TODO: WHY THOUGH???
     */
    @Override
    public int[] sort(int[] array) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }

        quickSort(array, 0, array.length-1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if(lo == hi){
            return;
        }
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }

    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        // place all the elements less than A[lo] before it and return the new index of A[lo]
        int piv = array[lo];
        int pivIndex = lo;
        int count = 0;
        for(int i = lo; i <= hi; i++ ){
            if(array[i] < piv){
                int smallVal = array[i];
                int nextSlot = piv;
                for(int j = pivIndex; j< i;j++ ){
                    int holder = array[j+1];
                    array[j+1] = nextSlot;
                    nextSlot = holder;
                }
                count ++;
                array[pivIndex] = smallVal;
                pivIndex = count + lo;
            }
        }
        return pivIndex;
    }

}

