import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        if(inputStream.length < 1){
            return null;
        }
        // divide in half
        // make one max and one min pq
        // make mech for redingin median
        PriorityQueue<Integer> firstHalf = maxPQ();
        PriorityQueue<Integer> secondHalf = minPQ();


        if(inputStream.length % 2 != 0){
            // it is odd
            for(int i = 0; i <= (int) (inputStream.length/2 - 1.0); i ++){
                firstHalf.offer(inputStream[i]);
            }
            for(int i = (int) (inputStream.length/2 - 1.0); i < inputStream.length; i ++){
                secondHalf.offer(inputStream[i]);
            }
        }else{
            // it is even
            for(int i = 0; i <= (int) (inputStream.length/2 ); i ++){
                firstHalf.offer(inputStream[i]);
            }
            for(int i = (int) (inputStream.length/2); i < inputStream.length; i ++){
                secondHalf.offer(inputStream[i]);
            }
        }

        // this need to be a for loop that colelcts them alll and returns them
        if(firstHalf.size() > secondHalf.size()){
            return firstHalf.peek();
        }
        return (firstHalf.peek() + secondHalf.peek())/2;
    }

}
