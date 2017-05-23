package TFIDF;


import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;

public class CalculoTFIDF {
    
	public static HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido = new HashMap<String,tupla<Double, HashMap<File, Double>>>();
    /*Creo un índice invertido para todas las clases 
     * porque es un sólo indice global por consulta.
     * Asi que en vez de copiarlo en cada archivo, simplemente lo creo como
     * estático
	*/
	private static double numFiles = 0;
	/*
	 * Guardo esta variable auxiliar para averiguar el 
	 * numero de archivos mirados para el calculo del IDF.
	 * 
	 * Se incrementará hasta N durante el calculo del TF2
	 */
	
	public HashMap<String,Double> calcularTF1(ArrayList<String> vText){
        HashMap<String,Double> mapaOcurrencias = new HashMap<String,Double>();
        double ocurrenciasP = 0;
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
        numFiles++;
        
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
                HashMap<File, Double> mapaArchivo = new HashMap<File, Double>();
                mapaArchivo.put(f, tf);
                indiceInvertido.put(palabra, new tupla(0,mapaArchivo));
            }
        }
    }

    public void calcularIDF() {
        for(String palabra : indiceInvertido.keySet()){
            indiceInvertido.get(palabra).setIDF(
            		Math.log(numFiles/indiceInvertido.get(palabra).docPeso().size())/ Math.log(2));
            //Log2(numero de archivos / numero de archivos que contienen palabra)
        }
    }
    
    public HashMap<File, Double> calcularLongitud(){
    	HashMap<File, Double> Longitudes = new HashMap<File, Double>();
    	double idf;
    	double TFIDF;
    	
    	/*
         * Para cada palabra P del indice Invertido
         * 	-Obtengo el IDF de P
         * 	-Para cada archivo F que contiene la palbra P
         * 		-*Multiplico su TF por el IDF previo y elevo el resultado al cuadrado	
         * 		-Si el mapa de longitudes tiene el archivo F ya añadido
         * 			-*SI:		Sumo el TFIDF nuevo al documento F
         * 		-Inserto en el archivo F el nuevo TFIDF (La suma con el actual o uno nuevo en caso de no existir)
         */	
    	for(String P : indiceInvertido.keySet()){

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
		return Longitudes;
    }
}


