package graph;

/* See restrictions in Graph.java. */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Queue;

/** Implements a generalized traversal of a graph.  At any given time,
 *  there is a particular collection of untraversed vertices---the "fringe."
 *  Traversal consists of repeatedly removing an untraversed vertex
 *  from the fringe, visting it, and then adding its untraversed
 *  successors to the fringe.
 *
 *  Generally, the client will extend Traversal.  By overriding the visit
 *  method, the client can determine what happens when a node is visited.
 *  By supplying an appropriate type of Queue object to the constructor,
 *  the client can control the behavior of the fringe. By overriding the
 *  shouldPostVisit and postVisit methods, the client can arrange for
 *  post-visits of a node (as in depth-first search).  By overriding
 *  the reverseSuccessors and processSuccessor methods, the client can control
 *  the addition of neighbor vertices to the fringe when a vertex is visited.
 *
 *  Traversals may be interrupted or restarted, remembering the previously
 *  marked vertices.
 *  @author William Tai
 */
public abstract class Traversal {

    /** A Traversal of G, using FRINGE as the fringe. */
    protected Traversal(Graph G, Queue<Integer> fringe) {
        _G = G;
        _fringe = fringe;
        _visited = new ArrayList<>();
        needPostVisit = new ArrayList<>();
    }

    /** Unmark all vertices in the graph. */
    public void clear() {
        _visited.clear();
    }

    /** Initialize the fringe to V0 and perform a traversal. */
    public void traverse(Collection<Integer> V0) {
        this.clear();
        _fringe.addAll(V0);
        while (!_fringe.isEmpty()) {
            int v = _fringe.remove();
            if (needPostVisited(v)) {
                postVisit(v);
            } else if (!marked(v)) {
                mark(v);
                visit(v);
                if (shouldPostVisit(v)) {
                    _fringe.add(v);
                    needPostVisit.add(v);
                }
                processSuccessors(v);
            }
        }
    }

    /** Items that need to be post visited.
     * @param v it is an integer
     * @return boolean
     * */
    private boolean needPostVisited(int v) {
        if (shouldPostVisit(v) && needPostVisit.contains(v)) {
            needPostVisit.remove(needPostVisit.indexOf(v));
            return true;
        } else {
            return false;
        }
    }

    /** Initialize the fringe to { V0 } and perform a traversal. */
    public void traverse(int v0) {
        traverse(Arrays.<Integer>asList(v0));
    }

    /** Returns true iff V has been marked. */
    protected boolean marked(int v) {
        if (_visited.isEmpty()) {
            return false;
        } else {
            return _visited.contains(v);
        }
    }

    /** Mark vertex V. */
    protected void mark(int v) {
        _visited.add(v);
    }

    /** Perform a visit on vertex V.  Returns false iff the traversal is to
     *  terminate immediately. */
    protected boolean visit(int v) {
        return true;
    }

    /** Return true if we should postVisit V after traversing its
     *  successors.  (Post-visiting generally is useful only for depth-first
     *  traversals, although we define it for all traversals.) */
    protected boolean shouldPostVisit(int v) {
        return false;
    }

    /** Revisit vertex V after traversing its successors.  Returns false iff
     *  the traversal is to terminate immediately. */
    protected boolean postVisit(int v) {
        return true;
    }

    /** Return true if we should schedule successors of V in reverse order. */
    protected boolean reverseSuccessors(int v) {
        return false;
    }

    /** Process the successors of vertex U.  Assumes U has been visited.  This
     *  default implementation simply processes each successor using
     *  processSuccessor. */
    protected void processSuccessors(int u) {
        for (int v : _G.successors(u)) {
            if (processSuccessor(u, v)) {
                _fringe.add(v);
            }
        }
    }

    /** Process successor V to U.  Returns true iff V is then to
     *  be added to the fringe.  By default, returns true iff V is unmarked. */
    protected boolean processSuccessor(int u, int v) {
        return !marked(v);
    }

    /** The graph being traversed. */
    private final Graph _G;
    /** The fringe. */
    protected final Queue<Integer> _fringe;

    /** The visited ones. */
    private ArrayList<Integer> _visited;

    /** The need post visited ones. */
    private ArrayList<Integer> needPostVisit;

}
