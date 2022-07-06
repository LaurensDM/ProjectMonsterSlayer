module Elementen {
	requires javafx.controls;
	requires javafx.media;
	requires java.desktop;
	requires javafx.graphics;

	
	opens application to javafx.graphics, javafx.fxml;
}
