package fr.unilim.java4.gestionimage.controller;

import fr.unilim.java4.gestionimage.*;
import fr.unilim.java4.gestionimage.model.Images;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;


import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

public class TraitementController {

    public ImageView imageview ;

    Images  ImageSaved = new Images() ;
    public String url ;
    public TextField password;
    public ImageView imageview2;

   public static JsonManipulation manip = new JsonManipulation();
    public TextField inputTags;
    public TextField TagsSearched;

    public void displayImageSelected(File file){
        Image img = new Image(file.toURI().toString()) ;
        imageview.setImage(img);
    }

    public void recup(String url){
        this.url = url ;
    }

    public void rotation() throws IOException {
         Rotation rotation = new Rotation();
         rotation.ApplyRotation(imageview);

         ImageSaved.AddOnTransformation("rotation");


    }

    public void ApplySymetricHorizontal() throws IOException {
        Symetrie sym = new SymetrieHorizontal(imageview.getImage()) ;
        imageview.setImage(sym.SymetricTransformation());


        ImageSaved.AddOnTransformation("Symetrie Horizontal");


    }

    public  void ApplySymetricVertical() throws IOException {
        Symetrie sym = new SymetrieVertical(imageview.getImage());
        imageview.setImage(sym.SymetricTransformation());


        ImageSaved.AddOnTransformation("Symetrie verticale");


    }

    public void ApplyFiltreGBR() throws IOException {
        Filter filter = new FiltreGBR(imageview.getImage());
        imageview.setImage(filter.AddFilter());


        ImageSaved.AddOnTransformation("filtre gbr");


    }

    public void ApplyFilterNoirBlanc() throws IOException {
        Filter filter = new FiltreNoirBlanc(imageview.getImage());
        imageview.setImage(filter.AddFilter());


        ImageSaved.AddOnTransformation("filtre noir et blanc");


    }

    public void ApplyFilterSepia() throws IOException {
        Filter filter= new FiltreSepia(imageview.getImage());
        imageview.setImage(filter.AddFilter());


        ImageSaved.AddOnTransformation("filtre sepia");





    }

    public void ApplyFilterSobel() throws IOException {
        Filter filter = new FiltreSobel(imageview.getImage());
        imageview.setImage(filter.AddFilter());


        ImageSaved.AddOnTransformation("filtre sobel");


    }

    public void cryptImage() throws NoSuchAlgorithmException {
        Cryptographie crypt = new Cryptographie(imageview.getImage());
        Image imageCrypt = crypt.encryptImage(password.getText());
        imageview2.setImage(imageCrypt);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");


        // Ajoutez tous les filtres d'extension ici
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG (*.PNG)", "*.PNG"),
                new FileChooser.ExtensionFilter("JPG (*.JPG)","*.JPG"),
                new FileChooser.ExtensionFilter("JPEG (*.JPEG)","*.JPEG"),
                new FileChooser.ExtensionFilter("GIF (*.GIF)","*.GIF"));

        File file = fileChooser.showSaveDialog(new Stage());

        if (file != null) {
            try {
                // Récupérez l'extension de l'image d'origine
                String originalExtension = imageview.getImage().getUrl().substring(imageview.getImage().getUrl().lastIndexOf(".") + 1);
                // Utilisez l'extension pour enregistrer l'image cryptée
                ImageIO.write(SwingFXUtils.fromFXImage(imageview2.getImage(), null), originalExtension, file);
                System.out.println("Image enregistrée avec succès !");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public void Enregistrer() throws IOException {
        String content = inputTags.getText();
        String[] Tags = content.split(" ");
        ArrayList<String> ListTags = new ArrayList<String>();

        ListTags.addAll(Arrays.asList(Tags));
        ImageSaved.setPath(url);
        ImageSaved.setTags(ListTags);
        ArrayList<Images> imageList = manip.ReadJsonFile();
        ImageSaved.setIdentifiant(imageList.size());


        boolean imageAlreadyExist = false ;
        for(Images im : imageList){
                    if(im.Path.equals(ImageSaved.Path)){
                          imageAlreadyExist = true ;
                          ImageSaved = new Images();
                          Alert alert = new Alert(Alert.AlertType.ERROR);
                          alert.setTitle("enregistrement");
                          alert.setHeaderText(null);
                          alert.setContentText("L'image est deja enregestre ");
                          alert.showAndWait();
                          break ;
                    }

        }

        if(! imageAlreadyExist){
                    imageList.add(ImageSaved);
                    manip.WriteInJsonFile(imageList);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("enregistrement");
                    alert.setHeaderText(null);
                    alert.setContentText("Enregistrer avec success");
                    alert.showAndWait();
        }



    }

    public  void searchImages() throws IOException {

         FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("view/imageFound.fxml"));
         Parent root = loader.load() ;
         Scene newScene = new Scene(root , 600 , 700) ;
         Stage newStage = new Stage();
         ArrayList<Images> im = new ArrayList<Images>() ;

         String[] tags = TagsSearched.getText().split(" ");
        ArrayList<String> tagsList = new ArrayList<String>() ;

        tagsList.addAll(Arrays.asList(tags));

        ArrayList<Images> imageFound = new ArrayList<Images>();
        ArrayList<Images> fetchImages = manip.ReadJsonFile();

        for(Images image : fetchImages){
            for(String tag : image.getTags()){
                if(tagsList.contains(tag)){
                    imageFound.add(image);
                }
            }
        }
          ImageFoundController gc = loader.getController();
          gc.displayGalerie(imageFound);

        newStage.setScene(newScene);
        newStage.show();

    }
}
