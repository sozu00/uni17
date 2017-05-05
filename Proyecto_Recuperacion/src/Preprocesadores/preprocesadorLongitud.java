package Preprocesadores;

import java.io.IOException;
import java.util.ArrayList;

public class preprocesadorLongitud implements preprocesadorGenerico {

    static int L = 2;
    public static void setLength(int n){L = n;}


    public ArrayList<String> ejecutar(ArrayList<String> vText) throws IOException {
        int i = 0;
        while(i < vText.size()) {
            if (vText.get(i).length() <= L)
                vText.remove(i);
            else
                i++;
        }
        return vText;
    }

}
