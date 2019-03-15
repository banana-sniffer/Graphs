package graph;

/* See restrictions in Graph.java. */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.Collections;



/** The shortest paths through an edge-weighted graph.
 *  By overrriding methods getWeight, setWeight, getPredecessor, and
 *  setPredecessor, the client can determine how to represent the weighting
 *  and the search results.  By overriding estimatedDistance, clients
 *  can search for paths to specific destinations using A* search.
 *  @author William Tai
 */
public abstract class ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public ShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public ShortestPaths(Graph G, int source, int dest) {
        _G = G;
        _source = source;
        _dest = dest;
        distances = new ArrayList<>();
        trees = new TreeSet<>(lookatme);
    }

    /** Creates a comparator to be used by the TreeSet. */
    private Comparator<Integer> lookatme = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            double temp = getWeight(o1) + estimatedDistance(o1);
            double temp1 = getWeight(o2) + estimatedDistance(o2);
            if (temp > temp1) {
                return 1;
            } else {
                return -1;
            }
        }
    };

    /** Initialize the shortest paths.  Must be called before using
     *  getWeight, getPredecessor, and pathTo. */
    public void setPaths() {
        for (int elem: _G.vertices()) {
            setWeight(elem, Double.MAX_VALUE);
        }
        setWeight(getSource(), 0.0);
        trees.add(getSource());
        while (!trees.isEmpty()) {
            int v = trees.pollFirst();
            if (v == getDest()) {
                return;
            }
            for (int elem: _G.successors(v)) {
                if (getWeight(v) + getWeight(v, elem) < getWeight(elem)) {
                    trees.remove(elem);
                    setWeight(elem, getWeight(v, elem) + getWeight(v));
                    trees.add(elem);
                    setPredecessor(elem, v);
                }
            }
        }
    }

    /** Returns the starting vertex. */
    public int getSource() {
        return _source;
    }

    /** Returns the target vertex, or 0 if there is none. */
    public int getDest() {
        return _dest;
    }

    /** Returns the current weight of vertex V in the graph.  If V is
     *  not in the graph, returns positive infinity. */
    public abstract double getWeight(int v);

    /** Set getWeight(V) to W. Assumes V is in the graph. */
    protected abstract void setWeight(int v, double w);

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    public abstract int getPredecessor(int v);

    /** Set getPredecessor(V) to U. */
    protected abstract void setPredecessor(int v, int u);

    /** Returns an estimated heuristic weight of the shortest path from vertex
     *  V to the destination vertex (if any).  This is assumed to be less
     *  than the actual weight, and is 0 by default. */
    protected double estimatedDistance(int v) {
        return 0.0;
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    protected abstract double getWeight(int u, int v);

    /** Returns a list of vertices starting at _source and ending
     *  at V that represents a shortest path to V.  Invalid if there is a
     *  destination vertex other than V. */
    public List<Integer> pathTo(int v) {
        ArrayList<Integer> maps = new ArrayList<>();
        while (getPredecessor(v) != 0) {
            maps.add(v);
            v = getPredecessor(v);
        }
        maps.add(getSource());
        Collections.reverse(maps);
        return maps;
    }

    /** Returns a list of vertices starting at the source and ending at the
     *  destination vertex. Invalid if the destination is not specified. */
    public List<Integer> pathTo() {
        return pathTo(getDest());
    }

    /** Returns the distance. */
    private ArrayList<Integer> getDist() {
        return distances;
    }

    /** Returns the trees. */
    private TreeSet<Integer> getTrees() {
        return trees;
    }
    /** The graph being searched. */
    protected final Graph _G;
    /** The starting vertex. */
    private final int _source;
    /** The target vertex. */
    private final int _dest;
    /** The distances. */
    private ArrayList<Integer> distances;
    /** The tree to hold the stuff. */
    private TreeSet<Integer> trees;

}
