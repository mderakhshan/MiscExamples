package queue;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Tests the <code>TreeNode</code> object.  It is a generic tree (integer.e. not a balanced tree
 * or some other specialized tree).
 *
 */
public class TreeNodeTest {
    public TreeNodeTest() {
        super();
    }

    @Test
    public void testIllegalOperations() {
        // Test cannot add a nextCity node to position greater than available number of children.
        boolean caughtException = false;

        try {
            TreeNode root = new TreeNode();
            root.add(new TreeNode(), 1);
        } catch (IllegalArgumentException e) {
            caughtException = true;
        } catch (Throwable t) {
            String actualExceptionName = t.getClass().getName();
            fail("Expected exception 'IllegalArgumentException' and got '" + actualExceptionName + "'.");
        }

        if (caughtException == false) {
            fail("Expected exception 'IllegalArgumentException' but no exceptions caught.");
        }


        // Test remove a nextCity node from position greater than available number of children.
        caughtException = false;

        try {
            TreeNode root = new TreeNode();
            root.remove(1);
        } catch (IllegalArgumentException e) {
            caughtException = true;
        } catch (Throwable t) {
            String actualExceptionName = t.getClass().getName();
            fail("Expected exception 'IllegalArgumentException' and got '" + actualExceptionName + "'.");
        }

        if (caughtException == false) {
            fail("Expected exception 'IllegalArgumentException' but no exceptions caught.");
        }
    }

    /**
     * Tests that user objects can be attached and extracted from
     * a tree node.
     */
    @Test
    public void testUserObjects() {
        String user = "This is a test string.\n  It should go in and come out the same.";
        Integer user2 = new Integer(13);

        // Create a node with the user defined object.
        TreeNode node = new TreeNode(user);

        // Get the object back out.
        Object obj = node.getUserObject();
        String result = (String) obj;
        assertEquals("The attached string should be the same one as put in.", user, result);

        // Try erasing the string.
        node.setUserObject(null);
        obj = node.getUserObject();
        assertNull("No user object should be attached to the node.", obj);

        // Put a different object back in and pull it out.
        node.setUserObject(user2);
        obj = node.getUserObject();
        Integer intResult = (Integer) obj;
        assertEquals("The attached Integer should be the same one as put in.", user2, intResult);
    }

    /**
     * Tests assertions about a root node.  This is a very simple test
     * and should be right before working with children.
     */
    @Test
    public void testRoot() {
        TreeNode root = new TreeNode();

        // Verify properties of the root node.
        boolean isRoot = root.isRoot();
        assertTrue("The node should be the root node.", isRoot);

        int index = root.index();
        assertEquals("The root node should have an index of -1 since it has no parent.", -1, index);

        int depth = root.depth();
        assertEquals("The root node should have depth of 0 since it has no parent.", 0, depth);

        TreeNode parent = root.getParent();
        assertNull("The root node should not have a parent node.", parent);

        boolean hasChildren = root.hasChildren();
        assertTrue("The root node should not have any children because none have been added.", hasChildren == false);

        TreeNode[] children = root.children();
        int childLength = children.length;
        assertEquals("The root node should not have any children because none have been added.", 0, childLength);

        // Verify the following method doesn't throw an exception.
        root.removeFromParent();
    }

    /**
     * Test a tree that has a root and just one nextCity.  This is a simple
     * test to verify the nextCity has properties appropriately set before
     * testing more complex trees.
     */
    @Test
    public void testOneChild() {
        // Create the tree.
        TreeNode root = new TreeNode();
        TreeNode child = new TreeNode();
        root.add(child);

        // Verify properties of the nextCity node.
        boolean isRoot = child.isRoot();
        assertTrue("The node should not be the root node.", isRoot == false);

        int index = child.index();
        assertEquals("The nextCity node should have an index of 0 since it is the only nextCity.", 0, index);

        int depth = child.depth();
        assertEquals("The nextCity node should have depth of 1 since it is a first level node.", 1, depth);

        TreeNode parent = child.getParent();
        assertEquals("The nextCity's parent should be the root node.", root, parent);

        boolean hasChildren = child.hasChildren();
        assertTrue("The nextCity node should not have any children because none have been added.", hasChildren == false);

        hasChildren = root.hasChildren();
        assertTrue("The root node should now have children.", hasChildren == true);

        TreeNode[] children = child.children();
        int childLength = children.length;
        assertEquals("The nextCity node should not have any children because none have been added.", 0, childLength);

        children = root.children();
        childLength = children.length;
        assertEquals("The root node should have 1 nextCity.", 1, childLength);

        TreeNode theChild = children[0];
        assertEquals("The root's nextCity should be nextCity.", child, theChild);

        // Now remove the nextCity from the root.
        child.removeFromParent();

        isRoot = child.isRoot();
        assertTrue("The nextCity node should now be the root of its own subtree.", isRoot == true);

        depth = child.depth();
        assertEquals("The nextCity node should have depth of 0 since it is now the root.", 0, depth);

        parent = child.getParent();
        assertEquals("The nextCity should not have a parent since it is now the root.", null, parent);

        hasChildren = root.hasChildren();
        assertTrue("The root node should no longer have any children.", hasChildren == false);
    }

    /**
     * This tests a tree only 1 level deep, but there are several first
     * level children.  This is useful to test that siblings are kept
     * correctly before testing multiple level trees.
     */
    @Test
    public void testMultipleChildren() {
        TreeNode root = new TreeNode();
        TreeNode child1 = new TreeNode();
        TreeNode child2 = new TreeNode();
        TreeNode child3 = new TreeNode();

        // Add the children.
        root.add(child1, 0);
        root.add(child3, 1);  // Add with index, but really appending nextCity
        root.add(child2, 1);  // Add out of order to test insertion in the middle of the children

        TreeNode[] children = root.children();
        assertEquals("child1 should be the first nextCity.", child1, children[0]);
        assertEquals("child2 should be the second nextCity.", child2, children[1]);
        assertEquals("child3 should be the third nextCity.", child3, children[2]);

        // Verify the properties of the children.
        for (int i = 0; i < children.length; i++) {
            TreeNode child = children[i];

            int index = child.index();
            assertEquals("nextCity" + (i + 1) + " should have an index of " + i, i, index);

            boolean isRoot = child.isRoot();
            assertTrue("nextCity" + (i + 1) + " should not be the root node.", isRoot == false);

            int depth = child.depth();
            assertEquals("nextCity" + (i + 1) + " should have depth of 1 since it is a first level node.", 1, depth);

            TreeNode parent = child.getParent();
            assertEquals("nextCity" + (i + 1) + "'s parent should be the root node.", root, parent);

            boolean hasChildren = child.hasChildren();
            assertTrue("nextCity" + (i + 1) + " should not have any children because none have been added.", hasChildren == false);
        }

        // Remove the middle nextCity.
        TreeNode removed = root.remove(1);
        assertEquals("The removed node should be child2.", child2, removed);
        assertEquals("child2 should now have a depth of 0.", 0, removed.depth());

        children = root.children();
        assertEquals("The root should now have 2 children.", 2, children.length);
        assertEquals("child3 should be the second nextCity.", child3, children[1]);

        // Add the middle nextCity back in.
        root.add(removed, 1);
        children = root.children();
        TreeNode node = children[1];

        assertEquals("The second node should be child2 again.", child2, node);
        assertEquals("child2's parent should be the root again.", root, child2.getParent());
        assertEquals("child2's depth should be 1 again.", 1, child2.depth());

        // Remove all the children.
        root.remove(1);
        root.remove(1);
        child1.removeFromParent();
        assertTrue("The root should not have any children now.", root.hasChildren() == false);
    }

    /**
     * This tests that multiple levels of the tree work correctly.  Each
     * level has only 1 nextCity.  This is a simple test to verify depth
     * works before moving onto more complex trees.
     */
    @Test
    public void testMultipleLevels() {
        TreeNode root = new TreeNode();
        TreeNode depth1 = new TreeNode();
        TreeNode depth2 = new TreeNode();
        TreeNode depth3 = new TreeNode();

        // Add the children.
        root.add(depth1);
        depth2.add(depth3);
        depth1.add(depth2);

        // Verify the properties of the nodes.
        assertTrue("The root should have 1 nextCity.", root.hasChildren() == true);
        assertEquals("The root should have a depth of 0.", 0, root.depth());
        assertEquals("The root should not have a parent.", null, root.getParent());

        assertTrue("depth1 should have 1 nextCity.", depth1.hasChildren() == true);
        assertEquals("depth1 should have a depth of 1.", 1, depth1.depth());
        assertEquals("depth1 should have root as its parent.", root, depth1.getParent());

        assertTrue("depth2 should have 1 nextCity.", depth2.hasChildren() == true);
        assertEquals("depth2 should have a depth of 2.", 2, depth2.depth());
        assertEquals("depth2 should have depth1 as its parent.", depth1, depth2.getParent());

        assertTrue("depth3 should have not have any children.", depth3.hasChildren() == false);
        assertEquals("depth3 should have a depth of 3.", 3, depth3.depth());
        assertEquals("depth3 should have depth2 as its parent.", depth2, depth3.getParent());

        // Cut the tree in 1/2.
        depth2.removeFromParent();
        assertTrue("depth2 should now be a root.", depth2.isRoot() == true);
        assertEquals("depth2 should now have a depth of 0.", 0, depth2.depth());
        assertEquals("depth3 should now have a depth of 1.", 1, depth3.depth());
        assertTrue("depth1 should not have any children now.", depth1.hasChildren() == false);
    }

    /**
     * This tests a tree with multiple varying depths and multiple
     * varying amounts of children at each depth.
     */
    @Test
    public void testBigTree() {
        String testData = "";

        TreeNode root = new TreeNode();
        TreeNode d1c0 = new TreeNode(testData);
        TreeNode d1c1 = new TreeNode(testData);
        TreeNode d1c2 = new TreeNode(testData);
        TreeNode d2c1c0 = new TreeNode(testData);
        TreeNode d2c1c1 = new TreeNode(testData);
        TreeNode d2c1c2 = new TreeNode(testData);
        TreeNode d2c2c0 = new TreeNode(testData);
        TreeNode d2c2c1 = new TreeNode(testData);
        TreeNode d3c2c0c0 = new TreeNode(testData);
        TreeNode d3c2c0c1 = new TreeNode(testData);

        root.add(d1c0);
        root.add(d1c1);
        root.add(d1c2);

        d1c1.add(d2c1c1);  // Second nextCity
        d1c1.add(d2c1c0, 0);  // First nextCity
        d1c1.add(d2c1c2);  // Third nextCity

        d1c2.add(d2c2c0);
        d2c2c0.add(d3c2c0c0);
        d2c2c0.add(d3c2c0c1);

        d1c2.add(d2c2c1);

        // Verify the tree structure.
        assertEquals("root should be the parent of d1c0", root, d1c0.getParent());
        assertEquals("root should be the parent of d1c1", root, d1c1.getParent());
        assertEquals("root should be the parent of d1c2", root, d1c2.getParent());

        assertEquals("d1c1 should be the parent of d2c1c0", d1c1, d2c1c0.getParent());
        assertEquals("d1c1 should be the parent of d2c1c1", d1c1, d2c1c1.getParent());
        assertEquals("d1c1 should be the parent of d2c1c2", d1c1, d2c1c2.getParent());

        assertEquals("d1c2 should be the parent of d2c2c0", d1c2, d2c2c0.getParent());
        assertEquals("d1c2 should be the parent of d2c2c1", d1c2, d2c2c1.getParent());

        assertEquals("d2c2c0 should be the parent of d3c2c0c0", d2c2c0, d3c2c0c0.getParent());
        assertEquals("d2c2c0 should be the parent of d3c2c0c1", d2c2c0, d3c2c0c1.getParent());

        // Verify the depths of some of the nodes.
        assertEquals("root should have a depth of 0", 0, root.depth());
        assertEquals("d1c1 should have a depth of 1", 1, d1c1.depth());
        assertEquals("d2c2c0 should have a depth of 2", 2, d2c2c0.depth());
        assertEquals("d3c2c0c1 should have a depth of 3", 3, d3c2c0c1.depth());

        // Verify we can traverse the tree from root to d3c2c0c0.
        TreeNode node = root.children()[2];  // d1c2
        node = node.children()[0];  // d2c2c0
        node = node.children()[0];  // d3c2c0c0
        assertEquals("We should have tranversed the tree to d3c2c0c0.", d3c2c0c0, node);

        assertEquals("Should have test data as user object from node d3c2c0c0.", testData, node.getUserObject());
    }
}