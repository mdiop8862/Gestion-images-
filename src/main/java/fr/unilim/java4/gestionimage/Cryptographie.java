package fr.unilim.java4.gestionimage;

import fr.unilim.java4.gestionimage.model.Images;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Cryptographie {
    public Image image ;
    public Cryptographie(Image image){
        this.image = image ;
    }


    public Image encryptImage(String password) throws NoSuchAlgorithmException {
        byte[] seed = Cryptographie.PasswordHash(password);
        SecureRandom random = new SecureRandom(seed);
        int width = (int) this.image.getWidth() ;
        int height = (int) this.image.getHeight();
        WritableImage encryptImage = new WritableImage(width , height);

        for(int i=0 ; i < width ; i++){
            for(int j=0 ; j < height ; j++){
                int randomX = random.nextInt((int) image.getWidth());
                int randomY = random.nextInt((int) image.getHeight());
                int pixel1 = image.getPixelReader().getArgb(i, j);
                int pixel2 = image.getPixelReader().getArgb(randomX, randomY);
                encryptImage.getPixelWriter().setArgb(i, j, pixel2);
                encryptImage.getPixelWriter().setArgb(randomX, randomY, pixel1);

            }
        }

        return encryptImage ;
    }
    public static byte[] PasswordHash(String password) throws NoSuchAlgorithmException {
        byte[] passwordbyte = password.getBytes() ;
        MessageDigest mess = MessageDigest.getInstance("SHA-256");
        return mess.digest(passwordbyte);
    }
}
