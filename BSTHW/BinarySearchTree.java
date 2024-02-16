package BST;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {

    private BSTNode<T> root;
    private int counter = 0;

    public void add(T data) {
        if (root == null) {
            root = new BSTNode<>(data);
        } else {
            addHelper(data, root);
        }
    }

    private void addHelper(T data, BSTNode<T> node) {
        int compare = data.compareTo(node.getData());
        if (compare > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTNode<>(data));
            } else {
                addHelper(data, node.getRight());
            }
        } else {
            if (node.getLeft() == null) {
                node.setLeft(new BSTNode<>(data));
            } else {
                addHelper(data, node.getLeft());
            }
        }
    }

    public boolean contains(T target) {
        System.out.println("check if the binary search tree contains the target element");
        return containsHelper(target, root);
    }

    private boolean containsHelper(T target, BSTNode<T> node) {
        System.out.println("check if the subtree contains the target element");
        if (node == null) {
            System.out.println("target element is not found in the subtree");
            return false;
        }
        int compare = target.compareTo(node.getData());
        if (compare < 0) {
            System.out.println("Target element is smaller than the current node, searching in the left subtree");
            return containsHelper(target, node.getLeft());
        } else if (compare > 0) {
            System.out.println("Target element is larger than the current node, searching in the right subtree");
            return containsHelper(target, node.getRight());
        } else {
            System.out.println("Target element found in subtree");
            return true;
        }
    }

    public boolean isEmpty() {
        System.out.println("check if BST is empty");
        if (root == null) {
            return true;
        } else {
            return false;
        }
    }

    public T min() {
        System.out.println("find the minimum element ");
        if (isEmpty()) {
            return null;
        }
        BSTNode<T> node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getInfo();
    }

    public T max() {
        System.out.println("Finding the maximum element");
        if (isEmpty()) {
            return null;
        }

        BSTNode<T> node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getInfo();
    }

    public int size() {
        System.out.println("get the size of the binary search tree");
        return counter;
    }

    public Iterator<T> getIterator(Traversal orderType) {
        System.out.println("Creating an iterator for traversing the binar search tree");

        final LinkedQueue<T> infoQueue = new LinkedQueue();
        if (orderType == Traversal.Preorder) {
            System.out.println("Traversing the tree in pre-order traversal to populate the iterator queue");
            preOrder(root, infoQueue);
        } else if (orderType == Traversal.Inorder) {
            System.out.println("Traversing the tree in in-order traversal to populate the iterator queue");
            inOrder(root, infoQueue);
        } else if (orderType == Traversal.Postorder) {
            System.out.println("Traversing the tree in post-order traversal to populate the iterator queue");
            postOrder(root, infoQueue);
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                System.out.println("check if the iterator has more elements");
                System.out.println();
                return !infoQueue.isEmpty();
            }

            @Override
            public T next() {
                System.out.println("get the next element from the iterator queue");
                if (!hasNext()) {
                    throw new IndexOutOfBoundsException("Illegal invocation of next in binary search tree");
                }
                return infoQueue.dequeue();
            }

            public void remove() {
                System.out.println("Unsupported remove attemped on binary search tree iterator");
                throw new UnsupportedOperationException("Unsupported remove attempted on binary search tree iterator");
            }
        };
    }

    private void inOrder(BSTNode<T> node, LinkedQueue<T> queue) {
        if (node == null)
            return;

        // Visit current node
        System.out.println("Visiting node: " + node.getData());
        // Traverse left subtree
        inOrder(node.getLeft(), queue);
        queue.enqueue(node.getData());
        // Traverse right subtree
        inOrder(node.getRight(), queue);
    }

    // Pre-order traversal
    private void preOrder(BSTNode<T> node, LinkedQueue<T> queue) {
        if (node == null) {
            return;
        }

        // Visit current node
        System.out.println("Visiting node: " + node.getData());
        queue.enqueue(node.getData());

        // Traverse left subtree
        preOrder(node.getLeft(), queue);

        // Traverse right subtree
        preOrder(node.getRight(), queue);
    }

    // Post-order traversal
    private void postOrder(BSTNode<T> node, LinkedQueue<T> queue) {
        if (node == null) {
            return;
        }

        // Traverse left subtree
        postOrder(node.getLeft(), queue);

        // Traverse right subtree
        postOrder(node.getRight(), queue);

        // Visit current node
        System.out.println("Visiting node: " + node.getData());
        queue.enqueue(node.getData());
    }

    public void breadthFirstSearch() {
        BSTNode<T> node = null;
        if (node != null) {
            LinkedQueue<BSTNode<T>> queue = new LinkedQueue<>();
            queue.enqueue(node);
            while (!queue.isEmpty()) {
                node = queue.dequeue();
                System.out.println(node.getInfo());
                if (node.getLeft() != null) {
                    queue.enqueue(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.enqueue(node.getRight());
                }
            }
        }
    }

    public void depthFirstSearch() {
        BSTNode<T> node = null;
        if (node != null) {
            Stack<BSTNode<T>> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                node = stack.pop();
                System.out.println(node.getInfo());
                if (node.getRight() != null) {
                    stack.push(node.getRight());
                }
                if (node.getLeft() != null) {
                    stack.push(node.getLeft());
                }
            }
        }
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                ", counter=" + counter +
                '}';
    }
}

