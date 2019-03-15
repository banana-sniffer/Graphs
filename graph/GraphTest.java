package graph;

import org.junit.Test;


import java.util.ArrayList;
import java.util.TreeSet;

import static org.junit.Assert.*;

/** Unit tests for the Graph class.
 *  @author William Tai
 */
public class GraphTest {


    @Test
    public void emptyGraph() {
        DirectedGraph g = new DirectedGraph();
        assertEquals("Initial graph has vertices", 0, g.vertexSize());
        assertEquals("Initial graph has edges", 0, g.edgeSize());
    }

    @Test
    public void dontStopMeNow() {
        ArrayList<int[]> imHavingSuch = new ArrayList<int[]>();
        UndirectedGraph aGoodTime = new UndirectedGraph();
        for (int i = 0; i < 7; i++) {
            aGoodTime.add();
        }
        aGoodTime.add(1, 2);
        aGoodTime.add(1, 5);
        aGoodTime.add(7, 2);
        aGoodTime.add(4, 3);
        Iteration<int[]> imHavingABall = aGoodTime.edges();
        imHavingABall.iterator();
        while (imHavingABall.hasNext()) {
            imHavingSuch.add(imHavingABall.next());
        }
        assertEquals(imHavingSuch.get(0)[0], 1);
        assertEquals(imHavingSuch.get(0)[1], 2);
        assertEquals(imHavingSuch.get(1)[0], 2);
        assertEquals(imHavingSuch.get(1)[1], 1);
        assertEquals(imHavingSuch.get(2)[0], 1);
        assertEquals(imHavingSuch.get(2)[1], 5);
    }

    @Test
    public void stirFry() {
        DirectedGraph wristTwistinLikeAStirFry = new DirectedGraph();
        for (int i = 0; i < 6; i++) {
            wristTwistinLikeAStirFry.add();
        }
        wristTwistinLikeAStirFry.add(1, 2);
        wristTwistinLikeAStirFry.add(4, 3);
        wristTwistinLikeAStirFry.add(1, 6);
        wristTwistinLikeAStirFry.add(4, 5);
        wristTwistinLikeAStirFry.add(1, 5);
        wristTwistinLikeAStirFry.add(6, 5);
        wristTwistinLikeAStirFry.add(2, 4);
        Iteration<Integer> curryChicken =
                wristTwistinLikeAStirFry.successors(1);
        ArrayList<Integer> takeoff = new ArrayList<>();
        while (curryChicken.hasNext()) {
            takeoff.add(curryChicken.next());
        }
        assertTrue(takeoff.contains(2));
        assertTrue(takeoff.contains(5));
        assertTrue(takeoff.contains(6));
    }

    @Test
    public void allOfTheLights() {
        DirectedGraph lights = new DirectedGraph();
        for (int i = 0; i < 7; i++) {
            lights.add();
        }
        lights.add(1, 2);
        lights.add(1, 4);
        lights.add(2, 3);
        lights.add(3, 6);
        lights.add(3, 7);
        lights.add(4, 5);
        lights.add(5, 7);
        lights.add(6, 7);
        ArrayList<Integer> ghettoUni = new ArrayList<>();
        for (int elem: lights.vertices()) {
            ghettoUni.add(elem);
        }
        assertTrue(ghettoUni.contains(1));
        assertTrue(ghettoUni.contains(2));
        assertTrue(ghettoUni.contains(3));
        assertTrue(ghettoUni.contains(4));
        assertTrue(ghettoUni.contains(5));
        assertTrue(ghettoUni.contains(6));
        assertTrue(ghettoUni.contains(7));
    }

    @Test
    public void sizeDirectedTest() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 7; i++) {
            g.add();
        }
        assertEquals(g.vertexSize(), 7);
        DirectedGraph g1 = new DirectedGraph();
        g.add();
        assertEquals(g1.vertexSize(), 0);
        DirectedGraph g2 = new DirectedGraph();
        assertEquals(g2.vertexSize(), 0);
    }

    @Test
    public void sizeUndirectedTest() {
        UndirectedGraph g = new UndirectedGraph();
        for (int i = 0; i < 5; i++) {
            g.add();
        }
        assertEquals(g.vertexSize(), 5);
        UndirectedGraph g1 = new UndirectedGraph();
        assertEquals(g1.vertexSize(), 0);
    }

    @Test
    public void edgeSizeDirected() {
        DirectedGraph g = new DirectedGraph();
        for (int i = 0; i < 9; i++) {
            g.add();
        }
        for (int i = 0; i < 7; i++) {
            g.add(1, i + 1);
        }
        assertEquals(g.edgeSize(), 7);
    }

    @Test
    public void misc() {
        TreeSet<Integer> temp = new TreeSet<>();
        temp.add(3);
        temp.add(2);
        temp.add(1);
        assertTrue("This is true", temp.contains(3));
        assertTrue("This is also true", temp.contains(2));
    }

    @Test
    public void hollaBackGirl() {
        DirectedGraph thisMyShit = new DirectedGraph();
        for (int i = 0; i < 10; i++) {
            thisMyShit.add();
        }
        thisMyShit.add(1, 2);
        thisMyShit.add(1, 3);
        thisMyShit.add(1, 4);
        thisMyShit.add(1, 8);
        thisMyShit.add(2, 1);
        thisMyShit.add(2, 3);
        thisMyShit.add(2, 5);
        thisMyShit.add(2, 6);
        thisMyShit.add(3, 1);
        thisMyShit.add(3, 2);
        thisMyShit.add(3, 7);
        thisMyShit.add(3, 8);
        thisMyShit.add(4, 1);
        thisMyShit.add(5, 2);
        thisMyShit.add(6, 2);
        thisMyShit.add(7, 3);
        thisMyShit.add(7, 10);
        thisMyShit.add(8, 1);
        thisMyShit.add(8, 3);
        thisMyShit.add(8, 8);
        thisMyShit.add(8, 9);
        thisMyShit.add(8, 10);
        thisMyShit.add(9, 8);
        thisMyShit.add(10, 7);
        thisMyShit.add(10, 8);
        DepthFirstTraversal pomPomsDown
                = new DepthFirstTraversal(thisMyShit);
        pomPomsDown.traverse(1);
    }
}
