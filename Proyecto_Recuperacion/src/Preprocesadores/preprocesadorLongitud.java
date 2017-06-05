package Preprocesadores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class preprocesadorLongitud implements preprocesadorGenerico {

    private static int L;
    @SuppressWarnings("SameParameterValue")
    public static void setLength(int n){L = n;}


    public ArrayList<String> execute(ArrayList<String> vText) throws IOException {
        Iterator<String> s = vText.iterator();
        while(s.hasNext())
        	if (s.next().length() <= L) {
                s.remove();
            }
        return vText;
    }

}
