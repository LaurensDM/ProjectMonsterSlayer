module Elementen {
    requires javafx.controls;
    requires javafx.media;
    requires java.desktop;
    requires javafx.graphics;
    requires java.sql;


    opens application to javafx.graphics, javafx.fxml;
}
