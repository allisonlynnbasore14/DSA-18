import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


// I AM JUST THREE OFF ON TEST 6 and 1 off on test 7

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // Find the smallestr gap, cover that group
        // find next smallest gap, either extend a current board or make new board
        // repeat
        // always be checking if the number of boards left is equal to the number of groups
        ArrayList<Integer> occupied2 = new ArrayList<Integer>();
        Arrays.sort(occupied);
        for(int i =0;i<occupied.length;i++){
            occupied2.add(occupied[i]);
        }


        //int output= barnRepairRecur(M, occupied2,  0, 0);
        int spaces = findNumofSpaces(occupied2);
        int output2 = barnRepairRecurConden(M, occupied2, 0, 0, spaces, new ArrayList<Integer>());
        //System.out.println("output = " + output);
        System.out.println("output2 = " + output2);
        return output2;
    }

    public static int findNumofSpaces(ArrayList<Integer> occup){
        if(occup.size() == 0){
            return 0;
        }
        int spaces = 0;
        int preVal = occup.get(0);
        for(int i = 1; i< occup.size(); i++){
            if(preVal+1 != occup.get(i)){

                spaces++;
            }
            preVal = occup.get(i);
        }
        return spaces;
    }

    public static int barnRepairRecurConden(int M, ArrayList<Integer> occupied, int numGroups, int numSpacesCovered, int spaces, ArrayList<Integer> noMergeIndexes){
        if(spaces == 0){
            numGroups++;
            numSpacesCovered = numSpacesCovered+occupied.size();
            return numSpacesCovered;
        }

        if(spaces+1 <= M){
            return numSpacesCovered + occupied.size();
            // M - number of current boards = number of groups, return number of covered stalls
        }

        int rangeMax = occupied.get(occupied.size()-1); // range min is 1
        int minGap = rangeMax;
        int gapGateNum = -1;
        int gapGateIndex = -1;
        for(int i=1;i<occupied.size();i++){
            if(occupied.get(i)-occupied.get(i-1)-1<minGap && occupied.get(i)-occupied.get(i-1)-1 > 0 && !noMergeIndexes.contains(i-1)){
                minGap = occupied.get(i)-occupied.get(i-1)-1;
                gapGateNum = occupied.get(i);
                gapGateIndex = i;
            }
        }

        noMergeIndexes.add(gapGateIndex-1);
        spaces = spaces-1;
        numSpacesCovered = numSpacesCovered+minGap;


        return barnRepairRecurConden(M, occupied,numGroups, numSpacesCovered, spaces, noMergeIndexes);


    }

    public static int barnRepairRecur(int M, ArrayList<Integer> occupied, int numGroups, int numSpacesCovered){
        // So basically, we need to account for when there is one off of a group and you delete the group
        // instead you should be finding the mimums and merging them until there are only M groups left



        // if the number of gaps +1 = M - numGroups, add the number of gaps to the list and break
        int spaces = findNumofSpaces(occupied);
        if(spaces == 0){
            numGroups++;
            numSpacesCovered = numSpacesCovered+occupied.size();
            return numSpacesCovered;
        }
        if(spaces+1 == M-numGroups){
            return numGroups+numSpacesCovered;
            // M - number of current boards = number of groups, return number of covered stalls
        }

//        for(int j=0;j<occupied.length;j++){
//
//        }

        int rangeMax = occupied.get(occupied.size()-1); // range min is 1
        int minGap = rangeMax;
        int gapGateNum = -1;
        int gapGateIndex = -1;
        for(int i=1;i<occupied.size();i++){
            if(occupied.get(i)-occupied.get(i-1)<minGap && occupied.get(i)-occupied.get(i-1) > 1){
                minGap = occupied.get(i)-occupied.get(i-1)-1;
                gapGateNum = occupied.get(i);
                gapGateIndex = i;
            }
        }

        numSpacesCovered = numSpacesCovered + minGap;

        //deleting everything before the gap
        int findBeginningofGroupIndex = gapGateIndex-2;
        int nextVal = occupied.get(gapGateIndex-1);

        System.out.println(" deleted = " + occupied.get(gapGateIndex-1));
        occupied.remove(gapGateIndex-1);
        numSpacesCovered++;
        gapGateIndex--;
        boolean deleteForward = true;
        if(findBeginningofGroupIndex <0){
            deleteForward = false;
        }
        if(deleteForward){
            while(nextVal-1 == occupied.get(findBeginningofGroupIndex)){
                nextVal = occupied.get(findBeginningofGroupIndex);
                System.out.println(" deleted = " + occupied.get(findBeginningofGroupIndex));
                occupied.remove(findBeginningofGroupIndex);
                numSpacesCovered++;
                //Update the other index
                gapGateIndex--;
                findBeginningofGroupIndex--;
                if(findBeginningofGroupIndex <0){
                    break;
                }
            }
        }

        System.out.println(" after delete forward = " + numSpacesCovered);


        int prevVal = gapGateNum;

        if(prevVal == 14){
            int kk = 0;
        }

        int findEndofGroupIndex = gapGateIndex;
        // Deleting everything after the gap
        if(findEndofGroupIndex+1 < occupied.size()){
             while(prevVal+1 == occupied.get(findEndofGroupIndex+1) ){
                prevVal = occupied.get(findEndofGroupIndex+1);
                 System.out.println(" deleted = " + occupied.get(findEndofGroupIndex));
                occupied.remove(findEndofGroupIndex);
                 numSpacesCovered++;
                if(findEndofGroupIndex+1 >= occupied.size()){
                    break;
                }
                //findEndofGroupIndex++;
            }
        }
        System.out.println(" deleted = " + occupied.get(findEndofGroupIndex));
        occupied.remove(findEndofGroupIndex);
        numSpacesCovered++;
        numGroups++;

        System.out.println(" after delete back = " + numSpacesCovered);


        // update boardPositions, and number of groups,


        // call self with new baord and number

        //reutrn number of covered stalls
        return barnRepairRecur(M, occupied,numGroups, numSpacesCovered);
    }


    public static void main(String[] args) {
        BarnRepair mE = new BarnRepair();
        mE.solve(2, new int[] {1,2,4,5,6,12});
    }

}