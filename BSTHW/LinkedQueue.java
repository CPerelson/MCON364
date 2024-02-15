package BST;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class LinkedQueue<T>{
    private Node<T> front;
    private Node<T> rear;
    private int size;

    private static class Node<T>{
        T data;
        Node<T> next;

        Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    public LinkedQueue(){
        front = null;
        rear = null;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void enqueue(T element) {
        Node<T> newNode = new Node<T>(element);
        if (isEmpty()){
            front = newNode;
        }
        else {
            rear.next = newNode;
            rear = newNode;
        }
        rear = newNode;
        size++;
    }
    public T dequeue(){
        if (isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        T removedElement = front.data;
        front = front.next;
        size--;
        if (isEmpty()){
            rear = null;
        }
        return removedElement;
    }

    public T peek(){
        if (isEmpty()){
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }
}
