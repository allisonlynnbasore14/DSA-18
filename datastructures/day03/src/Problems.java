import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // Need to test and figure it out
        int outputTotal = 0;
        List<Integer> outputList = new LinkedList<>();
        int desiredLength = A.length-k;
        while(outputTotal < desiredLength){
            int minimumIndex = 0;
            for(int i = 0; i <= k; i++) {
                if (A[i] < A[minimumIndex]) {
                    minimumIndex = i;
                }
            }
            outputList.add(A[minimumIndex]);
            outputTotal ++;
            A = Arrays.copyOfRange(A, minimumIndex+1, A.length);
            k = k - minimumIndex;
            if(k <= 0){
                for(int b = 0 ; b < A.length; b++){
                    outputList.add(A[b]);
                }

                return outputList;
            }
        }
        return outputList;

    }


    public static boolean isPalindrome(Node n) {
        // 1) find length with one fast/ one slow method
        // 2) See if there is a mid node
        // 3) Reverse First half
        // 4) Compare halfs

        if(n == null){
            return true;
        }

        Node slowNode = n;
        Node fastNode = n;
        int count = 0;

        while(fastNode.next != null){
            slowNode = slowNode.next;
            fastNode = fastNode.next;
            count ++;
        }

        // TODO determine if there is a mid Node

        output = compare(reverse(n, slowNode), slowNode, fastNode);


        return output;
    }

    public static String infixToPostfix(String s) {
        // Run trhough and identify entires/ partial entries
        // identify the operator
        // put the operator on right of entry
        // set entitty as a partial entry

        // define an entry as one quntity, operator, other quantity

        for(int i; i< s.length(); i++){
            System.out.println(i);
        }




        return null;
    }

}
