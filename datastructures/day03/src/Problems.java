import java.sql.SQLSyntaxErrorException;
import java.util.*;
import java.util.Stack;

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
            // Find the smalled value in the first k items
            for(int i = 0; i <= k; i++) {
                if (A[i] < A[minimumIndex]) {
                    // set the smallest as the minimum index
                    minimumIndex = i;
                }
            }
            // Added the minimum to the output to save it
            outputList.add(A[minimumIndex]);
            // increase the total to possibly stop the while loop
            outputTotal ++;
            // decrese A to be only from the numbers we have checked to the end
            A = Arrays.copyOfRange(A, minimumIndex+1, A.length);
            // reset k
            k = k - minimumIndex;
            // once k is less than or equal to 0, we are done and cand just add everything else
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
        // 3) reset the slow node if necessary for midpoint
        // 3) Reverse First half
        // 4) Compare both sides

        if(n == null || n.next == null){
            return true;
        }
        
        if(n.next.val == n.val && n.next.next == null){
            return true;
        }


        Node slowNode = n;
        Node fastNode = n;
        // This is only used if it is off
        Node middleNode = null;
        int count = 0;




        while(fastNode != null && fastNode.next != null){
            slowNode = slowNode.next;
            fastNode = fastNode.next.next;
            count ++;
        }

        // If there is a middle node then the fast one will be null
        if( fastNode != null){
            middleNode = slowNode;
            slowNode = slowNode.next;
        }


        Node target = slowNode;
        Node prev = null;
        Node next;


        // reversing the second half
        while(target != null){

            next = target.next;
            target.next = prev;
            prev = target;
            target = next;
        }

        Node startOfSecondHalf = prev;

        // compmare the two

        Node targetA = n;
        Node targetB = startOfSecondHalf;

            // 
        while(targetA != null && targetB != null){
            if(targetA.val == targetB.val ){
                targetA = targetA.next;
                targetB = targetB.next;
            }else{
                return false;
            }

        }

        return true;



    }

    public static String infixToPostfix(String s) {
        // Going to go through the string
        // if I come apon an operand I will

        boolean prevSpace = false;
        //making a stack to handle operands
        Stack<Character> stack = new Stack<>();
        String finalString = "";

        // starting to run through the string
        for(int i =0; i < s.length() ; i++){

            // if it is a number or a space* then we know it will be next on the output
            if(s.charAt(i) != '*' && s.charAt(i) != '/' && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != ')' && s.charAt(i) != '('){
                // If it is a space then we need to make sure we already haven't added a space
                if(s.charAt(i) == ' ' ){
                    if(!prevSpace){
                        finalString = finalString + s.charAt(i);
                        prevSpace = true;
                    }else{
                        prevSpace = true;
                    }
                }else{
                    // otherwise it is a number and should be added tot he final output string
                    finalString = finalString + s.charAt(i);
                    prevSpace = false;
                }
            }

            // if it is a beginning (, then we want to keep track of it and put it in the stack
            else if(s.charAt(i) == '('){
                stack.push(s.charAt(i));
            }

            // if it s a closing ), then we want to add to the final string everything in between the (  )
            else if(s.charAt(i) == ')'){
                // watch out for if there is a () inside a ()!
                while(!stack.isEmpty() && stack.peek() != '('){
                    finalString = finalString + stack.pop();
                    prevSpace = false;
                }
                // take out the ) from the stack
                stack.pop();
            }
            else{
                // This is when I find an operator
                while(!stack.isEmpty()) {
                    if (!isOper(s.charAt(i)) || isOper(stack.peek())) {
                        finalString = finalString + stack.pop() + ' ' ;
                    }else{
                        break;
                    }
                }
                stack.push(s.charAt(i));
            }

        }

        while (!stack.isEmpty())
            finalString = finalString + ' ' + stack.pop() ;


        if(finalString.charAt(0)==' ') {
            finalString = finalString.substring(1, finalString.length());
        }
        if(s.charAt(finalString.length()-1)==' '){
            finalString = finalString.substring(0, finalString.length() -1);
        }
        return finalString;
    }

    public static boolean isOper( char o){
        if(o == '*' || o == '+' || o == '-' || o == '/'){
            return true;
        }
        return false;
    }


/*    public static String infixToPostfix2(String s) {
        // Run trhough and identify entires/ partial entries
        // identify the operator
        // put the operator on right of entry
        // set entitty as a partial entry

        // define an entry as one quntity, operator, other quantity
        // entry = 1
        // quanity = 2
        // operand = 3


        boolean firstVal = false;
        boolean operator = false;
        boolean secondVal = false;
        boolean jumpParen = false;
        char currentOp = '.';
        int opIndex = -1;
        int lastIndex = -1;
        int orgLength = s.length();
        int jumpIndex = 0;

        for(int i =0; i < s.length() ; i++){

            if(s.charAt(i) == ')' || s.charAt(i) == '('){
                s = s.substring(0, i) + s.substring(i+2, s.length()-1);
                orgLength --;
                i --;
                continue;
            }

            if( s.charAt(i) == ' ' || s.charAt(i) == ' '){
                continue;
            }

            if( !firstVal && s.charAt(i) != '*' && s.charAt(i) != '/' && s.charAt(i) != '+' && s.charAt(i) != '-'){
                firstVal = true;
            }
            if( s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '+' || s.charAt(i) == '-'){
                operator = true;
                currentOp = s.charAt(i);
                opIndex = i;
                if(s.charAt(i+2) == '('){
                    jumpParen = true;
                    jumpIndex = i + 5;
                }
            }
            if( !secondVal && firstVal && operator && s.charAt(i) != '*' && s.charAt(i) != '/' && s.charAt(i) != '+' && s.charAt(i) != '-'){
                secondVal = true;
                lastIndex = i;

            }

            if(s.charAt(0)==' ') {
                s = s.substring(1, s.length());
            }
            if(s.charAt(s.length()-1)==' '){
                s = s.substring(0, s.length() -1);
            }

            if(firstVal && secondVal && operator){
                // delete the operator and move it to the end

                if(lastIndex >= s.length()){
                    return s.substring(0, opIndex -1) + s.substring(opIndex+1, lastIndex+1) + ' ' + currentOp;
                }

                if(!jumpParen){
                    s = s.substring(0, opIndex -1) + s.substring(opIndex+1, lastIndex+1) + ' ' + currentOp + s.substring(lastIndex+1, s.length());
                }else{
                    s = s.substring(0, opIndex -1) + s.substring(opIndex+1, jumpIndex + 1) + currentOp + s.substring( jumpIndex,  s.length());
                    jumpParen = false;

                }
                firstVal = true;
                operator = false;
                secondVal = false;
            }
        }

        if(s.charAt(0)==' ') {
            s = s.substring(1, s.length());
        }
        if(s.charAt(s.length()-1)==' '){
            s = s.substring(0, s.length() -1);
        }

        return s;
    }*/

}
