package graph;

/* See restrictions in Graph.java. */

import java.util.ArrayList;

/** Represents a general unlabeled directed graph whose vertices are denoted by
 *  positive integers. Graphs may have self edges.
 *
 *  @author William Tai
 */
public class DirectedGraph extends GraphObj {

    @Override
    public boolean isDirected() {
        return true;
    }

    @Override
    public int inDegree(int v) {
        int temp = 0;
        for (int i = 0; i < giveMeMyEdge().size(); i++) {
            if (giveMeMyEdge().get(i)[1] == v) {
                temp += 1;
            }
        }
        return temp;
    }

    @Override
    public Iteration<Integer> predecessors(int v) {
        ArrayList<Integer> prepubscent = new ArrayList<>();
        for (int[] x : giveMeMyEdge()) {
            if (x[1] == v) {
                prepubscent.add(x[0]);
            }
        }
        return Iteration.iteration(prepubscent);
    }

}
