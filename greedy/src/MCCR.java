

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MCCR {
    public static int MCCR(EdgeWeightedGraph G) {
        //G.setEindex(G.numberOfV());

        //EdgeWeightedGraph GFinal = new EdgeWeightedGraph(G.numberOfV());
        return MCCRecurCondensedTwo(G);
    }

    public static int MCCRecurCondensedTwo(EdgeWeightedGraph G){
        ArrayList<Integer> edgesPlaced = new ArrayList<Integer>();
        HashSet<Integer> verticiesVisited = new HashSet<Integer>();
        IndexPQ pq = new IndexPQ(G.numberOfV());
        HashMap<Integer,Integer> previousNodes = new HashMap<>();
        for(int i = 0; i< G.numberOfV();i++){
            pq.insert(i, Integer.MAX_VALUE);
            verticiesVisited.add(i);
        }
        pq.changeKey(0,0);
        previousNodes.put(0,-1);
        verticiesVisited.add(0);

        //Iterator<Integer> verts = G.vertices.iterator();
        while(!pq.isEmpty()){

            int min = pq.delMin();
            verticiesVisited.remove(min);
            edgesPlaced.add(min);
            Iterable<Edge> initalEdges = G.edges(min); // neighbors to the smallest key
            Edge current = null;
            for (Edge extra :initalEdges) {
                int target = extra.other(min);
                if(verticiesVisited.contains(target) && extra.weight() < (int)pq.keys[target]){
                    // fix pq and add to previous
                    previousNodes.put(target, min);
                    pq.changeKey(target, extra.weight());
                }

            }
        }

        // get all the weights
        return getWeights(previousNodes, G);

    }

    public static int getWeights(HashMap<Integer,Integer> prev, EdgeWeightedGraph G){
        int weightSum = 0;
        for(int prevNode :prev.keySet()){
            for(Edge edg : G.edges(prevNode)){
                int e = edg.other(prevNode);
                if(prev.get(prevNode) == e){
                    weightSum = weightSum + edg.weight();
                }
            }
        }
        return weightSum;
    }

}
