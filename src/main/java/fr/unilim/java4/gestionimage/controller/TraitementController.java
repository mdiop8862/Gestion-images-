package fr.unilim.java4.gestionimage.controller;

import fr.unilim.java4.gestionimage.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class TraitementController {

    public ImageView imageview ;
    public void displayImageSelected(File file){
        Image img = new Image(file.toURI().toString()) ;
        imageview.setImage(img);
    }

    public void rotation(){
         int rot = (int) imageview.getRotate() ;
        imageview.setRotate(45+rot);

    }

    public void ApplySymetricHorizontal(){
        Symetrie sym = new SymetrieHorizontal(imageview.getImage()) ;
        imageview.setImage(sym.SymetricTransformation());

    }

    public  void ApplySymetricVertical(){
        Symetrie sym = new SymetrieVertical(imageview.getImage());
        imageview.setImage(sym.SymetricTransformation());
    }

    public void ApplyFiltreGBR(){
        Filter filter = new FiltreGBR(imageview.getImage());
        imageview.setImage(filter.AddFilter());

    }

    public void ApplyFilterNoirBlanc(){
        Filter filter = new FiltreNoirBlanc(imageview.getImage());
        imageview.setImage(filter.AddFilter());
    }

    public void ApplyFilterSepia(){
        Filter filter= new FiltreSepia(imageview.getImage());
        imageview.setImage(filter.AddFilter());
    }

    public void ApplyFilterSobel(){
        Filter filter = new FiltreSobel(imageview.getImage());
        imageview.setImage(filter.AddFilter());
    }
}
