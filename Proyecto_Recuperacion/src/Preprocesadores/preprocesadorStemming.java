package Preprocesadores;

import org.tartarus.snowball.ext.englishStemmer;

import java.io.IOException;
import java.util.ArrayList;

public class preprocesadorStemming implements preprocesadorGenerico {
    public ArrayList<String> ejecutar(ArrayList<String> vText) throws IOException {

        englishStemmer E = new englishStemmer();
        for(String p : vText){
            E.setCurrent(p);
            if(E.stem()) vText.set(vText.indexOf(p), E.getCurrent());
        }

        return vText;
    }

}
