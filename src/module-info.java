module Elementen {
	requires javafx.controls;
	requires javafx.media;
	requires java.desktop;
	requires javafx.graphics;
	requires org.junit.jupiter.api;
	requires org.junit.jupiter.params;
	
	opens application to javafx.graphics, javafx.fxml;
}
