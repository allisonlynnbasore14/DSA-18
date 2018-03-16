package divide_and_conquer;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            //System.out.println(y);
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x]) {
                maxIndex = y;
            }
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        //O(log N)


        // Recursively run through the array,
        // use peakOne Day to see if you go right or left everytime you divide at the middle
        if(nums.length == 0){
            return -1;
        }

        return findOneDPeakRecur(nums, 0, nums.length);
    }

    public static int findOneDPeakRecur(int[] nums, int lo, int hi) {
        // Recursively run through the array,
        // use peakOne Day to see if you go right or left everytime you divide at the middle
        if(lo >= hi){
            return -1;
        }
        int mid = lo + (hi-lo)/2;
        int p = peakOneD(mid, nums);
        if(p < 0){
            return findOneDPeakRecur(nums, lo, mid-1);
        }else if(p == 0){
            return mid;
        }else{
            return findOneDPeakRecur(nums, mid+1, hi);
        }

    }

    public static int[] findTwoDPeak(int[][] nums) {
        //O(N) time, where N is the length of one dimension of the grid, assuming it is a square.
        int[] b = findTwoDPeakRecur(nums,0,nums[0].length, 0, nums.length);

        return b;
    }

    public static int[] findTwoDPeakRecur(int[][] nums, int clo, int chi, int rlo, int rhi){
        if(clo >= chi){
            return new int[]{};
        }
        System.out.println("4444444444");
        if(rlo >= rhi){
            return new int[]{};
        }
        int mid = clo + (chi-clo)/2;
        int m = maxYIndex(mid,rlo,rhi,nums);
        //System.out.println("ddd");
        int[] o = new int[nums[0].length];
        for(int i = 0; i< nums[0].length; i++){
            o[i] = nums[m][i];
        }
        int p = peakOneD(mid, o);

        // after finding which way to go in respect to columsnm, we need to find which way to go in respect to rows
        int midRow = rlo + (rhi-rlo)/2;
        int mRow = maxXIndex(midRow, clo,chi, nums);
        // FIX THIS HERE
        //System.out.println(mRow);
        int[] h = new int[nums.length];
        for(int i = 0; i< nums.length; i++){
            h[i] = nums[0][mRow];
            h[i] = nums[i][mRow];
        }
        int nn = 0;
        int ddd = nn+3;
        //System.out.println("ddd");
        int pRow = peakOneD(midRow, h);

        if(p < 0 && pRow < 0){
            return findTwoDPeakRecur(nums, clo, mid-1, rlo, midRow-1);
        }else if(p < 0 && pRow > 0){
            return findTwoDPeakRecur(nums, clo, mid-1, midRow+1, rhi);
        }else if(pRow == 0){
            System.out.println(midRow);
            System.out.print(mRow);
            return new int[]{midRow, mRow};
        }else if(p == 0){
            System.out.println(mid);
            System.out.print(m);
            return new int[]{mid,m};
        }else if(p>0 && pRow > 0){
            return findTwoDPeakRecur(nums, mid+1, chi, midRow+1, rhi);
        }else if(p>0 && pRow < 0){
            return findTwoDPeakRecur(nums, mid+1, chi, rlo, midRow-1);
        }
        System.out.println(mRow);
        System.out.print( nums.length -1);
        return new int[]{mRow, nums.length -1};
    }
/*
    public static int[] findTwoDPeakRecur(int[][] nums, int clo, int chi, int rlo, int rhi){

        // Check if there are no more rows, check if there are no more colums
        if(clo >= chi || rlo >= rhi){
            return new int[]{-1,-1};
        }


        // Divide the colums in half
        // divide the rows in half
        int midR = (rhi-rlo)/2;
        int midC = (chi-clo)/2;

        int maxR = maxXIndex(midR, rlo, rhi, nums);
//        int p = peakOneD(maxR, nums[][]);
//        if(p > 0){
//            mid = mid+1;
//        }else if(p == 0){
//            return mid;
//        }else{
//            mid = mid -1;
//        }

        int maxC = maxYIndex(midC, clo, chi, nums);

        // check if anything return 0 on both colum and row

        return new int[-1];
    }*/

}
