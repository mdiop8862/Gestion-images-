package fr.unilim.java4.gestionimage.controller;

import fr.unilim.java4.gestionimage.*;
import fr.unilim.java4.gestionimage.model.Images;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class TraitementController {

    public ImageView imageview ;

    public String url ;
    public TextField password;
    public ImageView imageview2;

   public static JsonManipulation manip = new JsonManipulation();
    public TextField inputTags;

    public void displayImageSelected(File file){
        Image img = new Image(file.toURI().toString()) ;
        imageview.setImage(img);
    }

    public void recup(String url){
        this.url = url ;
    }

    public void rotation() throws IOException {
         int rot = (int) imageview.getRotate() ;
         imageview.setRotate(90+rot);

         ArrayList<Images> imageList = manip.ReadJsonFile();

         for (Images image : imageList){
             if(image.Path.equals(url)){
                 image.AddOnTransformation("rotation");
             }
         }
        manip.WriteInJsonFile(imageList);

    }

    public void ApplySymetricHorizontal() throws IOException {
        Symetrie sym = new SymetrieHorizontal(imageview.getImage()) ;
        imageview.setImage(sym.SymetricTransformation());

        ArrayList<Images> imageList = manip.ReadJsonFile();
        System.out.println(imageview.getImage().getUrl());
        for (Images image : imageList){
            if(image.Path.equals(url)){
                image.AddOnTransformation("Symetrie Horizontal");
            }
        }
        manip.WriteInJsonFile(imageList);

    }

    public  void ApplySymetricVertical() throws IOException {
        Symetrie sym = new SymetrieVertical(imageview.getImage());
        imageview.setImage(sym.SymetricTransformation());
        ArrayList<Images> imageList = manip.ReadJsonFile();
        for (Images image : imageList){
            if(image.Path.equals(url)){
                image.AddOnTransformation("Symetrie verticale");
            }
        }
        manip.WriteInJsonFile(imageList);
    }

    public void ApplyFiltreGBR() throws IOException {
        Filter filter = new FiltreGBR(imageview.getImage());
        imageview.setImage(filter.AddFilter());

        ArrayList<Images> imageList = manip.ReadJsonFile();
        for (Images image : imageList){
            if(image.Path.equals(url)){
                image.AddOnTransformation("filtre gbr");
            }
        }
        manip.WriteInJsonFile(imageList);

    }

    public void ApplyFilterNoirBlanc() throws IOException {
        Filter filter = new FiltreNoirBlanc(imageview.getImage());
        imageview.setImage(filter.AddFilter());

        ArrayList<Images> imageList = manip.ReadJsonFile();
        for (Images image : imageList){
            if(image.Path.equals(url)){
                image.AddOnTransformation("filtre noir et blanc");
            }
        }
        manip.WriteInJsonFile(imageList);
    }

    public void ApplyFilterSepia() throws IOException {
        Filter filter= new FiltreSepia(imageview.getImage());
        imageview.setImage(filter.AddFilter());

        ArrayList<Images> imageList = manip.ReadJsonFile();
        for (Images image : imageList){
            if(image.Path.equals(url)){
                image.AddOnTransformation("filtre sepia");
            }
        }
        manip.WriteInJsonFile(imageList);

    }

    public void ApplyFilterSobel() throws IOException {
        Filter filter = new FiltreSobel(imageview.getImage());
        imageview.setImage(filter.AddFilter());

        ArrayList<Images> imageList = manip.ReadJsonFile();
        for (Images image : imageList){
            if(image.Path.equals(url)){
                image.AddOnTransformation("filtre sobel");
            }
        }
        manip.WriteInJsonFile(imageList);
    }

    public void cryptImage() throws NoSuchAlgorithmException {
        Cryptographie crypt = new Cryptographie(imageview.getImage());
        imageview2.setImage(crypt.encryptImage(password.getText()));

    }

    public void AddTags() throws IOException {
        String content = inputTags.getText();
        String[] Tags = content.split(" ");
        ArrayList<String> ListTags = new ArrayList<String>();

        ListTags.addAll(Arrays.asList(Tags));
        ArrayList<Images> imageList = manip.ReadJsonFile();
        for(Images image : imageList){
            if(image.Path.equals(url)){
                image.setTags(ListTags);
            }
        }
        manip.WriteInJsonFile(imageList);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("enregistrement des tags ");
        alert.setHeaderText(null);
        alert.setContentText("Enregistrer avec success");
        alert.showAndWait();

    }
}
