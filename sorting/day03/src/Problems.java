import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        // make the negatives positive * 2
        // use coutning sort
        // put negatives back
        for(int i = 0; i < A.length; i++){
            A[i] = A[i] + 100;
        }
        countingSort(A);
        for(int i = 0; i < A.length; i++){
            A[i] = A[i] - 100;
        }
        return;
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        // make a linked list
        // put in all the values of each postion in the string
        //
;
        LinkedList<String>[] L = new LinkedList[127];
        for (int i = 0; i < 127; i++){
            L[i] = new LinkedList<String>();
        }

        for (String i : A) {
            char digit =  Character.MIN_VALUE;
            String temp = i;
            int count = n;

            // Break the string into chars

            // When n = 4, if size = 4, return a[0], else 0

            int b = getNthCharacter(i, n);
/*            if(n == 0){
                digit = i.charAt(i.length()-1);
            }else{
                if(i.length() < n ){
                    digit =  Character.MIN_VALUE;
                }else{
                    digit = i.charAt(i.length() - n );
                }
            }*/
            //int b = Character.getNumericValue(digit);
            L[b].add(i);
//            while (count >= 0) {
//                if (temp.length() < 2 && count != 0) {
//                    digit = 0;
//                    L[digit].add(i);
//                    count = -1;
//                } else if (count == 0) {
//                    digit = temp % 10;
//                    L[digit].add(i);
//                    count = -1;
//                } else {
//                    temp = temp / 10;
//                    count--;
//                }
//            }


        }
        int j = 0; // index in A to place numbers
        for (LinkedList<String> list : L) {
            for (int p = 0; p < list.size(); p++) {
                A[j] = list.get(p);
                j++;
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        for(int m = 0; m < stringLength ; m ++){
            countingSortByCharacter(S, m);
        }
    }

    /**
     * @param A The array to count swaps in
     */

    public static int countSwaps(int[] A) {
        // TODO
        return 0;
    }

    static void countingSort(int[] A) {

        int max = A[0];

        for (int i = 1; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        int[] newArray = new int[max+1];
        for(int i = 0; i < A.length; i++){
            int index = A[i];
            newArray[index] = newArray[index] + 1;
        }
        int count = 0;
        for(int j = 0; j < newArray.length; j++){
            //if(newArray[j] != 0){
            // j is the index of the new array and the value that is kept
            for(int p = newArray[j]; p>0; p-- ){
                A[count] = j;
                count++;
            }
            //}
        }
    }

}
