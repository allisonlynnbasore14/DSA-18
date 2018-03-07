
public class Problems {

    public static int leastSum(int[] A) {

        // with radix sort everything has to be backwards now

        if(A.length < 4) {
            int sum = 0;
            for (int k = 0; k < A.length; k++) {
                sum = A[k] + sum;
            }
            return sum;
        }
        //HeapSort hs = new HeapSort();
        //hs.heapify(A);
        RadixSort.radixSort(A, A.length);
//
        if(A.length == 5){
            int first = A[0]*100 + A[2]*10 + A[4];
            int second = A[1]*10+ A[3];

            return first + second;
        }
//
//        if(A.length == 4){
//            int a = A.length-1;
//            int b = A.length-3;
//            int first = A[a]*10 + A[b];
//            int second = A[a-1]*10+ A[b-1]*1;
//
//            return first + second;
//        }

        if(A.length %2 == 0){
            // is even
            int sum1 = 0;
            int sum2 = 0;
            double L = (A.length/2)-1;
            int m1 = (int) Math.pow(10.0, L);
            int m2 = (int) Math.pow(10.0, L);
            for(int i = 0; i<A.length-1;i++){
                sum1 = sum1 + A[i]*m1;
                i++;
                sum2 = sum2 + A[i]*m2;
                m1 = m1/10;
                m2 = m2/10;
            }
            return sum1 + sum2;
        }else{
            // is odd
            int sum1 = 0;
            int sum2 = 0;
            double L = (A.length/2)-1;
            int m1 = (int) Math.pow(10.0, L);
            int m2 = (int) Math.pow(10.0, L-1);
            for(int i = 0; i<A.length-1;i++){
                sum1 = sum1 + A[i]*m1;
                i++;
                if(i == A.length){
                    return sum1 + sum2;
                }
                sum2 = sum2 + A[i]*m2;
                m1 = m1/10;
                m2 = m2/10;
            }
            return sum1 + sum2;
        }

/*        if(A.length < 7){
            int a = A.length-1;
            int b = A.length-3;
            int c = A.length-5;
            if(A[b]<A[a-1]){
                int hold = A[a-1];
                A[a-1] = A[b];
                A[b] = hold;
            }
            if(A[a-1]<A[a]) {
                int hold = A[a];
                A[a] = A[a - 1];
                A[a - 1] = hold;
            }
            if(A[c-1]<A[c]) {
                int hold = A[c];
                A[c] = A[c - 1];
                A[c - 1] = hold;
            }
            int first = A[a]*100 + A[b]*10 + A[c];
            int second = A[a-1]*100 + A[b-1]*10 + A[c-1];

            return first + second;
        }*/



    }


    public static int findPlace(int a, int[]B) {
        if(a< B[3]){
            if(a< B[2]){
                if(a< B[1]){
                    if(a< B[0]){
                        B[0] = a;
                        return 0;
                    }
                    B[1] = a;
                    return 1;
                }
                B[2] = a;
                return 2;
            }
            B[3] = a;
            return 3;
        }
        return -1;
    }




}

/*


    int[] B = new int[4];
        for(int p = 0; p < 4; p++){
        B[p] = 10;
        }

        if(A.length < 5){
        int sum = 0;
        for(int k = 0; k<A.length; k++){
        sum = A[k] +sum;
        }
        return sum;
        }
        for(int i = 0; i<A.length; i++){
        int place = findPlace(A[i], B);

        if(place != -1 && B[place] == 10){
        B[place] = A[i];
        }
        if(place != -1 && B[place] !=10){
        int holder = B[place];
        B[place] = A[i];
        findPlace(holder, B);
        }

        //makes it so the largest is in the back and the smallest in the front
        if(A[i]< B[3]){
        if(A[i]< B[2]){
        if(A[i]< B[1]){
        if(A[i]< B[0]){
        B[0] = A[i];
        continue;
        }
        B[1] = A[i];
        continue;
        }
        B[2] = A[i];
        continue;
        }
        B[3] = A[i];
        continue;
        }

        }*/
