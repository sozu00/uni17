package Recuperation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Filtros.CadenaFiltros;
import Filtros.Filtrado;
import Filtros.filtroGenerico;
import Filtros.filtroRegex;
import Indexing.AppPath;
import Preprocesadores.Preprocesado;
import SeparacionPalabras.Divisor;
import TFIDF.tupla;

public class Recuperation {
	
	private String terminos;
	private ArrayList<String> Consulta;
	ArrayList<String> vText;
	ArrayList<String> vTextProcesado = new ArrayList<String>();
	Filtrado F;
	Preprocesado P;
	Divisor D;
	HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido;
	HashMap<File, Double> longDoc;
	CadenaFiltros CdF;
	public void execute() throws IOException{
		Consulta = new ArrayList<String>();
		
    	//Filtrado
		F = new Filtrado(terminos);
		terminos = F.execute();

		//Division en Lista
		D =  new Divisor();
		vText = D.ejecutar(terminos);

		//Preprocesado
		P = new Preprocesado(vText);
    	vTextProcesado = P.execute();
    	
    	readDocs();
    	
	}
	
	
	private void readDocs() throws IOException {
		CdF = new CadenaFiltros();
		CdF.add(new filtroRegex(" +", ""));
		CdF.add(new filtroRegex("[{}]",""));
		readIndice();
		readlongitud();
	}
	private void readlongitud() throws IOException{
		String LD = new String(Files.readAllBytes(Paths.get(AppPath.RES+"longDocumentos")));
		longDoc = new HashMap<File, Double>();
		LD = CdF.ejecutar(LD);
		ArrayList<String> longitudT =  new ArrayList<String>(Arrays.asList(LD.split(",")));
		for(String s : longitudT){
			ArrayList<String> doc = new ArrayList<String>(Arrays.asList(s.split("=")));
			longDoc.put(new File(doc.get(0)), Double.parseDouble(doc.get(1)));
		}
		System.out.print(longDoc);
	}

	private void readIndice() throws IOException{
		String indInv = new String(Files.readAllBytes(Paths.get(AppPath.RES+"indiceInvertido")));
		indiceInvertido = new HashMap<String,tupla<Double, HashMap<File, Double>>>();
		
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
			ArrayList<String> L = new ArrayList<String>(Arrays.asList(s.split("\\x5b")));
			ArrayList<String> palIDF = new ArrayList<String>(Arrays.asList(L.get(0).split("=")));
			System.out.println(palIDF.get(1));
			ArrayList<String> fileTF = new ArrayList<String>(Arrays.asList(L.get(1).split(",")));

			double idf = Double.parseDouble(palIDF.get(1));
			indiceInvertido.put(palIDF.get(0), new tupla(idf, new HashMap<File, Double>()));
			
			for(String fTF : fileTF){
				ArrayList<String> archivoTF = new ArrayList<String>(Arrays.asList(fTF.split("=")));
				indiceInvertido.get(palIDF.get(0)).docPeso().put(new File(archivoTF.get(0)), Double.parseDouble(archivoTF.get(1)));
			}
			
		}

	}
	
	public void read(String[] args){
		terminos = "";
		for(String a : args)
			terminos = terminos+a;
	}
}
