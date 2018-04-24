public class DiceRollSum {

    // Runtime: O(6*Sum)
    // Space: O(N)


    // the for loop makes it not store in memo every time but gathers and then returns

    public static int diceRollSum(int N) {
        int[] DP = new int[N + 1];
        for (int i = 0; i < DP.length; i++) {
            DP[i] = -1; // set a special empty value
        }

        int[] options = new int[6];
        for(int j=0;j<6;j++){
            options[j] = j+1;
        }

        int out = diceRollRecur(N, DP, options);


        return out;
    }

    public static int diceRollRecur(int i, int[] DP, int[] options){
        // base case
        if(i == 0){
            return 1;
        }
        if(i == 1){
            return 1;
        }

        if(DP[i] != -1){
            return DP[i];
        }
        int output = 0;
        if(i<=6){
            output = 1;
        }
        int val = 0;
        for(int j =1; j<7;j++){
            if(i>j){
                val = diceRollRecur(i-j,DP,options);
                int p = i-j;
                System.out.println("val = " + val);
                output = val +output;
            }

        }
        System.out.println("Brea = " );
        DP[i] = output;
        return output ;

    }

    public static void main(String args[]){
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(10));
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(9));
        System.out.println("diceRollRecur(9, DP, options) = " +diceRollSum(8));
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(7));
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(6));
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(5));
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(4));
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(3));
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(2));
        System.out.println("diceRollRecur(9, DP, options) = " + diceRollSum(1));
    }



}

//
///        // base case
//        if (i == 0) return 0;
//        // have we already solved this subproblem
//        if (DP[i] != -1) return DP[i];
//        // DP[i] = min(DP[j] + 1) for j in denominations
//        int answer = Integer.MAX_VALUE;
//        for (int j : denominations)
//        if (j <= i) answer = Math.min(coinsNeededRecurs(i - j, denominations, DP) + 1, answer);
//        // store our answer and return it
//        DP[i] = answer;
//        return answer;