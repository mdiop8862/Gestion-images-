package fr.unilim.java4.gestionimage;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FiltreSobel implements Filter {
    Image image ;
    public FiltreSobel(Image image){
        this.image = image ;
    }


    // implementation de addFilter pour ajouter le filtre sobel
    public Image AddFilter()  {

        int width = (int) image.getWidth();
        int height = (int) image.getHeight();

        // Convertir l'image en tableau de pixels en niveaux de gris
        int[][] pixels = new int[height][width];
        PixelReader reader = image.getPixelReader();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                pixels[y][x] = (int) (reader.getColor(x, y).grayscale().getRed() * 255);
            }
        }

        int[][] sobelX = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
        int[][] sobelY = {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}};
        int[][] result = new int[height][width];
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                int gx = 0, gy = 0;
                for (int j = -1; j <= 1; j++) {
                    for (int i = -1; i <= 1; i++) {
                        gx += pixels[y+j][x+i] * sobelX[j+1][i+1];
                        gy += pixels[y+j][x+i] * sobelY[j+1][i+1];
                    }
                }
                result[y][x] = (int) Math.sqrt(gx*gx + gy*gy);
            }
        }

        WritableImage filteredImage = new WritableImage(width, height);
        PixelWriter writer = filteredImage.getPixelWriter();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int value = result[y][x];
                if (value > 255) value = 255; // Limiter la valeur maximale à 255
                writer.setColor(x, y, Color.rgb(value, value, value));
            }

        }
        return filteredImage;



    }
}
