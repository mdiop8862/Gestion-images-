package fr.unilim.java4.gestionimage.controller;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class traitementController {

    public ImageView imageview ;
    public void displayImageSelected(File file){
        Image img = new Image(file.toURI().toString()) ;
        imageview.setImage(img);
    }

    public void rotation(){
         int rot = (int) imageview.getRotate() ;
        imageview.setRotate(45+rot);

    }
}
