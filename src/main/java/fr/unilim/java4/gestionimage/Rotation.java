package fr.unilim.java4.gestionimage;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rotation {


    public void ApplyRotation(ImageView imageview){
        int rot = (int) imageview.getRotate() ;
        imageview.setRotate(90+rot);
    }

}
