package fr.unilim.java4.gestionimage.controller;

import fr.unilim.java4.gestionimage.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FileChooserController {
    @FXML
    private Button mybtn ;


    public void handleButtonClick()  throws IOException{
        System.out.println("hello world");
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        ) ;

        File fileSelected = fc.showOpenDialog(new Stage());

        if(fileSelected != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/traitement.fxml"));
            Parent root = fxmlLoader.load();
            traitementController tc = fxmlLoader.getController();
            tc.displayImageSelected(fileSelected);
            Scene newScene = new Scene(root, 600, 700);
            Stage primaryStage =(Stage) mybtn.getScene().getWindow() ;
            primaryStage.setScene(newScene);



        }

    }

}
