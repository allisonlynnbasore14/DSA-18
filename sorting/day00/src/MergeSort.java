import static java.util.Arrays.copyOfRange;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     *
     * Best-case runtime: O(N)
     * Worst-case runtime: O(N log N)
     * Average-case runtime:
     *
     * Space-complexity: O(N)
     */
    @Override
    public int[] sort(int[] array) {
        int n = array.length;
        if(n == 1){
            return array;
        }
        if(n == 0){
            return array;
        }
        int half = (int) n/2;
        int[] left;
        int[] right;

        left = copyOfRange(array, 0, half);
        right = copyOfRange(array, half, n);

        left = sort(left);
        right = sort(right);

        return merge(left, right);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int n = a.length + b.length;
        int[] m = new int[n];
        int pointA = 0;
        int pointB = 0;
        for(int i = 0; i < n; i++){
            if(pointA == a.length){
                m[i] = b[pointB];
                pointB ++;
                continue;
            }
            if(pointB == b.length){
                m[i] = a[pointA];
                pointA ++;
                continue;
            }
            else if(a[pointA] < b[pointB]){
                m[i] = a[pointA];
                pointA ++;
            }else{
                m[i] = b[pointB];
                pointB ++;
            }
        }
        return m;
    }

}
