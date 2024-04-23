package fr.unilim.java4.gestionimage.model;

import java.util.ArrayList;

public class Images {
    private int Identifiant ;
    public String Path ;
    private ArrayList<String> Tags;
    private ArrayList<String> Transformations ;

    public Images(int identifiant , String Path , ArrayList<String> Tags , ArrayList<String> Transformations){
        this.Identifiant = identifiant ;
        this.Path = Path ;
        this.Tags = Tags ;
        this.Transformations = Transformations ;
    }

    public void AddOnTransformation(String transformation) {
        Transformations.add(transformation);
    }

    public void setTags(ArrayList<String> Tags){
        this.Tags = Tags ;
    }
}
