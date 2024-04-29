package fr.unilim.java4.gestionimage;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SymetrieVertical implements Symetrie {
    Image image ;
    public SymetrieVertical(Image image){
        this.image = image ;
    }


    // implementation de SymetricTransformation pour effectuer la symetrie vertical de l'image
    public Image SymetricTransformation(){
        int width = (int) this.image.getWidth() ;
        int heigth = (int) this.image.getHeight() ;
        WritableImage imgTransforme = new WritableImage(width,heigth) ;
        PixelReader pixelreadable = image.getPixelReader() ;
        PixelWriter pixelwriter = imgTransforme.getPixelWriter() ;

        for(int i=0 ; i<width ; i++){
            for(int j=0 ; j<heigth ; j++){
                Color color = pixelreadable.getColor(i,j);
                pixelwriter.setColor(i,heigth-j-1 , color);
            }
        }

        return imgTransforme ;

    }
}

