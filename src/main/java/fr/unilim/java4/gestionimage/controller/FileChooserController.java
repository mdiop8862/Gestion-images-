package fr.unilim.java4.gestionimage.controller;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.unilim.java4.gestionimage.HelloApplication;
import fr.unilim.java4.gestionimage.JsonManipulation;
import fr.unilim.java4.gestionimage.model.Images;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;


public class FileChooserController {
    @FXML
    private Button mybtn ;

    // Méthode appelée lors du clic sur le bouton
    public void handleButtonClick()  throws IOException{

        // chargement de l'image

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/traitement.fxml"));
        Parent root = fxmlLoader.load();

        // Récupération du contrôleur de la fenêtre de traitement

        TraitementController tc = fxmlLoader.getController();

        // Création d'un FileChooser pour choisir un fichier image

        FileChooser fc = new FileChooser();

        // Définition des filtres pour n'afficher que les fichiers image

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        ) ;

        // Affichage de la fenêtre de choix de fichier

        File fileSelected = fc.showOpenDialog(new Stage());

        // si un fichier est selection

        if(fileSelected != null) {

            String url = fileSelected.toURI().toString() ;
            tc.recup(url) ;

            tc.displayImageSelected(fileSelected);
            Scene newScene = new Scene(root, 600, 700);
            Stage primaryStage =(Stage) mybtn.getScene().getWindow() ;
            primaryStage.setScene(newScene);

        }

    }

}
