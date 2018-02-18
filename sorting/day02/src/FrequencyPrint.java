import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        // break the string into pieces
        // make a hashmap
        // hash each word with word|total
        // if a word already has a hashed entry, just add to the total
        // At the end make a second hashmap that keeps track of totals as keys and adds on to stirngs at values
        // adds strings together at end

        // sort uses O(N log N)

        PriorityQueue<String> pQueue = new PriorityQueue<String>();
        HashMap hm = new HashMap();
        HashMap hm2 = new HashMap();

        String[] array = s.split(" ");

        //Arrays.sort(array);
        for(int i = 0; i < array.length; i++){
            //pQueue.add(array[i]);
            if(hm.get(array[i]) != null){
                hm.put(array[i], (int) hm.get(array[i]) + 1);
            }else {
                hm.put(array[i], 1);
            }
        }

        //Array<Object> b = chooseBucket(key);
        int count = 0;
        String output = "";
        for (Object i : hm.keySet()) {
            if(hm2.get(hm.get(i)) != null){
                for(int m = 0; m < (int) hm.get(i); m++ ){
                    String l = "";
                    if(hm2.get(hm.get(i)) != null){
                        l = hm2.get(hm.get(i)) + " " + i;
                    }else{
                        l = (String) i;
                    }
                    hm2.put(hm.get(i), l);
                }
            }else{
                //for(int m = 0; m < (int) hm.get(i); m++ ){
                    //output = output + " " + i;
                    String o = "";
                    for(int k = 0; k < (int) hm.get(i); k++ ){
                        o = o + " " + i;
                    }
                    hm2.put(hm.get(i), o);

                //}
            }
            count++;
            //}
        }
        //for(int i= 0; i < array.length; i ++) {
         //   System.out.println(pQueue.poll());

        for (Object i : hm2.keySet()) {
            output = output + " " +hm2.get(i);

        }

        //for (Object i : hm.keySet()) {

            //output = output + " " +hm2.get(i);
            //}
       // }
        //}
        /*System.out.println("The queue elements:");
        Iterator itr = pQueue.iterator();
        while (itr.hasNext())
            System.out.println(itr.next());
*/      int p = 0;
        return output.trim();
    }

}
