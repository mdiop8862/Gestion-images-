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


    public void handleButtonClick()  throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/traitement.fxml"));
        Parent root = fxmlLoader.load();

        TraitementController tc = fxmlLoader.getController();
        System.out.println("hello world");
        FileChooser fc = new FileChooser();

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        ) ;

        File fileSelected = fc.showOpenDialog(new Stage());

        if(fileSelected != null) {

            String url = fileSelected.toURI().toString() ;
            tc.recup(url) ;
            final GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
            final Gson gson = builder.create();

            try {
                JsonManipulation manip = new JsonManipulation();
                ArrayList<Images> imagesList = manip.ReadJsonFile();
                Images image = new Images(imagesList.size() , url , new ArrayList<String>() , new ArrayList<String>()) ;
                boolean imageAlreadyExist = false ;
                for(Images im : imagesList){
                    if(im.Path.equals(image.Path)){
                          imageAlreadyExist = true ;
                          break ;
                    }

                }

                if(! imageAlreadyExist){
                    imagesList.add(image);
                    manip.WriteInJsonFile(imagesList);
                }



            } catch (IOException e) {
                e.printStackTrace();
            }



            tc.displayImageSelected(fileSelected);
            Scene newScene = new Scene(root, 600, 700);
            Stage primaryStage =(Stage) mybtn.getScene().getWindow() ;
            primaryStage.setScene(newScene);



        }

    }

}
