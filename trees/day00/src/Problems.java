import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        Collections.sort(values);
        BinarySearchTree<Integer> output = minimalHeightRecurr(values, new BinarySearchTree<Integer>());
        return output;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
    public static BinarySearchTree<Integer> minimalHeightRecurr(List<Integer> values, BinarySearchTree<Integer> output) {

        // Base case

        if(values.size() <= 1 ){
            if(values.size() == 0){
                return output;
            }
            output.add(values.get(0));
            return output;
        }

        int mid = 0;
        mid = values.size()/2 -1;

        // add middle
        output.add(mid-1);
        // cut in half
        output = minimalHeightRecurr(values.subList(0,mid),output);
        // call on each half


        if(mid <= values.size()-1){
            output = minimalHeightRecurr(values.subList(mid,values.size()-1),output);
        }else{
            for(int i = 0; i < values.size(); i++){
                if(values.get(i) != mid){
                    output.add(values.get(i));
                }
            }
        }
        return output;
    }
}
