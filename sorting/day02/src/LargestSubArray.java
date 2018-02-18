
import java.util.Collections;
import java.util.HashMap;


public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // Idea: go through the array
        // with the current elemtn check if it is a valid set with each of the vlalues after it
        // if it is a value set put a key value pari in the hash table that is index|# items included
        // find the largest one at end
        // return range

        HashMap hm = new HashMap();
        int maxVal = -1;
        int maxKey = 0;

        for(int i = 0; i < nums.length; i++){
            int sumValue = 0;
            sumValue = sumValue + getValue(nums[i]);
            for(int j = i+1 ; j < nums.length; j++){
                sumValue = sumValue + getValue(nums[j]);
                if(sumValue == 0){
                    int newValue = j-i+1;
                    if(newValue > maxVal){
                        maxVal = newValue;
                        maxKey = i;
                    }
                    hm.put(maxKey,maxVal);
                }
            }
        }

        for(Object i: hm.values()){
            System.out.println(i);
        }
        System.out.println(hm.values());
        return new int[]{maxKey, maxVal+maxKey -1};
    }


    public static int getValue(int b){
        if(b == 0){
            return -1;
        }else{
            return 1;
        }
    }

}
