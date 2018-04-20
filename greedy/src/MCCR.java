

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

// OLD ATTEMPT:
//    public static int MCCR(EdgeWeightedGraph G) {
//        G.setEindex(G.numberOfV());
//        ArrayList<Edge> edgesPlaced = new ArrayList<Edge>();
//        EdgeWeightedGraph GFinal = new EdgeWeightedGraph(G.numberOfV());
//        return MCCRecurCondensedTwo(G,  GFinal);
//    }
//
//    public static int MCCRecurCondensedTwo(EdgeWeightedGraph G,EdgeWeightedGraph GFinal){
//        int weightSum = 0;
//        boolean addWeight = false;
//        ArrayList<Integer> traversedIndexes = new ArrayList<Integer>();
//        ArrayList<Edge> traversed = new ArrayList<Edge>();
//        IndexPQ pq = new IndexPQ(G.numberOfV());
//        int t = 10;
//        for(int i = 0; i< G.numberOfV();i++){
//            pq.insert(i, Integer.MAX_VALUE);
////            pq.changeKey(i, Integer.MAX_VALUE);
//            //pq.changeKey(i, Integer.MAX_VALUE);
//        }
//        //pq.changeKey(0,0);
//        Iterable<Edge> e = G.edges(0); // neighbors to the smallest key
//        int minVal = Integer.MAX_VALUE;
//        Edge minEdg = null;
//        int count = 0;
//        for (Edge edg : e) {
//            if(edg.weight() < minVal){
//
//                    minVal = edg.weight();
//                    minEdg = edg;
//
//            }
//            pq.changeKey(edg.getW(),edg.weight());
//            count++;
//        }
//
//        //int old = pq.delMin();
//        GFinal.addEdge(minEdg);
//        traversed.add(minEdg);
//        traversedIndexes.add(minEdg.getV());
//        traversedIndexes.add(minEdg.getW());
//        //int prevIndex = 0;
//        int extraWeight = 0;
//        //weightSum = minEdg.weight();
//        // NEED TO FIX PREV INDEX AND THAT SHHOULD BE DONE
//
//        // the previous index is the one in traversed that has this as a node and matches minweight
//
//
//        while(GFinal.numberOfE() != G.numberOfE()){
//
//            System.out.println("weightSum = " + weightSum);
//            if(extraWeight == 5){
//                int dd = 3;
//            }
//
//            if(GFinal.numberOfV() == G.numberOfV()){
//                return weightSum;
//
//                //addWeight = true;
//            }
//
//            minVal = (int) pq.getMinsWeight();
//            int min = pq.delMin(); // smallest key
//            int prevIndex = findPrev(min, minVal, traversed, G);
//            // need to find a way to get the previous node that connected this node
//            Iterable<Edge> initalEdges = G.edges(min); // neighbors to the smallest key
//            Edge current = null;
//            for (Edge extra :initalEdges) {
//                    if (min == extra.getW()) {
//                        if (prevIndex == extra.getV()) {
//                            current = extra;
//                            extraWeight = extra.weight();
//                            break;
//                        }
//                        //pq.changeKey(extra.getV(),edg2.weight());
//                    } else {
//                        if (prevIndex == extra.getW()) {
//                            current = extra;
//                            extraWeight = extra.weight();
//                            break;
//                        }
//                    }
//
//            }
//
//
//            if(current != null) {
//                GFinal.addEdge(current);
//                traversed.add(current);
//                traversedIndexes.add(current.getW());
//                traversedIndexes.add(current.getV());
//                weightSum = weightSum + extraWeight;
//                System.out.println("extraWeight = " + extraWeight);
//
//            }
//
//
//
//            if(GFinal.numberOfV() == G.numberOfV()){
//                return weightSum;
//
//                //addWeight = true;
//            }
//            System.out.println(GFinal.numberOfV());
//            System.out.println(G.numberOfV());
//
//
//
//            //Edge targetEdge = G.edges(min);
//            Iterable<Edge> ep = G.edges(min); // neighbors to the smallest key
//            for (Edge edg2 : ep) {
//                System.out.println("ep2's weight = " + edg2.weight());
//                if(edg2.weight() == 12){
//                    int ssss = 10;
//                }
//                if(!traversed.contains(edg2) ){
//                    if(min == edg2.getW()){
//                        if(!traversedIndexes.contains(edg2.getV())){
//                            System.out.println("added = " + edg2.getV());
//                            if(pq.get(edg2.getV())>edg2.weight()){
//                                pq.changeKey(edg2.getV(),edg2.weight());
//                            }
//                        }else{
//                            System.out.println("edg2.getV() = " + edg2.getV());
//                        }
//                    }else{
//                        if(!traversedIndexes.contains(edg2.getW())){
//                            System.out.println("added = " + edg2.getW());
//                            if(pq.get(edg2.getW())>edg2.weight()){
//                                pq.changeKey(edg2.getW(),edg2.weight());
//                            }
//
//                        }else{
//                            System.out.println("edg2.getV() = " + edg2.getW());
//                        }
//                    }
//                }
//            }
//            // we want to add the edge that goes from current to min
//
//
//            //GFinal.addEdge();
//        }
//
//        return 0;
//        //IndexPQ<Key> pq = new IndexPQ<>(G.numberOfV());
//    }
//
//
//    public static int findPrev(int minIndex, int minWeight, ArrayList<Edge> traversed, EdgeWeightedGraph G){
//        //for(int i = 0; i< traversed.size(); i++){
//            Iterable<Edge> e = G.edges(minIndex); // neighbors to the smallest key
//            for (Edge edg : e) {
//                if(edg.weight() == minWeight){
//                    if(minIndex == edg.getW()){
//                        return edg.getV();
//                    }else{
//                        return edg.getW();
//                    }
//                }
//            }
//        //}
//        return -1;
//
//    }
//
//    public static int MCCRecurCondensed(EdgeWeightedGraph G, int weightSum, EdgeWeightedGraph GFinal, ArrayList<Edge> placedEdges){
//        if (G.numberOfV() - 1 == GFinal.numberOfE()) {
//            return weightSum;
//        }
//
//        int minEdge = -1;
//        Edge targetEdge = null;
//        for (int i = 0; i < G.getEindex(); i++) {
//            Iterable<Edge> it = G.edges(i);
//            if (it != null) {
//                for (Edge edg : it) {
//                    if (edg.weight() < minEdge || minEdge == -1) {
//                        if (!used(placedEdges, edg)) {
//                            //if(GFinal.numberOfE() < 4 || (GFinal.edges(edg.getW()) == null && GFinal.edges(edg.getV()) == null)){
//                                minEdge = edg.weight();
//                                targetEdge = edg; //}
////                                if(edg.weight() == 8 && GFinal.numberOfE() == 4){
////                                    int stop = 100;
////                                }
//
//                                //System.out.println("edg.weight() + \" \" + G.numberOfE() = " + edg.weight() + " " + GFinal.numberOfE());
////                                if (!isCycleRec(edg, edg.getW(), null, GFinal, GFinal.edges(edg.getW()), null, new ArrayList<Edge>(), 0) && !isCycleRec(edg, edg.getV(), null, GFinal, GFinal.edges(edg.getV()), null, new ArrayList<Edge>(),0)) {
//                                minEdge = edg.weight();
//                                targetEdge = edg;
////                                }
//
//                            //}
//                        }
//                    }
//                }
//            }
//        }
//        if(GFinal.numberOfE() >= 4 ){
//            int stop = 100;
//        }
//        if(GFinal.numberOfE() < 4 ){
//            GFinal.addEdge(targetEdge);
//            System.out.println(" = " + targetEdge.weight());
//            weightSum = weightSum + minEdge;
//        }else if(!isCycleRecFinal(GFinal,new ArrayList<Integer>(), targetEdge.getV(),GFinal.edges(targetEdge.getV()), -1,targetEdge)){
//            GFinal.addEdge(targetEdge);
//            System.out.println(" = " + targetEdge.weight());
//            weightSum = weightSum + minEdge;
//        }
//        placedEdges.add(targetEdge);
//
//        return MCCRecurCondensed(G, weightSum,GFinal, placedEdges);
//
//    }
//
//
//    public static int MCCRrecur(EdgeWeightedGraph G, int weightSum, int numLeft, ArrayList<Edge> placedEdges) {
//        if (G.numberOfV() - 1 == numLeft) {
//            return weightSum;
//        }
//
//        // find the smallest weight
//        //for each vertice, get the edges, iterator through
//        int minEdge = -1;
//        Edge targetEdge = null;
//        int targetV = -1;
//        int targetW = -1;
//        int targetIndex = -1;
//        for (int i = 0; i < G.getEindex(); i++) {
//            Iterable<Edge> it = G.edges(i);
//            if (it != null) {
//                for (Edge edg : it) {
//                    if (edg.weight() < minEdge || minEdge == -1) {
//                        if (!used(placedEdges, edg)) {
//                            if(G.numberOfE() <5){
//                                int jack = 3;
//                            }
//                            //if (!isCycleRec(it, placedEdges, edg)) {
//                                minEdge = edg.weight();
//                                targetEdge = edg;
//                                targetV = edg.getV();
//                                targetW = edg.getW();
//                                targetIndex = i;
//                            //}
//                        }
//                    }
//                }
//            }
//        }
//        //TODO: add a check if it is a cycle
//
//        // delete it from everything
//        farout:
//        for (int j = 0; j < G.getEindex(); j++) {
//            Iterable<Edge> it = G.edges(j);
//            if (it != null) {
//                for (Edge edg : it) {
//                    if (edg.getV() == targetV && edg.getW() == targetW) {
//                        placedEdges.add(edg);
//                        G.deleteEdge(edg, targetIndex);
//                        break farout;
//                    }
//                }
//            }
//        }
//
//        weightSum = weightSum + minEdge;
//        numLeft++;
//
//        return MCCRrecur(G, weightSum, numLeft, placedEdges);
//
//
//    }
//
//    public static boolean used(ArrayList<Edge> placedEdges, Edge e) {
//        for (int i = 0; i < placedEdges.size(); i++) {
//            if (placedEdges.get(i).getW() == e.getW() && placedEdges.get(i).getV() == e.getV()) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static int findNumE(Iterable<Edge> e){
//        int count = 0;
//        for(Edge ed : e){
//            count = count +1;
//        }
//        return count;
//    }
//
//    public static Edge getEdgeFromSet(Iterable<Edge> currentNod, int index){
//        int count = 0;
//        for(Edge ed : currentNod){
//            if(count == index){
//                return ed;
//            }
//            count++;
//        }
//        return null;
//    }
//
////    public static Iterable<Edge> getSetFromEdge(){
////
////    }
//
//
//    public static boolean isCycleRecFinal(EdgeWeightedGraph GFinal,ArrayList<Integer> traversed, int target,Iterable<Edge> currentNode,int currentEdgeIndex,Edge currentEdge){
//        if(traversed.contains(target)){
//            return true;
//        }
//        if(currentEdgeIndex!= -1){
//            traversed.add(currentEdgeIndex);
//        }
//        for(Edge ed : currentNode){
//            if(ed.getW() == currentEdgeIndex){
//                currentEdgeIndex = ed.getV();
//            }else{
//                currentEdgeIndex = ed.getW();
//            }
//
//            if(isCycleRecFinal(GFinal, traversed, target, GFinal.edges(currentEdgeIndex), currentEdgeIndex, ed)){
//                return true;
//            }
//        }
//        traversed.remove(traversed.size()-1);
//        return false;
//    }
//
//    public static boolean isCycleRec(Edge target, int currentEdgeIndex, Edge currentEdge, EdgeWeightedGraph GFinal, Iterable<Edge> currentNode, Edge prevEdge, ArrayList<Edge> traversed, int numHits){
//        if(currentEdge == target){
//            return true;
//        }
//        System.out.println("currentEdgeIndex = " + currentEdgeIndex);
//        if(currentEdgeIndex == target.getW() || currentEdgeIndex == target.getV()){
//            numHits++;
//            System.out.println("numHits = " + numHits);
//        }
//        if(numHits > 2){
//            return true;
//        }
//        for(Edge ed : currentNode){
//            if(traversed.contains(ed)){
//                continue;
//            }
//            traversed.add(ed);
//            if(ed == prevEdge){
//                if(findNumE(currentNode) <2){
//                    return false;
//                }else{
//                    continue;
//                }
//            }else{
//                prevEdge = ed;
//            }
//            if(ed.getW() == currentEdgeIndex){
//                currentEdgeIndex = ed.getV();
//                if(currentEdgeIndex == target.getW() || currentEdgeIndex == target.getV()){
//                    numHits++;
//                    System.out.println("numHits = " + numHits);
//                }
//                if(numHits > 2){
//                    return true;
//                }
//            }else{
//                currentEdgeIndex = ed.getW();
//                if(currentEdgeIndex == target.getW() || currentEdgeIndex == target.getV()){
//                    numHits++;
//                    System.out.println("numHits = " + numHits);
//                }
//                if(numHits > 2){
//                    return true;
//                }
//            }
//            boolean out = isCycleRec(target, currentEdgeIndex, ed, GFinal, GFinal.edges(currentEdgeIndex),prevEdge, traversed, numHits);
//            if(out){// getEdgeFromSet(currentNode, count))){
//                return true;
//            }else{
//                continue;
//            }
//        }
//        return false;
//
//    }
//
//
//    public static boolean isCycle(Iterable<Edge> e, ArrayList<Edge> placedEdges, Edge eItself) {
//        // starting at e, iterate through the paths using the placed edges,
//        // if you ever get back to e, reutnr true, else return false
//        if (placedEdges.size() < 1) {
//            return false;
//        }
//        int currentPos = eItself.getW();
//        Edge currentEdg = eItself;
//        int count = 0;
//        int eLen = findNumE(e);
//        while (eItself.getV() != currentPos && count < placedEdges.size() * 2) {
//            for (int j = 0; j < 2; j++) {
//                if (j == 1) {
//                    currentPos = eItself.getV();
//                }
//                for (int i = 0; i < eLen ; i++) {
//                    if (placedEdges.get(i).getV() == currentPos && placedEdges.get(i).getW() != currentEdg.getW()) {
//                        count = count + 2;
//                        currentPos = placedEdges.get(i).getW();
//                        currentEdg = placedEdges.get(i);
//                        i = 0;
//                    } else if (placedEdges.get(i).getW() == currentPos && placedEdges.get(i).getV() != currentEdg.getV()) {
//                        currentPos = placedEdges.get(i).getV();
//                        currentEdg = placedEdges.get(i);
//                        count = count + 2;
//                        i = 0;
//                    } else {
//                        if(j == 1){
//                            return false;
//                        }
//                        continue;
//
//                    }
//                }
//            }
//        }
//        return true;
//    }
