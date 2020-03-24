package hw4;

import static org.junit.Assert.*;

public class EdgeTest {

    private Edge normal = new Edge("a", "b");
    private Edge normal_1 = new Edge("a", "b");
    private Edge normal_with_label_c = new Edge("z", "b", "c");
    private Edge normal_with_label_c_1 = new Edge("a", "z", "c");
    private Edge normal_with_long_label = new Edge("a", "b", "cde");
    private Edge normal_with_long_label_1 = new Edge("a", "b", "cdf");
    private Edge normal_with_label_d = new Edge("a", "b", "d");
    private Edge normal_with_same_label_from = new Edge("a", "b", "a");
    private Edge normal_with_same_label_to = new Edge("a", "b", "b");

    // Special
    private Edge reflective = new Edge("a", "a");
    private Edge reflective_1 = new Edge("a", "a");
    private Edge reflective_with_label_b = new Edge("a", "a", "b");
    private Edge reflective_with_same_label = new Edge ("a", "a", "a");

    @org.junit.Test
    public void getFrom() {
        assertEquals("a", normal.getFrom());
        assertEquals("z", normal_with_label_c.getFrom());
        assertEquals("a", normal_with_same_label_from.getFrom());
        assertEquals("a", normal_with_same_label_to.getFrom());
        assertEquals("a", reflective.getFrom());
        assertEquals("a", reflective_with_label_b.getFrom());
    }

    @org.junit.Test
    public void getTo() {
        assertEquals("b", normal.getTo());
        assertEquals("b", normal_with_label_d.getTo());
        assertEquals("b", normal_with_same_label_from.getTo());
        assertEquals("b", normal_with_same_label_to.getTo());
        assertEquals("a", reflective.getTo());
        assertEquals("a", reflective_with_same_label.getTo());
        assertEquals("a", reflective_with_label_b.getTo());
    }

    @org.junit.Test
    public void getName() {
        assertEquals("", normal.getName());
        assertEquals("c", normal_with_label_c.getName());
        assertEquals("a", normal_with_same_label_from.getName());
        assertEquals("b", normal_with_same_label_to.getName());
        assertEquals("", reflective.getName());
        assertEquals("a", reflective_with_same_label.getName());
        assertEquals("b", reflective_with_label_b.getName());
    }

    // "" < "a" < "z"
    @org.junit.Test
    public void compareTo() {
        // Normal Cases
        assertTrue(normal.compareTo(normal_with_same_label_from)  < 0);
        assertTrue(normal_with_label_c.compareTo(normal_with_label_d) < 0);

        // Cross Comparison between reflective and normal edge
        assertTrue(reflective_with_label_b.compareTo(reflective) > 0);
        assertTrue(normal_with_label_d.compareTo(reflective_with_label_b) > 0);
        assertTrue(normal_with_same_label_from.compareTo(normal_with_same_label_to) < 0);
        assertTrue(reflective_with_label_b.compareTo(normal_with_label_c_1) < 0);

        // Comparison of labels with different length
        assertTrue(normal_with_long_label.compareTo(normal_with_long_label_1) < 0);
        assertTrue(normal_with_long_label.compareTo(normal_with_label_c) > 0);

        // Mixed Node/Edge Compare
       assertTrue(normal_with_label_c_1.compareTo(normal_with_long_label) > 0);

       // Equals
        assertEquals(0,normal.compareTo(normal_1));
        assertEquals(0,reflective.compareTo(reflective_1));
    }
}