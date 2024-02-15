package BST;

import java.util.Iterator;

public class BSTMain {
    public static void main(String[] args) {
        BinarySearchTree<Character> binarySearchTree = new BinarySearchTree<>();
        Iterator<Character> iterator;
        binarySearchTree.add('P'); binarySearchTree.add('C'); binarySearchTree.add('R'); binarySearchTree.add('T');
        binarySearchTree.add('E'); binarySearchTree.add('F'); binarySearchTree.add('H'); binarySearchTree.add('J');
        binarySearchTree.add('X'); binarySearchTree.add('Y'); binarySearchTree.add('U');

        //inorder
        System.out.println("Inorder: ");
        iterator = binarySearchTree.getIterator(BSTInterface.Traversal.Inorder);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        //preorder
        System.out.println("\nPreorder: ");
        iterator = binarySearchTree.getIterator(BSTInterface.Traversal.Preorder);
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //postorder
        System.out.println("\nPostorder: ");
        iterator = binarySearchTree.getIterator(BSTInterface.Traversal.Postorder);
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}

