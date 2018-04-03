import sun.awt.AWTIcon32_security_icon_yellow16_png;

import java.util.*;

public class CoinsOnAClock {

    // O(3^n)

    // space = 0(n)

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // for each type of coin, make an array of chars
        //LinkedList<Character> coins = new LinkedList<>();
        int numCoins = pennies+nickels+dimes;
        List<Character> coins = new ArrayList<>();
        List<char[]> result = new ArrayList<>();
        char[] clock = new char[numCoins];
        coinsRecur(result, 0,  clock, pennies, nickels, dimes);

        return result;
    }

    private static char[] copyOf(char[] A) {
        char[] B = new char[A.length];
        //for (int i = 0; i < A.length; i++)
        System.arraycopy(A, 0,B,0,A.length );
                   // A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static void coinsRecur(List<char[]> output, int currentPos,char[] clock, int numPens,int numNick, int numDim){
        while(currentPos > clock.length-1){
            currentPos = currentPos- (clock.length);
        }
        // base case: check if unused is empty
        int total = numPens + numNick + numDim;
        if(total == 0){
            for(int i = 0; i< clock.length; i++){
                if(clock[i] != 'p'){
                    if(clock[i] != 'n'){
                        if(clock[i] != 'd') {
                            return;
                        }
                    }
                }

            }
            output.add( copyOf(clock));
            return;
        }

        for(int w = 0; w < 3; w++) {
            char u = ' ';
            if(w== 0 && numPens > 0){
                u = 'p';
            }
            if(w== 1 && numNick > 0){
                u = 'n';
            }
            if(w== 2 && numDim > 0){
                u = 'd';
            }
            int coinVal = checkCoin(u);
            if(clock[currentPos] == '\u0000'){
                if(w == 0){
                    if(numPens > 0){
                        numPens--;
                    }else{
                        continue;
                    }

                }
                if(w == 1){
                    if(numNick > 0){
                        numNick--;
                    }else{
                        continue;
                    }
                }
                if(w == 2){
                    if(numDim > 0){
                        numDim--;
                    }else{
                        continue;
                    }
                }
                clock[currentPos]=u;

                coinsRecur(output, currentPos+coinVal, clock, numPens, numNick, numDim);
                //unUsedPos++;
                clock[currentPos] = '\u0000';

                //unused.add(0,u);
                if(w == 0){
                    numPens++;
                }
                if(w == 1){
                    numNick++;
                }
                if(w == 2){
                    numDim++;
                }
            }

        }


        return;

    }

    public static int checkCoin(char a){
        if(a == 'p'){
            return 1;
        }
        if(a == 'n'){
            return 5;
        }
        if(a == 'd'){
            return 10;
        }
        return 0;
    }
}
