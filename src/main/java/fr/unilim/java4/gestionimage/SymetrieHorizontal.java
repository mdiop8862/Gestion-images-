package fr.unilim.java4.gestionimage;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class SymetrieHorizontal implements Symetrie {

    Image image ;
    public SymetrieHorizontal(Image image){
        this.image = image ;
    }
    public Image SymetricTransformation(){

        int width = (int) this.image.getWidth() ;
        int heigth = (int) this.image.getHeight();
        WritableImage imgTransforme = new WritableImage(width , heigth) ;
        PixelReader pixelreader = this.image.getPixelReader() ;
        PixelWriter pixelwriter = imgTransforme.getPixelWriter();

        for(int i=0 ; i < width ; i++){
            for(int j=0 ; j < heigth ; j++){
                Color colorRead = pixelreader.getColor(i,j) ;
                pixelwriter.setColor(width-i-1 ,j , colorRead);
            }
        }
        return imgTransforme ;

    }

}
