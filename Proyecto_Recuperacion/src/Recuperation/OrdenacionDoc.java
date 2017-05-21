package Recuperation;

import TFIDF.tupla;

import java.io.File;
import java.util.*;

/**
 * Created by sozu on 21/05/2017.
 */
public class OrdenacionDoc {

    public static HashMap<File, Double> Documentos = new HashMap<>();


    public void crearLista() {
        /*
        To Do:
            Recorrer todo el indice invertido buscando:
                Palabra P
                   Documentos de palabra P, si D esta en Documentos, le sumo TF*IDF*IDF, sino lo anado
         */
        double valorAColocar;

        for (String P : Lector.indiceInvertido.keySet()) {
            for (File D : Lector.indiceInvertido.get(P).docPeso().keySet()) {
                valorAColocar = (Lector.indiceInvertido.get(P).docPeso.get(D) * Lector.indiceInvertido.get(P).IDF() * Lector.indiceInvertido.get(P).IDF());
                if (Documentos.containsKey(D)) valorAColocar += Documentos.get(D);
                Documentos.put(D, valorAColocar);
            }
        }
        for(File D : Documentos.keySet()){
            if (Lector.longDoc.containsKey(D)) {
                Documentos.put(D, Documentos.get(D) / Lector.longDoc.get(D));
            }
            //System.out.println(D+" "+Documentos.get(D));
        }
    }

    public void mostrarLista(int n) {
        Comparator<Map.Entry<File, Double>> CompDocs = new Comparator<Map.Entry<File, Double>>() {
            @Override
            public int compare(Map.Entry<File, Double> e1, Map.Entry<File, Double> e2) {
                Double v1 = e1.getValue();
                Double v2 = e2.getValue();
                return v1.compareTo(v2);
            }
        };
        List<Map.Entry<File, Double>> ListaDocs = new ArrayList<>(Documentos.entrySet());
        Collections.sort(ListaDocs, CompDocs);
        for(int i = 0; i<n && i<ListaDocs.size(); i++)
            System.out.println("Candidato "+(i+1)+": "+ListaDocs.get(i).getKey());
    }
}