package fr.unilim.java4.gestionimage;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FiltreGBR implements Filter {
    Image image ;
    public FiltreGBR(Image image){
        this.image = image ;
    }

    public Image AddFilter(){
        int width = (int) this.image.getWidth() ;
        int heigth = (int) this.image.getHeight();
        WritableImage newImg = new WritableImage(width , heigth);
        PixelReader pixelreader = image.getPixelReader();
        PixelWriter pixelwriter = newImg.getPixelWriter();

        for(int i=0 ; i<width ;  i++){
            for(int j=0 ; j< heigth ; j++){
                Color color = pixelreader.getColor(i,j);
                float green = (float) color.getGreen() ;
                float red = (float) color.getRed() ;
                float blue = (float) color.getBlue() ;
                Color newCol = Color.color(green , blue , red);
                pixelwriter.setColor(i,j,newCol);

            }
        }

        return newImg ;



    }
}
