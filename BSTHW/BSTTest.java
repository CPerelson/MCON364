package BST;
import BST.BSTInterface;
import BST.BinarySearchTree;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;

public class BSTTest {

    @Test
    public void testAdd() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(8);
        tree.add(1);

        assertEquals(4, tree.size());
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(8));
    }

    @Test
    public void testContains() {
        BinarySearchTree<String> tree = new BinarySearchTree<>();
        tree.add("C");
        tree.add("B");
        tree.add("D");

        assertTrue(tree.contains("C"));
        assertFalse(tree.contains("D"));
    }

    @Test
    public void testMinMax() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(8);
        tree.add(1);

        assertEquals(Integer.valueOf(1), tree.min());
        assertEquals(Integer.valueOf(8), tree.max());
    }

    @Test
    public void testIteratorInOrder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(8);
        tree.add(1);

        Iterator<Integer> iter = tree.getIterator(BSTInterface.Traversal.Inorder);
        int[] expected = { 1, 3, 5, 8 };
        int i = 0;
        while (iter.hasNext()) {
            assertEquals(Integer.valueOf(expected[i++]), iter.next());
        }
    }
    
    @Test
    public void testEmptyTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        assertTrue(tree.isEmpty());
        assertNull(tree.min());
        assertNull(tree.max());
    }

}
