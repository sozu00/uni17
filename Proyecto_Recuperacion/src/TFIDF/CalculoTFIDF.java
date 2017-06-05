package TFIDF;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class CalculoTFIDF {
    
	public static final HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido = new HashMap<>();

    public static final HashMap<File, Double> Longitudes = new HashMap<>();
    /*Creo un índice invertido para todas las clases 
     * porque es un sólo indice global por consulta.
     * Asi que en vez de copiarlo en cada archivo, simplemente lo creo como
     * estático
	*/
	
	public HashMap<String,Double> calcularTF1(ArrayList<String> vText){
        HashMap<String,Double> mapaOcurrencias = new HashMap<>();
        double ocurrenciasP;
        /*
         * Para cada palabra del texto, 
         * 	si el mapa de ocurrencias la contiene ya la ocurrencia 
         * 		-*SI: 		Incremento 1
         * 		-*SI_NO: 	Añado una nueva 
         */
        for (String p : vText){
            if (mapaOcurrencias.containsKey(p))
                ocurrenciasP = 1 + mapaOcurrencias.get(p);
            else
                ocurrenciasP = 1;

            mapaOcurrencias.put(p, ocurrenciasP);
        }
        return mapaOcurrencias;
    }

    public void calcularTF2(HashMap<String, Double> textFrecuencia, File f){
        
    	double tf;
        
        /*
         * Para cada palabra P del texto
         * 	-Calculo el TF de P en el documento F
         * 	-Si el indice tiene la palabra ya
         * 		-*SI:		Añado la palabra P al documento F
         * 		-*SI_NO: 	Creo un nuevo Map para P con el documento F
         */		
        
        for (String palabra : textFrecuencia.keySet()) {
            tf = 1 + Math.log(textFrecuencia.get(palabra)) / Math.log(2);

            if (indiceInvertido.containsKey(palabra))
                indiceInvertido.get(palabra).docPeso().put(f, tf);
           
            else {
                HashMap<File, Double> mapaArchivo = new HashMap<>();
                mapaArchivo.put(f, tf);
                indiceInvertido.put(palabra, new tupla<>(0.0, mapaArchivo));
            }
        }
    }


    public void calcularIDFyLongitud(){
    	double idf;
    	double TFIDF;
    	
    	/*
         * Para cada palabra P del indice Invertido
         * 	-Asigno el IDF de P (Con la formula log(N/n)/log(2), siendo N el total de archivos y n el numero de archivos
         * 	en los que aparece P.
         * 	-Para cada archivo F que contiene la palabra P
         * 		-*Multiplico su TF por el IDF previo y elevo el resultado al cuadrado	
         * 		-Si el mapa de longitudes tiene el archivo F ya añadido
         * 			-*SI:		Sumo el TFIDF nuevo al documento F
         * 		-Inserto en el archivo F el nuevo TFIDF (La suma con el actual o uno nuevo en caso de no existir)
         */	
    	for(String P : indiceInvertido.keySet()){

            indiceInvertido.get(P).setIDF(
                    Math.log(Indexing.Indexing.numFiles/indiceInvertido.get(P).docPeso().size())/ Math.log(2));

    		idf = indiceInvertido.get(P).getIDF();

    		for(File f : indiceInvertido.get(P).docPeso().keySet()){
    			
    			TFIDF = Math.pow(indiceInvertido.get(P).docPeso.get(f) * idf, 2);

    			if(Longitudes.containsKey(f))
    				TFIDF += Longitudes.get(f);

    			Longitudes.put(f, TFIDF);
    		}
    	}
    	
    	//Al finalizar hay que hacerle la raiz a los sumatorios hechos anteriormente
    	for(File f : Longitudes.keySet())
    		Longitudes.put(f, Math.sqrt(Longitudes.get(f)));
    }
}


