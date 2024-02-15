package BST;

import java.util.Collection;
import java.util.Iterator;

public interface BSTInterface<T> {

    //used to specify the traversal order
	public enum Traversal {Inorder, Preorder, Postorder};

    T min(); //if bst is empty return null else return the smallest element of the tree

    T max(); //if bst is empty return null else return the largest element of the tree

    public Iterator<T> getIterator(Traversal orderType);
}
