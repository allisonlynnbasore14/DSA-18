import java.util.*;


public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        // The Plan:
        // Make a hashmap for every node
        // for every node, go through all other nodes and make key, value pairs for all the distances
        // calculate n*(n-1) for all the hash values

        if(points.length == 0){
            return 0;
        }

        int total = 0;

        for(int i = 0; i < points.length; i++){
            HashMap hm = new HashMap();
            for(int p = 0; p < points.length; p++){
                if(p == i){
                    continue;
                }
                int dist = distCal(points[i][0], points[i][1], points[p][0], points[p][1]);
                if(hm.get(dist) != null){
                    hm.put(dist, (int) hm.get(dist)+ 1);

                }else{
                    hm.put(dist, 1);
                }

            }

            Collection set = hm.values();
            int subTotal = 0;
            for (Object s : set) {
                if((int)s > 1){
                    subTotal = subTotal + (int) s;
                }
            }

            total = total + subTotal/2;

        }

        return total;
    }

    public static int distCal(int x1, int y1, int x2, int y2){
        int a = x2-x1;
        int b = y2-y1;
        int c = a^2 + b^2;
        return (int) Math.sqrt(c);
    }
}

