public class DungeonGame {

    // Rurntime: O(N)

    public static int minInitialHealth(int[][] map) {
        int[][] dp = new int[map.length][map[0].length];
        for(int i = 0; i<map.length;i++){
            for(int j = 0; j<map[0].length;j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        return findMinHeath(map,dp,0,0);
    }


    public static int findMinHeath(int[][] map, int[][] dp, int i, int j){


        if(i==map.length-1 && j==map[0].length-1){

            // base case for when it gets to the end
            if(map[i][j]*-1 <0){
                dp[i][j] = 0;
                return dp[i][j];
            }
            dp[i][j] = (map[i][j]*-1) +1;
            return dp[i][j];
        }


        if(i==map.length-1){
            int val = findMinHeath(map, dp, i, j+1) +(map[i][j]*-1);
            if(val<0){
                dp[i][j] = 0;
            }else{
                dp[i][j] = val;
            }
            return dp[i][j];
        }
        if(j==map[0].length-1){
            //dp[i][j] =  dp[i+1][j];
            int val =findMinHeath(map, dp, i+1, j)+(map[i][j]*-1);
            if(val<0){
                dp[i][j] = 0;
            }else{
                dp[i][j] = val;
            }
            return dp[i][j];
        }

        if(dp[i][j]!= Integer.MAX_VALUE){
            return dp[i][j];
        }

        int v = min(findMinHeath(map,dp,i+1,j),findMinHeath(map,dp,i,j+1))+(map[i][j]*-1);
        if(v<0){
            dp[i][j] = 0;
        }else{
            dp[i][j] = v;
        }

        return dp[i][j];
    }

    public static int min(int a, int b)
    {
        return (a < b)? a : b;
    }

}
