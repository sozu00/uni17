package Recuperation;

import Filtros.CadenaFiltros;
import Filtros.filtroRegex;
import Indexing.AppPath;
import TFIDF.tupla;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by sozu on 21/05/2017.
 */
public class Lector {

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
        String LD = new String(Files.readAllBytes(Paths.get(AppPath.RESWindows + "longDocumentos")));
        longDoc = new HashMap<File, Double>();
        LD = CdF.ejecutar(LD);
        ArrayList<String> longitudT = new ArrayList<String>(Arrays.asList(LD.split(",")));
        for (String s : longitudT) {
            ArrayList<String> doc = new ArrayList<String>(Arrays.asList(s.split("=")));
            longDoc.put(new File(doc.get(0)), Double.parseDouble(doc.get(1)));
        }
        //System.out.print(longDoc);
    }

    private void readIndice() throws IOException{
        String indInv = new String(Files.readAllBytes(Paths.get(AppPath.RESWindows+"indiceInvertido")));
        indiceInvertido = new HashMap<String, tupla<Double, HashMap<File, Double>>>();
        indInv = CdF.ejecutar(indInv);

        ArrayList<String> indiceT =  new ArrayList<String>(Arrays.asList(indInv.split("@@")));

        int i = indiceT.size()-1;
        ArrayList<String> Superior = new ArrayList<String>(Arrays.asList(indiceT.get(i-1).split(",")));
        ArrayList<String> Actual = new ArrayList<String>(Arrays.asList(indiceT.get(i).split(",")));
        indiceT.set(i, Superior.get(Superior.size()-1)+Actual.toString());

        for(i = indiceT.size()-2; i>0 ;i--){
            Superior = new ArrayList<String>(Arrays.asList(indiceT.get(i-1).split(",")));
            Actual = new ArrayList<String>(Arrays.asList(indiceT.get(i).split(",")));
            Actual.remove(Actual.size()-1);
            indiceT.set(i, Superior.get(Superior.size()-1)+Actual.toString());
        }
        indiceT.remove(0);

        for(String s : indiceT){
            s = new filtroRegex("]","").ejecutar(s);
            s = new filtroRegex(" +", "").ejecutar(s);
            ArrayList<String> L = new ArrayList<String>(Arrays.asList(s.split("\\x5b")));
            ArrayList<String> palIDF = new ArrayList<String>(Arrays.asList(L.get(0).split("=")));
            ArrayList<String> fileTF = new ArrayList<String>(Arrays.asList(L.get(1).split(",")));

            double idf = Double.parseDouble(palIDF.get(1));
            indiceInvertido.put(palIDF.get(0), new tupla(idf, new HashMap<File, Double>()));

            for(String fTF : fileTF){
                ArrayList<String> archivoTF = new ArrayList<String>(Arrays.asList(fTF.split("=")));
                indiceInvertido.get(palIDF.get(0)).docPeso().put(new File(archivoTF.get(0)), Double.parseDouble(archivoTF.get(1)));
            }

        }

    }
}
