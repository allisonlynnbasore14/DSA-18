public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(n) + O(n+K) --> O(n+K), where k = the max value in the array
     *
     * Space: O(n)
     *
     * k: maximum element in array A
     *
     * use when k is close to n
     */
    static void countingSort(int[] A) {

        int max = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        int[] newArray = new int[max+1];
        for(int i = 0; i < A.length; i++){
            int index = A[i];
            newArray[index] = newArray[index] + 1;
        }
        int count = 0;
        for(int j = 0; j < newArray.length; j++){
            //if(newArray[j] != 0){
                // j is the index of the new array and the value that is kept
                for(int p = newArray[j]; p>0; p-- ){
                    A[count] = j;
                    count++;
                }
            //}
        }
    }

}
