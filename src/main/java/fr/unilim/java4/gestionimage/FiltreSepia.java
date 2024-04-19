package fr.unilim.java4.gestionimage;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FiltreSepia implements Filter {
    Image image ;
    public FiltreSepia(Image image){
        this.image = image ;
    }

    public Image AddFilter(){
        int width = (int) this.image.getWidth() ;
        int heigth = (int) this.image.getHeight();
        WritableImage newImg = new WritableImage(width , heigth);
        PixelReader pixelreader = image.getPixelReader();
        PixelWriter pixelwriter = newImg.getPixelWriter();

        for(int i= 0 ; i < width ; i++){
            for(int j=0 ; j < heigth ; j++){
                Color color = pixelreader.getColor(i,j);
                float outR = (float) Math.min( 1,(0.393 * color.getRed() + 0.769 * color.getGreen() + 0.189 * color.getBlue()));
                float outG = (float) Math.min(1,(0.349 * color.getRed() + 0.686 * color.getGreen() + 0.168 * color.getBlue()));
                float outB = (float) Math.min(1,(0.272 * color.getRed() + 0.534 * color.getGreen() + 0.131 * color.getBlue()));
                Color newColor = Color.color(outR , outG , outB) ;
                pixelwriter.setColor(i,j,newColor);

            }
        }
        return  newImg ;
    }

}
