import java.util.ArrayList;

public class MCCR {

    public static int MCCR(EdgeWeightedGraph G) {
        G.setEindex(G.numberOfV());
        ArrayList<Edge> edgesPlaced = new ArrayList<Edge>();
        EdgeWeightedGraph GFinal = new EdgeWeightedGraph(G.numberOfV());
        return MCCRecurCondensed(G, 0, GFinal, edgesPlaced);
    }

    public static int MCCRecurCondensed(EdgeWeightedGraph G, int weightSum, EdgeWeightedGraph GFinal, ArrayList<Edge> placedEdges){
        if (G.numberOfV() - 1 == GFinal.numberOfE()) {
            return weightSum;
        }

        int minEdge = -1;
        Edge targetEdge = null;
        for (int i = 0; i < G.getEindex(); i++) {
            Iterable<Edge> it = G.edges(i);
            if (it != null) {
                for (Edge edg : it) {
                    if (edg.weight() < minEdge || minEdge == -1) {
                        if (!used(placedEdges, edg)) {
                            if(GFinal.numberOfE() < 4 || (GFinal.edges(edg.getW()) == null && GFinal.edges(edg.getV()) == null)){
                                minEdge = edg.weight();
                                targetEdge = edg;
                            }else{
                                if(edg.weight() == 10 && GFinal.numberOfE() == 6){
                                    int stop = 100;
                                }

                                System.out.println("edg.weight() + \" \" + G.numberOfE() = " + edg.weight() + " " + GFinal.numberOfE());
                                if (!isCycleRec(edg, edg.getW(), null, GFinal, GFinal.edges(edg.getW()), null, new ArrayList<Edge>(), 0) && !isCycleRec(edg, edg.getV(), null, GFinal, GFinal.edges(edg.getV()), null, new ArrayList<Edge>(),0)) {
                                    System.out.println("TRUE = " + "TRUE");
                                    minEdge = edg.weight();
                                    targetEdge = edg;
                                }
                                System.out.println("FALSE = " + "OR FALSE");

                            }
                        }
                    }
                }
            }
        }
        System.out.println(" = " + targetEdge.weight());
        GFinal.addEdge(targetEdge);
        placedEdges.add(targetEdge);
        weightSum = weightSum + minEdge;
        return MCCRecurCondensed(G, weightSum,GFinal, placedEdges);

    }


    public static int MCCRrecur(EdgeWeightedGraph G, int weightSum, int numLeft, ArrayList<Edge> placedEdges) {
        if (G.numberOfV() - 1 == numLeft) {
            return weightSum;
        }

        // find the smallest weight
        //for each vertice, get the edges, iterator through
        int minEdge = -1;
        Edge targetEdge = null;
        int targetV = -1;
        int targetW = -1;
        int targetIndex = -1;
        for (int i = 0; i < G.getEindex(); i++) {
            Iterable<Edge> it = G.edges(i);
            if (it != null) {
                for (Edge edg : it) {
                    if (edg.weight() < minEdge || minEdge == -1) {
                        if (!used(placedEdges, edg)) {
                            if(G.numberOfE() <5){
                                int jack = 3;
                            }
                            //if (!isCycleRec(it, placedEdges, edg)) {
                                minEdge = edg.weight();
                                targetEdge = edg;
                                targetV = edg.getV();
                                targetW = edg.getW();
                                targetIndex = i;
                            //}
                        }
                    }
                }
            }
        }
        //TODO: add a check if it is a cycle

        // delete it from everything
        farout:
        for (int j = 0; j < G.getEindex(); j++) {
            Iterable<Edge> it = G.edges(j);
            if (it != null) {
                for (Edge edg : it) {
                    if (edg.getV() == targetV && edg.getW() == targetW) {
                        placedEdges.add(edg);
                        G.deleteEdge(edg, targetIndex);
                        break farout;
                    }
                }
            }
        }

        weightSum = weightSum + minEdge;
        numLeft++;

        return MCCRrecur(G, weightSum, numLeft, placedEdges);


    }

    public static boolean used(ArrayList<Edge> placedEdges, Edge e) {
        for (int i = 0; i < placedEdges.size(); i++) {
            if (placedEdges.get(i).getW() == e.getW() && placedEdges.get(i).getV() == e.getV()) {
                return true;
            }
        }
        return false;
    }

    public static int findNumE(Iterable<Edge> e){
        int count = 0;
        for(Edge ed : e){
            count = count +1;
        }
        return count;
    }

    public static Edge getEdgeFromSet(Iterable<Edge> currentNod, int index){
        int count = 0;
        for(Edge ed : currentNod){
            if(count == index){
                return ed;
            }
            count++;
        }
        return null;
    }

//    public static Iterable<Edge> getSetFromEdge(){
//
//    }


    public static boolean isCycleRec(Edge target, int currentEdgeIndex, Edge currentEdge, EdgeWeightedGraph GFinal, Iterable<Edge> currentNode, Edge prevEdge, ArrayList<Edge> traversed, int numHits){
        if(currentEdge == target){
            return true;
        }
        System.out.println("currentEdgeIndex = " + currentEdgeIndex);
        if(currentEdgeIndex == target.getW() || currentEdgeIndex == target.getV()){
            numHits++;
            System.out.println("numHits = " + numHits);
        }
        if(numHits > 1){
            return true;
        }
        for(Edge ed : currentNode){
            if(traversed.contains(ed)){
                continue;
            }
            traversed.add(ed);
            if(ed == prevEdge){
                if(findNumE(currentNode) <2){
                    return false;
                }else{
                    continue;
                }
            }else{
                prevEdge = ed;
            }
            if(ed.getW() == currentEdgeIndex){
                currentEdgeIndex = ed.getV();
                if(currentEdgeIndex == target.getW() || currentEdgeIndex == target.getV()){
                    numHits++;
                    System.out.println("numHits = " + numHits);
                }
                if(numHits > 1){
                    return true;
                }
            }else{
                currentEdgeIndex = ed.getW();
                if(currentEdgeIndex == target.getW() || currentEdgeIndex == target.getV()){
                    numHits++;
                    System.out.println("numHits = " + numHits);
                }
                if(numHits > 1){
                    return true;
                }
            }
            boolean out = isCycleRec(target, currentEdgeIndex, ed, GFinal, GFinal.edges(currentEdgeIndex),prevEdge, traversed, numHits);
            if(out){// getEdgeFromSet(currentNode, count))){
                return true;
            }else{
                continue;
            }
        }
        return false;

    }


    public static boolean isCycle(Iterable<Edge> e, ArrayList<Edge> placedEdges, Edge eItself) {
        // starting at e, iterate through the paths using the placed edges,
        // if you ever get back to e, reutnr true, else return false
        if (placedEdges.size() < 1) {
            return false;
        }
        int currentPos = eItself.getW();
        Edge currentEdg = eItself;
        int count = 0;
        int eLen = findNumE(e);
        while (eItself.getV() != currentPos && count < placedEdges.size() * 2) {
            for (int j = 0; j < 2; j++) {
                if (j == 1) {
                    currentPos = eItself.getV();
                }
                for (int i = 0; i < eLen ; i++) {
                    if (placedEdges.get(i).getV() == currentPos && placedEdges.get(i).getW() != currentEdg.getW()) {
                        count = count + 2;
                        currentPos = placedEdges.get(i).getW();
                        currentEdg = placedEdges.get(i);
                        i = 0;
                    } else if (placedEdges.get(i).getW() == currentPos && placedEdges.get(i).getV() != currentEdg.getV()) {
                        currentPos = placedEdges.get(i).getV();
                        currentEdg = placedEdges.get(i);
                        count = count + 2;
                        i = 0;
                    } else {
                        if(j == 1){
                            return false;
                        }
                        continue;

                    }
                }
            }
        }
        return true;
    }


}

