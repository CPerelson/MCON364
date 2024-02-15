package BST;

public class BSTNode<T> {

    private T data; // The node info
    private BSTNode<T> left; // A link to the left child node
    private BSTNode<T> right; // A link to the right child node

    public BSTNode(T info) {
        this.data = info;
        left = null;
        right = null;
    }

    public void setInfo(T info) {
        this.data = info;
    }

    public T getInfo() {
        return data;
    }

    public void setLeft(BSTNode<T> link) {
        left = link;
    }

    public void setRight(BSTNode<T> link) {
        right = link;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

