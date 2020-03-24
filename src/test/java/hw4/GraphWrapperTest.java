package hw4;

import com.sun.source.tree.Tree;
import org.junit.Before;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class GraphWrapperTest {

    GraphWrapper g = new GraphWrapper();

    @Before
    public void spinUp() {
        // Build a Graph
        // Constructing graph
        g.addNode("a");
        g.addNode("b");
        g.addNode("c");
        g.addNode("d");
        g.addNode("ad");

        g.addEdge("a", "b", "");
        g.addEdge("b", "c", "");
        g.addEdge("c", "d", "");
        g.addEdge("a", "d", "edge1");
        g.addEdge("a", "a", "");
        g.addEdge("ad", "a", "");
    }

    @org.junit.Test
    public void addNode() {
        // Add new node
       g.addNode("e");
       g.addNode("a");

        // Get Nodes
        TreeSet<String> tree= new TreeSet<>(Set.of("a", "b", "c", "d", "e", "ad"));
        assertEquals("pass", testIteratorAgainstSet(g.listNodes(), tree));
    }

    public static String testIteratorAgainstSet(Iterator<String> i, Set<String> s) {
        Iterator<String> t = s.iterator();
        while (t.hasNext()) {
            String test = t.next();
            String ans = i.next();
            if (!test.equals(ans)) {
                return "order does not satisfied: expected " + ans + ", got : " + test;
            }
        }
        if (i.hasNext()) {
            return "contains extra items";
        }

        return "pass";
    }

    @org.junit.Test
    public void addEdge() {
        g.addEdge("a", "d", "edge1");
        g.addEdge("a", "a", "");
        g.addEdge("b", "b", "");

        // Answers
        TreeSet<String> a = new TreeSet<>();
        TreeSet<String> b = new  TreeSet<>();
        TreeSet<String> c = new  TreeSet<>();
        TreeSet<String> ad = new  TreeSet<>();

        a.add("a()");
        a.add("b()");
        a.add("d(edge1)");

        b.add("b()");
        b.add("c()");
        c.add("d()");
        ad.add("a()");

        // Get children
        assertEquals("pass", testIteratorAgainstSet(g.listChildren("a"), a));
        assertEquals("pass", testIteratorAgainstSet(g.listChildren("b"), b));
        assertEquals("pass", testIteratorAgainstSet(g.listChildren("c"), c));
        assertEquals("pass", testIteratorAgainstSet(g.listChildren("d"), Set.of()));
        assertEquals("pass", testIteratorAgainstSet(g.listChildren("ad"), ad));
        assertEquals("pass", testIteratorAgainstSet(g.listChildren("f"), Set.of()));
    }
}