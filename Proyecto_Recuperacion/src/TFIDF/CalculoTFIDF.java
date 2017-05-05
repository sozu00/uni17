package TFIDF;


import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
/**
 * Created by sozu on 05/05/2017.
 */
public class CalculoTFIDF {
    public static HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido = new HashMap<String,tupla<Double, HashMap<File, Double>>>();
    public HashMap<String,Double> calcularTF1(ArrayList<String> vText){
        HashMap<String,Double> mapa = new HashMap<String,Double>();
        double f = 0;
        for (String p : vText){
            if (mapa.containsKey(p))
                f = 1 + mapa.get(p);
            else
                f = 1;

            mapa.put(p, f);
        }
        return mapa;
    }

    public HashMap<String,tupla<Double, HashMap<File, Double>>> calcularTF2(HashMap<String, Double> textFrecuencia, File f){
        double tf = 0;
        for (String palabra : textFrecuencia.keySet()) {
            tf = 1 + Math.log(textFrecuencia.get(palabra)) / Math.log(2);
            if (indiceInvertido.containsKey(palabra)) {
                if (!indiceInvertido.get(palabra).docPeso().containsKey(f))
                    indiceInvertido.get(palabra).docPeso().put(f, tf);
            }
            else {
                    HashMap<File, Double> mapaArchivo = new HashMap<File, Double>();
                    mapaArchivo.put(f, tf);
                    indiceInvertido.put(palabra, new tupla(0,mapaArchivo));
            }
        }

        return indiceInvertido;
    }

    public void calcularIDF(HashMap<String, tupla<Double, HashMap<File, Double>>> indiceInvertido, double numFiles) {
        for(String palabra : indiceInvertido.keySet()){
            indiceInvertido.get(palabra).setIDF(Math.log(numFiles/indiceInvertido.get(palabra).docPeso().size())/ Math.log(2));
        }
    }
}

