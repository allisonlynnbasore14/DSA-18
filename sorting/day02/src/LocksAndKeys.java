import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LocksAndKeys {


    // Why can you not sort them?

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        quickSort(locks, keys, 0, locks.length-1);
        return result;
    }


    // Quick Sort Stuff


    public static void quickSort(char[] a, char[] b, int lo, int hi) {
        if(lo == hi){
            return;
        }
        if (lo < hi) {
            int p1 = partition(a, lo, hi, b[hi]);
            int p2 = partition(b, lo, hi, a[p1]);
            quickSort(a, b, lo, p1-1);
            quickSort(a, b,p1+1, hi);
        }

    }

    public static int partition(char[] array1, int lo, int hi, char pivot) {
        // place all the elements less than A[lo] before it and return the new index of A[lo]
        //char piv = array1[lo];
        int pivIndex = lo;
        int count = 0;
        for(int i = lo; i < hi; i++ ){
            if(array1[i] < pivot){
                //char smallVal = array1[i];
                //char nextSlot = pivot;
                //for(int j = pivIndex; j< i;j++ ){
                //    char holder = array1[j+1];
                //    array1[j+1] = nextSlot;
                //    nextSlot = holder;
                //}
                char temp = array1[pivIndex];
                array1[pivIndex] = array1[i];
                array1[i] = temp;
                pivIndex ++;
                //array1[pivIndex] = smallVal;
                //pivIndex = count + lo;
            }else if(array1[i] == pivot){
                char temp = array1[i];
                array1[i] = array1[hi];
                array1[hi] = temp;
                i--;

            }
        }

        char newTemp = array1[pivIndex];
        array1[pivIndex] = array1[hi];
        array1[hi] = newTemp;
        return pivIndex;
    }


}




