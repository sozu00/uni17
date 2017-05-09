package Indexing;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import Filtros.*;
import Preprocesadores.*;
import SeparacionPalabras.Divisor;
import TFIDF.*;

public class Indexing {
	
	public void execute() throws IOException{

		File file = new File(AppPath.DATA);
		String texto = "";
		ArrayList<String> vText;
		ArrayList<String> vTextProcesado = new ArrayList<String>();
		CadenaFiltros CdF = new CadenaFiltros();
		CadenaPreprocesadores CdP = new CadenaPreprocesadores();
		HashMap<String, Double> textFrecuencia;
		HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido = CalculoTFIDF.indiceInvertido;
		
		//Filtros
		CdF.add(new filtroMayusculas());
		CdF.add(new filtroRegex("[^-\\w]"));
		CdF.add(new filtroRegex("\\b[0-9]+\\b"));
		CdF.add(new filtroRegex("-+ | -+"));
		CdF.add(new filtroRegex(" +"));
		
		//Division de palabras
		Divisor d = new Divisor();

		//Preprocesadores
		CdP.add(new preprocesadorPalabrasVacias());
		CdP.add(new preprocesadorStemming());
		preprocesadorLongitud.setLength(-2);
		CdP.add(new preprocesadorLongitud());

		//TFIDF
		CalculoTFIDF TF = new CalculoTFIDF();

		if(file.isDirectory())
			for (File f : file.listFiles()) {

				texto = new String(Files.readAllBytes(Paths.get(f.getPath())));

		    	//Filtrado
				texto = CdF.ejecutar(texto);

				//Division en Lista
				vText = d.ejecutar(texto);

				//Preprocesado
		    	vTextProcesado = CdP.ejecutar(vText);
				//System.out.println(vTextProcesado.toString());

				//Calculo TF
				textFrecuencia = TF.calcularTF1(vTextProcesado);
				//indiceInvertido = TF.calcularTF2(textFrecuencia,f);
				TF.calcularTF2(textFrecuencia,f);
				}

				//TF.calcularIDF(indiceInvertido, numFiles);
				TF.calcularIDF();
	}
	
}
