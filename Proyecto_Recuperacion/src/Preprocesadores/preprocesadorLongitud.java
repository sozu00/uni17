package Preprocesadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class preprocesadorLongitud implements preprocesadorGenerico {

    static int L;
    public static void setLength(int n){L = n;}


    public ArrayList<String> ejecutar(ArrayList<String> vText) throws IOException {
        Iterator<String> s = vText.iterator();
        while(s.hasNext())
        	if (s.next().length() <= L)
        		vText.remove(s);
        return vText;
    }

}
