package graph;

/* See restrictions in Graph.java. */


/** A partial implementation of ShortestPaths that contains the weights of
 *  the vertices and the predecessor edges.   The client needs to
 *  supply only the two-argument getWeight method.
 *  @author William Tai
 */
public abstract class SimpleShortestPaths extends ShortestPaths {

    /** The shortest paths in G from SOURCE. */
    public SimpleShortestPaths(Graph G, int source) {
        this(G, source, 0);
    }

    /** A shortest path in G from SOURCE to DEST. */
    public SimpleShortestPaths(Graph G, int source, int dest) {
        super(G, source, dest);
    }

    /** Returns the current weight of edge (U, V) in the graph.  If (U, V) is
     *  not in the graph, returns positive infinity. */
    @Override
    protected abstract double getWeight(int u, int v);

    @Override
    public double getWeight(int v) {
        return weights[v];
    }

    @Override
    protected void setWeight(int v, double w) {
        weights[v] = w;
    }

    /** Returns the current predecessor vertex of vertex V in the graph, or 0 if
     *  V is not in the graph or has no predecessor. */
    @Override
    public int getPredecessor(int v) {
        return brothers[v];
    }

    @Override
    protected void setPredecessor(int v, int u) {
        brothers[v] = u;
    }

    /** Returns the brothers. */
    private int[] getBrothers() {
        return brothers;
    }
    /** Returns the weights. */
    private double[] getWeights() {
        return weights;
    }
    /** The brothers they are awesome. */
    private int[] brothers = new int[_G.maxVertex() + 1];

    /** The weights of the array. */
    private double[] weights = new double[_G.maxVertex() + 1];

}
