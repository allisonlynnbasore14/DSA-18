public class LongestCommonSubsequence {

    // Runtime: O(length of first one*length of second one)
    // Space: O(length of first one * length of second one)
    public static int LCS(String S1, String S2) {
        char[] X=S1.toCharArray();
        char[] Y=S2.toCharArray();
        int m = X.length;
        int n = Y.length;
        int holder[][] = new int[m+1][n+1];
        for(int i = 0; i<=m;i++){
            for(int j = 0;j<=n;j++){
                holder[i][j] = -1;
            }
        }
        return longestCommon(X,Y,m,n,holder);
    }


    public static int longestCommon(char[] A, char[] B, int m, int n, int[][] holder){
        if(m ==0 || n == 0) { // base case
            return 0;
        }

        // checking if the memo has what we already found
        if(holder[m][n] != -1){
            return holder[m][n];
        }

        // otherwise if the last letters match, find the longest common substring between the previus two sub strings and add 1
        if(A[m-1]==B[n-1]){
            holder[m][n]=longestCommon(A,B,m-1,n-1,holder)+1;
            return holder[m][n];
        }else{
            //otherwise, just find the max of the previous X vs the previus Y
            holder[m][n] = max(longestCommon(A,B,m-1,n,holder),longestCommon(A,B,m,n-1,holder));
            return holder[m][n];
        }
    }

    public static int max(int a, int b)
    {
        return (a > b)? a : b;
    }

}


