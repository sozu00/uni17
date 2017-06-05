package Recuperation;

import java.io.File;
import java.util.*;

@SuppressWarnings("DefaultFileTemplate")
public class OrdenacionDoc {

    private static HashMap<File, Double> Documentos;
    public static List<Map.Entry<File, Double>> ListaDocs;

    public void createList() {
        /*
        Recorrer todo el indice invertido buscando:
            -Palabra P
               -*Documentos de palabra P, calculo TF*IDF*IDF, si D esta en Documentos, 
               		-**SI: 		se lo sumo al actual
               		-**SI_NO: 	añado el valor nuevo
         */
    	Documentos = new HashMap<>();
        double valorAColocar;
        for(String P : Recuperation.vTextProcesado) {
            if(Lector.indiceInvertido.containsKey(P)){
                for (File D : Lector.indiceInvertido.get(P).docPeso().keySet()) {
                    valorAColocar = (Lector.indiceInvertido.get(P).docPeso.get(D) * Lector.indiceInvertido.get(P).getIDF() * Lector.indiceInvertido.get(P).getIDF());
                    if (Documentos.containsKey(D)) valorAColocar += Documentos.get(D);
                    Documentos.put(D, valorAColocar);
                }
            }
        }
        for(File D : Documentos.keySet()){
            if (Lector.longDoc.containsKey(D)) {
                Documentos.put(D, Documentos.get(D)/Lector.longDoc.get(D));
            }
        }
    }

    public void showList() {
    	//Creo un comparador para que los ordene de forma DESCENDENTE
        Comparator<Map.Entry<File, Double>> CompDocs = (e1, e2) -> {
            Double v1 = e1.getValue();
            Double v2 = e2.getValue();
            return v2.compareTo(v1);
        };
        
        ListaDocs = new ArrayList<>(Documentos.entrySet());
        
        ListaDocs.sort(CompDocs);
    }
}