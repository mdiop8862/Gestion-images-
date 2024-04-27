package fr.unilim.java4.gestionimage.controller;

import fr.unilim.java4.gestionimage.*;
import fr.unilim.java4.gestionimage.model.Images;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


import java.util.ArrayList;

public class ImageFoundController {

    @FXML
    public GridPane gridpane;


    public void displayGalerie(ArrayList<Images> Galerie) {

        int column = 0;
        int row = 0;
        System.out.println(Galerie.size());
        for (Images image : Galerie) {
            Image im = new Image(image.Path);
            for(String transform : image.getTransformations()){
                if(transform.equals("Symetrie Horizontal")){
                    Symetrie symetrie = new SymetrieHorizontal(im);
                    im = symetrie.SymetricTransformation();
                }
                if(transform.equals("Symetrie verticale")){
                    Symetrie symetrie = new SymetrieVertical(im);
                    im = symetrie.SymetricTransformation();
                }
                if(transform.equals("filtre noir et blanc")){
                    Filter filter = new FiltreNoirBlanc(im);
                    im = filter.AddFilter();
                }
                if(transform.equals("filtre sobel")){
                    Filter filter = new FiltreSobel(im);
                    im = filter.AddFilter() ;
                }
                if(transform.equals("filtre sepia")){
                    Filter filter = new FiltreSepia(im);
                    im = filter.AddFilter();
                }
            }
            ImageView imageView = new ImageView(im);
            imageView.setFitWidth(200);
            imageView.setFitHeight(200);
            gridpane.setHgap(20);
            gridpane.setVgap(20);
            gridpane.add(imageView, column, row);
            column++;
            if (column == 2) { // 2 images par ligne
                column = 0;
                row++;
            }


        }
    }



}
