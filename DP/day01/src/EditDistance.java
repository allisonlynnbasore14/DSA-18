public class EditDistance {

    //O(length1 * length2)

    public static int minEditDist(String a, String b) {
        // the idea: compare the last letter of each string, if they match return self, if they do not match, find the min of the inserting, of deleting, or of swaping

        int[][] dp  = new int[a.length()+1][b.length()+1];
        for(int i = 0; i< a.length()+1;i++){
            for(int j = 0; j<b.length()+1;j++){
                dp[i][j] = -1;
            }
        }

        return findMinEdits(a, b, dp, a.length(),b.length());
    }

    public static int findMinEdits(String a, String b, int[][] dp, int i, int j){

        // base case is if either of the strings are empty, return all of the other strings added



        if(i==0){
            return j;
        }
        if(j==0){
            return i;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        // if it is not a base case, or in the memo, then check for if the last letter match
        if(a.charAt(i-1) == b.charAt(j-1)){
            dp[i][j] = findMinEdits(a, b, dp, i-1, j-1);
            return dp[i][j];
        }

        // otherwise find the min of the three operations
        dp[i][j] = 1+ min(findMinEdits(a,b,dp,i-1,j),findMinEdits(a,b,dp,i,j-1),findMinEdits(a,b,dp,i-1,j-1));

        return dp[i][j];
    }

    public static int min(int a, int b, int c){
        int out = a;
        if(b<out){
            out = b;
        }
        if(c<out){
            out = c;
        }
        return out;
    }
}
