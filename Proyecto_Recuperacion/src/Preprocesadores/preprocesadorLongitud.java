package Preprocesadores;

import java.io.IOException;
import java.util.ArrayList;

public class preprocesadorLongitud implements preprocesadorGenerico {

    static int L = 2;
    public static void setLength(int n){L = n;}


    public ArrayList<String> ejecutar(ArrayList<String> vText) throws IOException {
        for(String s : vText){
        	if (s.length() <= L)
        		vText.remove(s);
        }
        return vText;
    }

}
