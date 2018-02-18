import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class LocksAndKeys {


    // Need to look into the why of the times and such
    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }
    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // TODO: Return a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys

        //locks = sort(locks);
        char[][] result = new char[2][];
        result[0] = locks;
        result[1] = keys;
        quickSort(locks, keys, 0, locks.length-1);
        for(int i = 0; i < locks.length; i++){
            System.out.println(locks[i]);
            System.out.println(keys[i]);
            System.out.println("ATo");
        }
        return result;
    }


    // Quick Sort Stuff

    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            //swap(array, i, randIndex);
        }
    }

    //public static char[] sort(char[] array1, char[] array2) {
/*
        Random rnd = ThreadLocalRandom.current();
        for (int i = array.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            char a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
*/


        //return array;
    //}

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




