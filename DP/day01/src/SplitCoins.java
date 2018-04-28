public class SplitCoins {

    // TIME: O(3*N) => O(N* S) s = sums

    public static int splitCoins(int[] coins) {

        // BOTTOM UP APPROACH

        // i = coin already places, j = left in pile

        // the goal is to get each array to sum to as close to total sum/2

        //finding sum and such
        int target = 0;
        for(int i = 0; i<coins.length;i++){
            target += coins[i];
        }
        int indivTarget = target/2;


        // making memo
        int[][] dpMemo = new int[coins.length+1][target +1];

        //doing the base case in one swoop, with each added array element, they can sum up and over 0
        for(int j = 0; j<coins.length+1;j++){
            dpMemo[j][0] = 1;
        }


        //otherwise, going through all possible sums, for each added element of the coins
        for(int a = 1; a<coins.length+1; a++){
            for(int b = 0; b<target +1;b++){
                if(b == 0 ){
                    dpMemo[a][0] = 1;
                }else {

                    // it equals whatever it got for the previous array entry
                    dpMemo[a][b] = dpMemo[a-1][b];
                    if(coins[a-1]<=b){
                        // if the previous isnt already satisfing the current sum, then use the previously memoed part to see if it should be a one
                        // if the current sum minus the previous array value at the previous array value is 1, then it should be 1 too
                        if(dpMemo[a][b]!=1){
                            dpMemo[a][b] = dpMemo[a-1][b-coins[a-1]];
                        }
                    }
                }

            }
        }

        int minVal = Integer.MAX_VALUE;
        for(int c  = indivTarget;c>=0;c--){
            // find the combination with the closest
            if(dpMemo[coins.length][c]==1){
                System.out.println("c = " + c);
                int totalSum = c+c;
                //sum of the two arrays
                minVal = target - totalSum;
                break;
            }
        }

        System.out.println("minVal = " + minVal);
        return minVal;
    }
}
