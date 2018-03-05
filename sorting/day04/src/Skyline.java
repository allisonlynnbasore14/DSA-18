import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    //O() = NlogN

    // Plan:
    // cut the skylines in half
    // find the skyline for each half
    // combine them

    // Base Case: When # < 6
    // Divide: Find median of x boundary, cut in half
    // Concquer: call skyline(half)
    // Combine: put results togehter

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        List<Point> output = recurrsiveSkyline(B, 0, B.length-1);
        return output;
    }


    public static List<Point> recurrsiveSkyline(Building[] B, int lo, int hi) {

        // Baseline:
        if (lo >= hi) {
            List<Point> output = new ArrayList<>();
/*            System.out.println(lo);
            System.out.println(hi);
            System.out.println(B.length);
            System.out.println("dd");*/

            output.add(new Point(B[lo].l,B[lo].h));
            output.add(new Point(B[lo].r,0));
            return output;
        }
        // Divide:
        int pivotIndex = (int) (lo + hi) / 2;

        // Conquer:
        List<Point> left = recurrsiveSkyline(B, lo, pivotIndex);
        List<Point> right = recurrsiveSkyline(B, pivotIndex+1, hi);

        // Combine:


        return combine(left, right);
    }



    public static Point getLPoint(Building b){

        return new Point(b.l,b.h);

    }

    public static Point getRPoint(Building b){
        return new Point(b.r,b.h);

    }

    public static List<Point> combine(List<Point> A, List<Point> B){
        int counterA = 0;
        int counterB = 0;
        int currentAH = 0;
        int currentBH = 0;
        boolean flagDontAdd = false;
        boolean Bbigest = false;
        List<Point> output = new ArrayList<>();

        while(counterA<A.size() && counterB<B.size()){
            if(A.get(counterA).x == 36 || B.get(counterB).x == 36 ){
            }

            // find which first x is smaller
            if(A.get(counterA).x < B.get(counterB).x){
                int maxHeight = A.get(counterA).y;
                if(A.get(counterA).y < currentBH){
                    maxHeight = currentBH;
                }

                if(output.size() >0){
                    if(currentAH < currentBH){
                        if(A.get(counterA).y <= output.get(output.size()-1).y){
                            flagDontAdd = true;
                        }
                    }

                }
                // A is smaller
                //if(A.get(counterA).y > currentBH ){
                    //A is taller

                if(!flagDontAdd){
                    output.add(new Point(A.get(counterA).x,maxHeight));

                }
                flagDontAdd = false;



/*                }else if(A.get(counterA).y == currentBH){
                    if(currentBH == 0){
                        output.add(A.get(counterA));
                    }
                }else{
                    if(output.size() > 1){
                        if(currentAH < currentBH){
                            //output.add(new Point(A.get(counterA).x,currentBH));
                        }
                    }

                }*/

                currentAH = A.get(counterA).y;
                counterA++;
            }else if (A.get(counterA).x > B.get(counterB).x){
                int maxHeight = B.get(counterB).y;
                if(B.get(counterB).y < currentAH){
                    maxHeight = currentAH;
                }
                if(output.size() >0){
                    if(currentBH < currentAH){
                        if(B.get(counterB).y <= output.get(output.size()-1).y){
                            flagDontAdd = true;
                        }
                    }
                }
                // B is smaller
                //if(B.get(counterB).y > currentAH ) {
                    //B is taller

                if(!flagDontAdd){
                    output.add(new Point(B.get(counterB).x,maxHeight));

                }
                flagDontAdd = false;




/*
                if(B.get(counterB).y == currentAH){
                    if(currentAH== 0){
                        output.add(B.get(counterB));
                    }
                }else{
                    if(output.size() > 1){
                        if(currentBH < currentAH){
                            //output.add(new Point(B.get(counterB).x,currentAH));
                        }
                    }

                }*/

                //}
                currentBH = B.get(counterB).y;
                counterB++;
            }else{

                // they are equal
                if(B.get(counterB).y > A.get(counterA).y ){
                    output.add(B.get(counterB));
                }else{
                    output.add(A.get(counterA));
                }
                currentBH = B.get(counterB).y;
                currentAH = A.get(counterA).y;
                counterA++;
                counterB++;

            }
        }

        if(counterA>=A.size()){
            for(int i = counterB; i<B.size(); i++){
                output.add(B.get(i));
            }
        }
        if(counterB>=B.size()){
            for(int i = counterA; i<A.size(); i++){
                output.add(A.get(i));
            }
        }


        for (int i = 0; i < output.size(); i++) {
            int j = i+1;
            while(j < output.size() && output.get(j).y == output.get(i).y){
                output.remove(j);
                j++;
            }
        }

        for(int i= 0; i< output.size(); i++){
            System.out.println(output.get(i).x);
            System.out.println(output.get(i).y);
            System.out.println("SS");
        }
        System.out.println("BEAK");
        return output;
    }

    public static boolean isTaller(Point a, Point b){
        if(a.y > b.y){
            return true;
        }else{
            return false;
        }
    }



    public static List<Point> combineOld(List<Point> A, List<Point> B){

        int counterA = 0;
        int counterB = 0;
        List<Point> output = new ArrayList<>();

        //System.out.println();
        while(counterA<A.size() || counterB<B.size()){
            if(counterB>=B.size()){
                output.add(A.get(counterA));
                counterA ++;
                continue;
            }
            if(counterA>=A.size()){
                output.add(B.get(counterB));
                counterB ++;
                continue;
            }
            if(A.get(counterA).x < B.get(counterB).x){
                output.add(A.get(counterA));
                counterA ++;
            }else{
                output.add(B.get(counterB));
                counterB ++;
            }
        }

        return output;
    }


    public static List<Point> merge(Building[] B){
        List<Point> output = new ArrayList<>();
        if(B.length == 1){
            output.add(getLPoint(B[0]));
            output.add(new Point(B[0].r,0));
            return output;
        }

        Building a = B[0];
        Building b = B[1];
        System.out.println(a.h);
        System.out.println(b.h);
        System.out.println("HEIGHGT");
        if( a.l <b.l && a.r > b.l && a.r < b.r){
            // They are overlaping
            // A is first
            if(a.h > b.h){
                // adding left of A, B when it shifts down and right of b
                output.add(getLPoint(a));
                //System.out.println(a.l);
                //System.out.println(a.h);
                //System.out.println("INSIDE HERE");
                output.add(new Point(a.r,b.h));
                output.add(new Point(b.r,0));
            }else{
                // adding left of A, B when it shifts down and right of b
                output.add(getLPoint(a));
                output.add(new Point(b.l,b.h));
                output.add(new Point(b.r,0));
            }

        }
        else if( b.l < a.l && b.r > a.l && b.r < a.r){
            // They are overlaping
            // B is first
            if(a.h > b.h){
                // adding left of A, B when it shifts down and right of b
                output.add(getLPoint(a));
                output.add(new Point(a.r,b.h));
                output.add(new Point(b.r,0));
            }else{
                // adding left of A, B when it shifts down and right of b
                output.add(getLPoint(a));
                output.add(new Point(b.l,b.h));
                output.add(new Point(b.r,0));
            }
        }

        else if( a.l < b.l && a.r > b.r ){
            // B is inside of A
            if(a.h > b.h){
                output.add(getLPoint(a));
                output.add(new Point(a.r,0));
            }else{
                output.add(getLPoint(a));
                output.add(new Point(b.l,b.h));
                output.add(new Point(b.r,a.h));
                output.add(new Point(a.r,0));
            }

        }

        else if( b.l < a.l && b.r > a.r ){
            // A is inside of B
            if(b.h > a.h){
                output.add(getLPoint(b));
                output.add(new Point(b.r,0));
            }else{
                output.add(getLPoint(b));
                output.add(new Point(a.l,a.h));
                output.add(new Point(a.r,b.h));
                output.add(new Point(b.r,0));
            }
        }

        else if( a.l < b.l && a.r < b.r ){
            // They are not overlapping at all
            output.add(getLPoint(a));
            output.add(new Point(a.r,0));
            output.add(getLPoint(b));
            output.add(new Point(b.r,0));
        }
        for(int i= 0; i< output.size(); i++){
            System.out.println(output.get(i).x);
            System.out.println(output.get(i).y);
            System.out.println("SS");
        }
        return output;
    }

}

// add the first one to the output
// check if each point's next value falls into the its own range
// only include it if it dose not;

//int lastVal = B[0].r;
//int maxVal = B[B.length - 1].l;
//int maxH = B[0].h;
//output.add(getPoint(B[0]));
//int pointer = B[0].l;
//int indexVal = 0;
//List<Point> rights = new ArrayList<>();

// for each intersection, calcuate the =highest line it could draw
// for each value in b, do left and right node
// compare other values at that x


/*

            for (int n = 0; n < B.length; n++) {
                int bigLv = n;
                int bigRv = n;
                boolean setRight = false;
                int indexVal = n;
                for (int k = 0; k < B.length; k++) {
                    if (k == n) {
                        break;
                    }
                    // Does any other one match the left most point?
                    if (B[k].l == B[n].l) {
                        if (B[k].h > B[n].h) {
                            //output.add(getPoint(B[k]));
                            bigLv = k;
                        }
                    }
                    if (B[k].r >= B[n].l && B[k].r <= B[n].r) {
                        if (B[k].h > B[n].h) {
                            //output.add(getPoint(B[k]));
                            //setRight = true;
                            bigRv = k;
                        }
                    }

                }
                output.add(getLPoList<Point> output = new ArrayList<>();
            if(B.length == 1){
                output.add(getLPoint(B[0]));
                output.add(new Point(B[0].r,0));
                return output;
            }

            Building a = B[0];
            Building b = B[1];
            if( a.l <b.r && a.r > b.l ){
                // They are overlaping
                // A is first
                if(a.h > b.h){
                    // adding left of A, B when it shifts down and right of b
                    output.add(getLPoint(a));
                    output.add(new Point(a.r,b.h));
                    output.add(new Point(b.r,0));
                }else{
                    // adding left of A, B when it shifts down and right of b
                    output.add(getLPoint(a));
                    output.add(new Point(b.l,b.h));
                    output.add(new Point(b.r,0));
                }

            }
            if( b.l < a.r && b.r > a.l ){
                // They are overlaping
                // B is first
                if(a.h > b.h){
                    // adding left of A, B when it shifts down and right of b
                    output.add(getLPoint(a));
                    output.add(new Point(a.r,b.h));
                    output.add(new Point(b.r,0));
                }else{
                    // adding left of A, B when it shifts down and right of b
                    output.add(getLPoint(a));
                    output.add(new Point(b.l,b.h));
                    output.add(new Point(b.r,0));
                }
            }

            if( a.l < b.l && a.r > b.r ){
                // B is inside of A
                output.add(getLPoint(a));
                output.add(new Point(a.r,0));
            }

            if( b.l < a.l && b.r > a.r ){
                // A is inside of B
                output.add(getLPoint(b));
                output.add(new Point(b.r,0));
            }

            if( a.l < b.l && a.r < b.r ){
                // They are not overlapping at all[counterA]
                output.add(getLPoint(a));
                output.add(new Point(a.r,0));
                output.add(getLPoint(b));
                output.add(new Point(b.r,0));
            }
            return output;int(B[bigLv]));
                if (setRight) {
                    rights.add(getRPoint(B[bigRv]));
                }
            }
            output.addAll(rights);
            return output;

        }
//*/
//                        if(B[k].l == B[n].l) {
//                            if (B[k].h > B[n].h) {
//                                output.add(getPoint(B[indexVal]));
//                                indexVal = k;
//                            }
//                            if (B[k].r == B[n].r) {
//                                if (B[k].h > B[n].h) {
//                                    output.add(getPoint(B[indexVal]));
//                                    indexVal = k;
//                                }
//                            }
//                        }
//                    }else{
//                        output.add(getPoint(B[indexVal]));
//                        indexVal = k;
//                    }
//                }
//            }



//            for(int p = 1; p < maxVal; p++) {
//                //int maxHeight = 0;
//                int target = 0;
//                for(int k = indexVal; k < B.length; k++){
//                    if(B[k].l == p){
//                        int iY = B[indexVal].h;
//                        if(maxH < iY){
//                            // count the new intersection
//                            output.add(getPoint(B[indexVal]));
//                            indexVal = k;
//                        }
//                    }
//                }
//
//                //}
//            }


/*            while(pointer <= maxVal){
                if(pointer < lastVal ){
                    pointer ++;
                }else{
                    if(lastVal < pointer){
                        indexVal++;
                        for(int p = B[indexVal].l; p < B[indexVal].r; p++){
                            if(p <= pointer ){
                                output.add(new Point(p,B[indexVal].h));
    output.add(getLPoint(b));
                output.add(new Point(b.r,0));                            p =  B[indexVal].r;
                                pointer = p;
                            }

                        }
                        // output.add(getPoint(B[indexVal]));
                        // lastVal = B[indexVal].r;
                    }
                }

            }
            return output;*/
