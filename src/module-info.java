module finalproj {
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens model;
	opens view;
}
