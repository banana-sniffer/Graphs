package graph;
import java.util.ArrayList;
import java.util.Collections;

/* See restrictions in Graph.java. */

/** A partial implementation of Graph containing elements common to
 *  directed and undirected graphs.
 *
 *  @author William Tai
 */
abstract class GraphObj extends Graph {

    /** A new, empty Graph. */
    GraphObj() {
        vertices = new ArrayList<Integer>();
        edges = new ArrayList<int[]>();
    }

    @Override
    public int vertexSize() {
        return vertices.size();
    }

    @Override
    public int maxVertex() {
        for (int i = vertices.size() - 1; i >= 0; i--) {
            if (vertices.get(i) != null) {
                return vertices.get(i);
            }
        }
        return 0;
    }

    @Override
    public int edgeSize() {
        if (!isDirected() && edges.size() % 2 == 0) {
            return edges.size() / 2;
        }
        return edges.size();
    }

    @Override
    public abstract boolean isDirected();

    @Override
    public int outDegree(int v) {
        ArrayList<Integer> temp = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i)[0] == v
                    && !temp.contains(edges.get(i)[1])) {
                temp.add(edges.get(i)[1]);
                count += 1;
            }
        }
        return count;
    }

    @Override
    public abstract int inDegree(int v);

    @Override
    public boolean contains(int u) {
        return vertices.contains(u);
    }

    @Override
    public boolean contains(int u, int v) {
        if (contains(u) && contains(v)) {
            for (int[] elem : edges) {
                if (elem[0] == u && elem[1] == v) {
                    return true;
                }
                if (!isDirected()) {
                    if (elem[0] == v && elem[1] == u) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int add() {
        int temp = 0;
        while (temp < vertexSize() && vertices.get(temp) == temp + 1) {
            temp++;
        }
        vertices.add(temp, temp + 1);
        temp += 1;
        return temp;
    }

    @Override
    public int add(int u, int v) {
        checkMyVertex(u);
        checkMyVertex(v);
        if (contains(u, v)) {
            return edgeId(u, v);
        }
        if (!isDirected()) {
            edges.add(new int[]{u, v});
            edges.add(new int[]{v, u});
        } else {
            edges.add(new int[]{u, v});
        }
        return edgeId(u, v);
    }

    @Override
    public void remove(int v) {
        edges.removeIf(x -> x[0] == v || x[1] == v);
        vertices.removeIf(x -> x == v);
    }

    @Override
    public void remove(int u, int v) {
        if (!isDirected()) {
            edges.removeIf(x -> x[0] == u && x[1] == v);
            edges.removeIf(x -> x[0] == v && x[1] == u);
        } else {
            edges.removeIf(x -> x[0] == u && x[1] == v);
        }
    }

    @Override
    public Iteration<Integer> vertices() {
        ArrayList<Integer> cervix = new ArrayList<>();
        for (int elem : vertices) {
            cervix.add(elem);
        }
        return Iteration.iteration(cervix);
    }

    @Override
    public Iteration<Integer> successors(int v) {
        ArrayList<Integer> succIt = new ArrayList<>();
        for (int[] elem : edges) {
            if (!succIt.contains(elem[1]) && elem[0] == v) {
                succIt.add(elem[1]);
            }
        }
        Collections.sort(succIt);
        return Iteration.iteration(succIt);
    }

    @Override
    public abstract Iteration<Integer> predecessors(int v);

    @Override
    public Iteration<int[]> edges() {
        ArrayList<int[]> edgeList = new ArrayList<>();
        for (int[] elem : edges) {
            edgeList.add(elem);
        }
        return Iteration.iteration(edgeList);
    }

    @Override
    protected void checkMyVertex(int v) {
        if (!contains(v)) {
            throw new IllegalArgumentException("You a Fool my dude.");
        }
    }

    @Override
    protected int edgeId(int u, int v) {
        return (((u + v) * (u + v + 1)) / 2) + v;
    }

    /** Used to return edges. */
    ArrayList<int[]> giveMeMyEdge() {
        return edges;
    }

    /** The edges, fool. */
    private ArrayList<int[]> edges;

    /** Your vertices, fool. */
    private ArrayList<Integer> vertices;
}
