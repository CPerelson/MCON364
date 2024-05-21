module Calculator_HW9 {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.desktop;
	requires javafx.base;
	requires java.scripting;
	requires org.junit.jupiter.api;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
