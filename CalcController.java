package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CalcController {

	@FXML
	private Button addition;

	@FXML
	private Pane calculator;

	@FXML
	private Button clear;

	@FXML
	private Button division;

	@FXML
	private Button eight;

	@FXML
	private Button equal;

	@FXML
	private Button five;

	@FXML
	private Button four;

	@FXML
	private Button multiplication;

	@FXML
	private Button nine;

	@FXML
	private Button one;

	@FXML
	private TextField output;

	@FXML
	private Button seven;

	@FXML
	private Button six;

	@FXML
	private Button subtraction;

	@FXML
	private Button three;

	@FXML
	private Button two;

	@FXML
	private Button zero;
	String op = "";
	long number1;
	long number2;

	@FXML
	void ActionButton(ActionEvent event) {
		String num = ((Button) event.getSource()).getText();
		getOutput().appendText(num);

	}

	@FXML
	void OperationButton(ActionEvent event) {
		String operation = ((Button) event.getSource()).getText();
		if (!op.equals("") && !operation.equals("=")) {
			// If an operation is already selected and the pressed button is not "=",
			// calculate it
			calculate();
		}
		if (!operation.equals("=")) {
			// If the pressed button is not "=", store the selected operation and append it
			// to the output
			op = operation;
			getOutput().appendText(" " + operation + " ");
		} else {
			// If the pressed button is "=", calculate the result
			calculate();
		}
	}

	private void calculate() {
		String expression = getOutput().getText();
		String[] parts = expression.split("\\s+");
		long n1 = Long.parseLong(parts[0]);
		String op = parts[1];
		long n2 = Long.parseLong(parts[2]);

		long result = 0;
		switch (op) {
		case "+":
			result = n1 + n2;
			break;
		case "-":
			result = n1 - n2;
			break;
		case "*":
			result = n1 * n2;
			break;
		case "/":
			if (n2 == 0) {
				getOutput().setText("0");
				return;
			}
			result = n1 / n2;
			break;
		}

		getOutput().setText(result + "");
	}

	@FXML
	void ClearButton(ActionEvent clear) {
		getOutput().clear();
		op = ""; // Clear the stored operation
	}

	@FXML
	void initialize() {
		assert getOutput() != null : "fx:id=\"calcvVewer\" was not injected: check your FXML file 'calc.fxml'.";
		assert one != null : "fx:id=\"oneButton\" was not injected: check your FXML file 'calc.fxml'.";
		assert zero != null : "fx:id=\"zeroButton\" was not injected: check your FXML file 'calc.fxml'.";

		getOutput().setText("");
	}

	public TextField getOutput() {
		return output;
	}

	public void setOutput(TextField output) {
		this.output = output;
	}
}
