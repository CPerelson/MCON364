package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import javafx.event.ActionEvent;

public class CalcController_TEST<SampleController> {
   
    private SampleController controller;

    @BeforeEach
    public void setUp() throws Exception {
        controller = new SampleController();
        controller.initialize();
    }

    @Test
    public void testAddition() {
        controller.getOutPut().setText("5");
        controller.OperationButton(new ActionEvent(controller.getPlusButton(), null));
        controller.getOutPut().setText("3");
        controller.OperationButton(new ActionEvent(controller.getEqualsButton(), null));
        assertEquals("8", controller.getOutPut().getText());
    }

    @Test
    public void testSubtraction() {
        controller.getOutPut().setText("10");
        controller.OperationButton(new ActionEvent(controller.getMinusButton(), null));
        controller.getOutPut().setText("5");
        controller.OperationButton(new ActionEvent(controller.getEqualsButton(), null));
        assertEquals("5", controller.getOutPut().getText());
    }

    @Test
    public void testMultiplication() {
        controller.getOutPut().setText("4");
        controller.OperationButton(new ActionEvent(controller.getMultipliedByButton(), null));
        controller.getOutPut().setText("6");
        controller.OperationButton(new ActionEvent(controller.getEqualsButton(), null));
        assertEquals("24", controller.getOutPut().getText());
    }

    @Test
    public void testDivision() {
        controller.getOutPut().setText("20");
        controller.OperationButton(new ActionEvent(controller.getDividedByButton(), null));
        controller.getOutPut().setText("4");
        controller.OperationButton(new ActionEvent(controller.getEqualsButton(), null));
        assertEquals("5", controller.getOutPut().getText());
    }

    @Test
    public void testDivisionByZero() {
        controller.getOutPut().setText("10");
        controller.OperationButton(new ActionEvent(controller.getDividedByButton(), null));
        controller.getOutPut().setText("0");
        controller.OperationButton(new ActionEvent(controller.getEqualsButton(), null));
        assertEquals("0", controller.getOutPut().getText());
    }

    @Test
    public void testClearButton() {
        controller.getOutPut().setText("123");
        controller.ClearButton(new ActionEvent(controller.getClearButton(), null));
        assertEquals("", controller.getOutPut().getText());
    }
}

    



