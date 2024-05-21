package Homework_dataStructure;

import java.util.Stack;

import javafx.scene.shape.Circle;

public class Model {
    private Stack<Circle> stack; // Stack to hold circle

    public Model() {
        stack = new Stack<>();
    }

    public void push(Circle circle) {
        stack.push(circle);
    }

    public Circle pop() {
        if (!stack.isEmpty()) {
            return stack.pop();
        } else {
            return null; // Return null if the stack is empty
        }
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}
