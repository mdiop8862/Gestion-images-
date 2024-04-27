module fr.unilim.java4.gestionimage {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.desktop;
    requires javafx.swing;
    requires java.sql;

    opens fr.unilim.java4.gestionimage.model to com.google.gson;
    opens fr.unilim.java4.gestionimage to javafx.fxml;
    exports fr.unilim.java4.gestionimage;
    exports fr.unilim.java4.gestionimage.controller;
    exports fr.unilim.java4.gestionimage.model;
    opens fr.unilim.java4.gestionimage.controller to javafx.fxml;
}