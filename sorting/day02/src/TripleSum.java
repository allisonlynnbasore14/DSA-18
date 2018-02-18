import java.util.Arrays;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        // Plan:
        // sort array
        // set end pointer to the last element
        // set beginning poitner to the first element
        // For each entry inside the two pointers
        // check entry, entry + 1, and end pointer entry
        // if entry match sum, add to output list
        // if entry is too small, move left side pointer over one
        // if entry is too large, move right side pointer over one
        // reset pointers with each entry

        int total = 0;

        Arrays.sort(arr);
        int startIndex = 0;
        int endIndex = arr.length - 1;
        for (int j = startIndex; j<endIndex; j++){
            int startI = startIndex + 1;
            int endI = arr.length - 1;
            endIndex = arr.length - 1;
            for(int i = startI; i < endI; i++){
                if(endIndex < startIndex){
                    continue;
                }
                if(endI < startI){
                    break;
                }
                if(i == j|| i == endIndex){
                    continue;
                }

                if(endIndex< i){
                    continue;
                }
                int subTotal = sumUp(arr[j], arr[i], arr[endIndex]);
                if( subTotal == sum){
                    // it works
                    total++;
                    endIndex = arr.length - 1;
                }else if (subTotal > sum){
                    // it is too big
                    endIndex--;
                    i--;
                    //else
                    // it is too small
                    //i = startIndex;
                    //startIndex++;

                }
            }
            startIndex++;
        }

        return total;
    }

    static int sumUp(int a, int b, int c){
        return a + b + c;
    }

}
