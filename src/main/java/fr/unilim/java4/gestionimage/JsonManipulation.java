package fr.unilim.java4.gestionimage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.unilim.java4.gestionimage.model.Images;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonManipulation {
    public static String path ;
    public final GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
    public final Gson gson = builder.create();

    public JsonManipulation(){
        path = "src/main/resources/fr/unilim/java4/gestionimage/meta.json";
    }

    public ArrayList<Images> ReadJsonFile() throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        Type listType = new TypeToken<ArrayList<Images>>() {}.getType();
        ArrayList<Images> imagesList = gson.fromJson(reader, listType);
        reader.close();
        if(imagesList == null){
            imagesList = new ArrayList<>();
        }

        return imagesList ;

    }

    public void WriteInJsonFile(ArrayList<Images> imageList) throws IOException {
        BufferedWriter write = new BufferedWriter(new FileWriter(path));
        gson.toJson(imageList , write) ;
        write.close();
    }
}
