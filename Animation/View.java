package Homework_dataStructure;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class View {
    private Scene scene;
    private StackPane stackPane;
    private Button pushButton;
    private Button popButton;
    private Button peekButton; // New button for peeking
    private List<Circle> circles;
    private double initialY = 150; // Initial Y-coordinate for the bottom of the stack
    private double spacingY = 70; // Spacing between stacked circle

    public View() {
        stackPane = new StackPane();
        pushButton = new Button("Push");
        popButton = new Button("Pop");
        peekButton = new Button("Peek"); // Initialize the peek button

        VBox buttonBox = new VBox(pushButton, popButton, peekButton); // Add peek button to button box
        stackPane.getChildren().addAll(buttonBox);

        scene = new Scene(stackPane, 400, 300);

        circles = new ArrayList<>(); // Initialize list of circles

        pushButton.setOnAction(e -> {
            Circle circle = createCircle(); // Create a new circle
            addCircle(circle); // Add the circle to the view
        });

        popButton.setOnAction(e -> {
            removeTopCircle(); // Remove the topmost circle from the view
        });

        peekButton.setOnAction(e -> {
            peekAtTopCircle(); // Peek at the topmost circle
        });
    }

    public Scene getScene() {
        return scene;
    }

    private Circle createCircle() {
    	Circle circle = new Circle(50, 20, 32, Color.YELLOW); // Create a new blue circle
        return circle;
    }
    public void addCircle(Circle circleToAdd) {
        circles.add(circleToAdd); // Add circle to list
        updateStack(); // Update the stack visualization
    }

    public void removeTopCircle() {
        if (!circles.isEmpty()) {
        	circles.remove(circles.size() - 1); // Remove the topmost circles from the list
            updateStack(); // Update the stack visualization
        }
    }

    private void updateStack() {
        stackPane.getChildren().clear(); // Clear previous elements
        double currentY = initialY; // Starting Y-coordinate for the bottom of the stack
        for (Circle circle : circles) {
        	circle.setTranslateY(currentY - circle.getRadius()); // Set the Y translation
            stackPane.getChildren().add(circle); // Add circle to the stack pane
            currentY -= spacingY; // Decrement Y-coordinate for next circle (to stack on top)
        }
        VBox buttonBox = new VBox(pushButton, popButton, peekButton); // Re-add buttons
        stackPane.getChildren().add(buttonBox);
    }

    private void peekAtTopCircle() {
        if (!circles.isEmpty()) {
        	Circle topCircle = circles.get(circles.size() - 1); // Get the topmost circle
            topCircle.setFill(Color.PURPLE); // Highlight the top circle temporarily
            // Reset the color after a short delay (e.g., 1 second)
            new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        topCircle.setFill(Color.YELLOW); // Reset color to yellow
                    }
                },
                1000 // 1 second delay
            );
        }
    }
    
    public Button getPushButton() {
        return pushButton;
    }

    public Button getPopButton() {
        return popButton;
    }

    public Circle getPushedCircle() {
        return getPushedCircle();
    }

    public StackPane getStackPane() {
        return stackPane;
    }
}
