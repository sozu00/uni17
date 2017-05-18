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
         * Para cada palabra del texto, si el mapa de ocurrencias la contiene ya la ocurrencia incremente 1
         * En caso de que no la contenga, creo 
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
        double tf = 0; 	
        numFiles++;
        for (String palabra : textFrecuencia.keySet()) {
            tf = 1 + Math.log(textFrecuencia.get(palabra)) / Math.log(2);
          
            //Si el indice tiene la palabra ya creada
            if (indiceInvertido.containsKey(palabra))
                indiceInvertido.get(palabra).docPeso().put(f, tf);
            //Añado la palabra P en el documento f                
            
            else {
            	/*
            	 * En caso de no existir la palabra en el mapa, se crea un nuevo map para esa palabra
            	 * en el archivo F y se añade con IDF = 0
            	 */
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
    	double idf = 0;
    	double TFIDF = 0;
    	
    	for(String palabra : indiceInvertido.keySet()){
    		
    		idf = indiceInvertido.get(palabra).IDF();
    		
    		for(File f : indiceInvertido.get(palabra).docPeso().keySet()){
    			
    			TFIDF = indiceInvertido.get(palabra).docPeso().get(f) * idf;
    			
    			if(Longitudes.containsKey(f))
    				TFIDF += Longitudes.get(f);
    			
    			Longitudes.put(f, TFIDF);
    		}
    	}
    	
    	for(File f : Longitudes.keySet())
    		Longitudes.put(f, Math.sqrt(Longitudes.get(f)));
		return Longitudes;
    }
}


