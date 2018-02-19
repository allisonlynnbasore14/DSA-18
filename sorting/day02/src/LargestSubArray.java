
import java.util.Collections;
import java.util.HashMap;


public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // Idea: go through the array
        // with the current element check if it is a valid set with each of the values after it
        // if it is a value set put a key value pair in the hash table that is index|# items included
        // find the largest one at end
        // return range

        // The problem is that it is n+n-1
        // right now I am not using the hashmap


        // maximum

        HashMap hm = new HashMap();

/*        for(int i = 0; i < nums.length; i++){
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
                    hm.put(maxVal, maxKey);
                }
            }
        }*/

        int sum = 0;
        int maxDistance = 0;
        int maxDistanceIndexStart = -1;
        int maxDistanceIndexEnd = -1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == 0){
                sum--;
                // put into hash map the distances | index
                if(hm.get(sum) == null){
                    hm.put(sum, i);
                }else{
                    int curr = (int) hm.get(sum);
                    hm.put(sum, i);
                    int newDiff = i-curr;
                    if(newDiff > maxDistance){
                        maxDistance = newDiff;
                        maxDistanceIndexStart = curr;
                        maxDistanceIndexEnd = i;
                    }
                }
            }else{
                sum++;
                // put into hash map the distances | index
                if(hm.get(sum) == null){
                    hm.put(sum, i);
                }else{
                    int curr = (int) hm.get(sum);
                    hm.put(sum, i);
                    int newDiff = i-curr;
                    if(newDiff > maxDistance){
                        maxDistance = newDiff;
                        maxDistanceIndexStart = curr;
                        maxDistanceIndexEnd = i;
                    }
                }
            }
        }


/*        for(Object i: hm.keySet()){
            //System.out.println(i);
        }

        System.out.println(hm.KeySet());

        for(Object i: hm.values()){
            //System.out.println(i);
        }*/

        return new int[]{maxDistanceIndexStart, maxDistanceIndexEnd};
    }


    public static int getValue(int b){
        if(b == 0){
            return -1;
        }else{
            return 1;
        }
    }

}
