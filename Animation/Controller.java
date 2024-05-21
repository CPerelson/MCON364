package Homework_dataStructure;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        // Set event handlers for buttons
        view.getPushButton().setOnAction(new PushButtonHandler());
        view.getPopButton().setOnAction(new PopButtonHandler());
    }

    // Event handler for the push button
    private class PushButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Circle circle = new Circle(50, 20, 32, Color.YELLOW); // Create a new yellow circle
            view.addCircle(circle); // Add the circle to the view
        }
    }

    // Event handler for the pop button
    private class PopButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            view.removeTopCircle(); // Remove the topmost circle from the view
        }
    }
}
