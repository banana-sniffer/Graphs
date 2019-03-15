package graph;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.LinkedList;

/** a linked list that uses an abstractqueue.
 * @author William Tai
 */
public class LfifoL extends AbstractQueue<Integer> {
    /** a queue. */
    private LinkedList<Integer> queue;

    /** Creates a new LfifoL class. */
    LfifoL() {
        queue = new LinkedList<>();
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
            throw new NullPointerException("Error: get out of here");
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
        return queue.getFirst();
    }

    @Override
    public Integer poll() {
        return queue.remove();
    }


}
