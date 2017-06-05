package Recuperation;

import Filtros.CadenaFiltros;
import Filtros.filtroRegex;
import TFIDF.tupla;
import main.AppPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@SuppressWarnings("DefaultFileTemplate")
class Lector {

    private CadenaFiltros CdF;
    public static HashMap<File, Double> longDoc;
    public static HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido;

    public void readDocs() throws IOException {
        CdF = new CadenaFiltros();
        CdF.add(new filtroRegex(" +", ""));
        CdF.add(new filtroRegex("[{}]",""));
        readIndice();
        readlongitud();
    }

    private void readlongitud() throws IOException {
        
    	String LD = new String(Files.readAllBytes(Paths.get(AppPath.RES + "longDocumentos")));
        longDoc = new HashMap<>();
        
        LD = CdF.ejecutar(LD);
        
        ArrayList<String> longitudT = new ArrayList<>(Arrays.asList(LD.split(",")));
        
        for (String s : longitudT) {
            ArrayList<String> doc = new ArrayList<>(Arrays.asList(s.split("=")));
            longDoc.put(new File(doc.get(0)), Double.parseDouble(doc.get(1)));
        }
    }

    private void readIndice() throws IOException{
        String indInv = new String(Files.readAllBytes(Paths.get(AppPath.RES+"indiceInvertido")));
        indiceInvertido = new HashMap<>();
        
        indInv = CdF.ejecutar(indInv);
        
        /*Separo Palabra+IDF de las tuplas
         * porque estan situadas talque
         * word=IDF@@FilePathWord=TF,FilePath2Word=TF2...
         */
        ArrayList<String> indiceT = new ArrayList<>(Arrays.asList(indInv.split("@@")));
        
        
        /*Pequeño FIX porque los TF de cada palabra están colocados en la fila inferior
         * tal que 
         * word=IDF
         * FilePathWord=TF, FilePath2Word=TF2, word2=IDF2
         * FilePathWord2=TF, FilePath2Word2=TF2, word3=IDF3		
         */
        int i = indiceT.size()-1;
        ArrayList<String> Superior = new ArrayList<>(Arrays.asList(indiceT.get(i - 1).split(",")));
        ArrayList<String> Actual = new ArrayList<>(Arrays.asList(indiceT.get(i).split(",")));
        indiceT.set(i, Superior.get(Superior.size()-1)+Actual.toString());

        for(i = indiceT.size()-2; i>0 ;i--){
            Superior = new ArrayList<>(Arrays.asList(indiceT.get(i - 1).split(",")));
            Actual = new ArrayList<>(Arrays.asList(indiceT.get(i).split(",")));
            Actual.remove(Actual.size()-1);
            indiceT.set(i, Superior.get(Superior.size()-1)+Actual.toString());
        }
        indiceT.remove(0);
       
        
        /*
         * Creación del índice invertido finalmente tras limpiar carácteres innecesarios
         */
        for(String s : indiceT){
            s = new filtroRegex("]","").ejecutar(s);
            s = new filtroRegex(" +", "").ejecutar(s);
            ArrayList<String> L = new ArrayList<>(Arrays.asList(s.split("\\x5b")));// "[" ese caracter esta reservado
            ArrayList<String> palIDF = new ArrayList<>(Arrays.asList(L.get(0).split("=")));
            ArrayList<String> fileTF = new ArrayList<>(Arrays.asList(L.get(1).split(",")));

            double idf = Double.parseDouble(palIDF.get(1));
            indiceInvertido.put(palIDF.get(0), new tupla<>(idf, new HashMap<>()));

            for(String fTF : fileTF){
                ArrayList<String> archivoTF = new ArrayList<>(Arrays.asList(fTF.split("=")));
                indiceInvertido.get(palIDF.get(0)).docPeso().put(new File(archivoTF.get(0)), Double.parseDouble(archivoTF.get(1)));
            }

        }

    }
}
