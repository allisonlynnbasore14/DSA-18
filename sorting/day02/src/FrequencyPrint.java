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

        // make hashmaps
        HashMap hm = new HashMap();
        HashMap hm2 = new HashMap();
        //split up the sting into an array
        String[] array = s.split(" ");
        // for each element in the array, put it in a hashmap
        // if there is already a word like it there, then just add to the value total
        for(int i = 0; i < array.length; i++){
            if(hm.get(array[i]) != null){
                hm.put(array[i], (int) hm.get(array[i]) + 1);
            }else {
                hm.put(array[i], 1);
            }
        }

        // Make a string to store things to output

        String output = "";

        // for each key in the set
        // check if there is already something in the second hashmap
        // if there is nothing than put in the word in to the value side and the frequency number as the key
        // if there is something then add the word to the string in the value
        // so each bucket only has one key,value pair

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
                    String o = "";
                    for(int k = 0; k < (int) hm.get(i); k++ ){
                        o = o + " " + i;
                    }
                    hm2.put(hm.get(i), o);

            }

        }

        // for all the buckets in the second hash map, put them togehter in order
        for (Object i : hm2.keySet()) {
            output = output + " " +hm2.get(i);

        }

        // cut off the extra spaces
        return output.trim();
    }

}
