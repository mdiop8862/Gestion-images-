package fr.unilim.java4.gestionimage;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FiltreNoirBlanc implements Filter {
    Image image ;
    public FiltreNoirBlanc(Image image){
        this.image = image ;
    }

    // implementation de addFilter pour ajouter le filtre noir et blanc
    public Image AddFilter(){
        int width = (int) this.image.getWidth() ;
        int heigth = (int) this.image.getHeight();
        WritableImage newImg = new WritableImage(width , heigth);
        PixelReader pixelreader = image.getPixelReader();
        PixelWriter pixelwriter = newImg.getPixelWriter();
        for(int i=0 ; i<width ;  i++){
            for(int j=0 ; j< heigth ; j++){
                Color color = pixelreader.getColor(i,j);
                float grayValue = (float) (( color.getRed()  + color.getBlue() + color.getGreen() )/3 ) ;
                Color newCol = Color.color(grayValue , grayValue , grayValue);
                pixelwriter.setColor(i,j,newCol);
            }
        }
        return newImg ;
    }


}
