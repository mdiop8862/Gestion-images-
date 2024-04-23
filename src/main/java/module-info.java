module fr.unilim.java4.gestionimage {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens fr.unilim.java4.gestionimage.model to com.google.gson;
    opens fr.unilim.java4.gestionimage to javafx.fxml;
    exports fr.unilim.java4.gestionimage;
    exports fr.unilim.java4.gestionimage.controller;
    opens fr.unilim.java4.gestionimage.controller to javafx.fxml;
}