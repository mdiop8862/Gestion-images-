package fr.unilim.java4.gestionimage.model;

import java.util.ArrayList;

public class Images {

    public String Path ;
    private ArrayList<String> Tags;
    private ArrayList<String> Transformations ;



    // constructeur par defaut
    public  Images(){
       this.Transformations = new ArrayList<String>() ;
       this.Tags = new ArrayList<String>();
    }

    // constructeur avec parametres
    public Images( String Path , ArrayList<String> Tags , ArrayList<String> Transformations){

        this.Path = Path ;
        this.Tags = Tags ;
        this.Transformations = Transformations ;
    }

    // methode pour ajouter une transformation dans la liste des transformations
    public void AddOnTransformation(String transformation) {
        Transformations.add(transformation);
    }

    // methode pour modifier l'attribut tags
    public void setTags(ArrayList<String> Tags){
        this.Tags = Tags ;
    }

    // methode qui retourne les tags
    public ArrayList<String> getTags(){
        return this.Tags ;
    }

    //  mwthode qui retourne les transformations
    public ArrayList<String> getTransformations(){
        return this.Transformations ;
    }

    // methode pour modifier les path
    public void setPath(String path){
        this.Path = path ;
    }

    // methode pour modifier l'attribut transformation
    public void setTransformations(ArrayList<String> transformations){
        this.Transformations = transformations;
    }



}
