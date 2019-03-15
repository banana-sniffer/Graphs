package graph;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.TreeSet;


/** New class that extends a Queue that uses a Treeset.
 * @author William Tai
 * */

public class DJKstra extends AbstractQueue<Integer> {
    /** The treeset that will be used in the fringe later on. */
    private TreeSet<Integer> queue;

    /** Creates a class DJKstra. */
    DJKstra() {
        queue = new TreeSet<>();
    }

    @Override
    public Iterator<Integer> iterator() {
        return queue.iterator();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean add(Integer integer) {
        if (queue.contains(integer)) {
            return false;
        } else {
            queue.add(integer);
            return true;
        }
    }

    @Override
    public boolean offer(Integer integer) {
        if (integer == null) {
            throw new NullPointerException("get out of here");
        }
        if (queue.contains(integer)) {
            return false;
        } else {
            queue.add(integer);
            return true;
        }
    }

    @Override
    public Integer peek() {
        return queue.first();
    }

    @Override
    public Integer poll() {
        int temp = queue.first();
        queue.remove(temp);
        return temp;
    }


}
