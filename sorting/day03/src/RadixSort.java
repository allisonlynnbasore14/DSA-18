import java.util.LinkedList;

public class RadixSort {

    /**
     * @param n the digit number, 0 is least significant
     * @return
     */
    private static int getNthDigit(int number, int base, int n) {
        return number / ((int) Math.pow(base, n)) % base;
    }


    /**
     * Use counting sort to sort the integer array according to a digit
     *
     * @param b The base used in radix sort
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByDigit(int[] A, int b, int n) {
        LinkedList<Integer>[] L = new LinkedList[b];
        for (int i = 0; i < b; i++)
            L[i] = new LinkedList<>();
        for (int i : A) {
            int digit = 0;
            int temp = i;
            int count = n;
            // When n = 4, I want to do temp % 10
            // When n = 3, I want to do temp / 10 % 10
            // When n = 2, I want to do temp /10/10 % 10
            // When n = 1; I want to do temp /10/10/10 %10

            while (count > 0) {
                if(temp < 10 && count != 0){
                    digit = 0;
                    L[digit].add(i);
                    count = 0;
                }else if(count == 0){
                    digit = temp % 10;
                    L[digit].add(i);
                    count = 0;
                }else{
                    temp = temp / 10;
                    count--;
                }
            }


            // If temp/10
/*            if(temp < 10){
                // if there is only one number??
                digit = temp;
            }else{
            }*/
/*

            String s = String.valueOf(temp);
            if(n > s.length()){
                L[0].add(i);
            }else{
                char ch = s.charAt(w-n);
                L[digit].add((int) ch);
            }
*/

            // TODO: Extract the relevant digit from i, and add i to the corresponding Linked List.
//            System.out.println(digit);
//            System.out.println("ff");
//            System.out.println(L.length);

        }
        int j = 0; // index in A to place numbers
        for (LinkedList<Integer> list : L) {
            // TODO: Put all numbers in the linked lists into A
            for(int p = list.size()-1; p >= 0; p--){
                A[j] = list.get(p);
                j++;
            }
        }
    }

    /**
     * Runtime: TODO: Express your runtime in terms of n, b, and w
     *
     * n: length of array
     * w: word length of integers A in base b (equal to log base b of k (log_b k) )
     *
     * @param b The base to use for radix sort
     */
    static void radixSort(int[] A, int b) { ;
        // Calculate the upper-bound for numbers in A
        int k = A[0] + 1;
        for (int i = 1; i < A.length; i++)
            k = (A[i] + 1 > k) ? A[i] + 1 : k;
        int w = (int) Math.ceil(Math.log(k) / Math.log(b)); // w = log base b of k, word length of numbers
        System.out.println(w);
        System.out.println("ss");
        // TODO: Perform radix sort
        for(int m = w; m >= 0 ; m --){
            countingSortByDigit(A, k, m);
            for(int dd = A.length-1; dd > A.length-100; dd --) {
                System.out.println(A[dd]);
            }
            System.out.println("00000000000000000000000000000");
        }
    }

}
