package Indexing;

import java.io.*;
import java.nio.file.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

import Filtros.*;
import Preprocesadores.*;
import SeparacionPalabras.Divisor;
import TFIDF.*;

public class Indexing {
	public static int numFiles;
	
	public void execute() throws IOException{

		File file = new File(AppPath.Corpus);
		numFiles = file.list().length;
		String texto;
		ArrayList<String> vText;
		ArrayList<String> vTextProcesado = new ArrayList<String>();
		Filtrado F;
		Preprocesado P;
		Divisor D;
		CalculoTFIDF TF = new CalculoTFIDF();
		HashMap<String, Double> textFrecuencia;
		HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido = CalculoTFIDF.indiceInvertido;
		System.out.println("Aplicando filtros a documentos...");
		int i= 0;
		
		if(file.isDirectory())
			for (File f : file.listFiles()) {
				
				mostrarIndexing(i);
				
				texto = new String(Files.readAllBytes(Paths.get(f.getPath())));

		    	//Filtrado
				F = new Filtrado(texto);
				texto = F.execute();

				//Division en Lista
				D =  new Divisor();
				vText = D.execute(texto);

				//Preprocesado
				P = new Preprocesado(vText);
		    	vTextProcesado = P.execute();
				
				//Calculo TF
				textFrecuencia = TF.calcularTF1(vTextProcesado);
				TF.calcularTF2(textFrecuencia,f);
				i++;
			}
		
			System.out.println();
			TF.calcularIDF();
			try{ 
				PrintWriter indInv = new PrintWriter(AppPath.RES+"indiceInvertido");
				PrintWriter longD = new PrintWriter(AppPath.RES+"longDocumentos");
				
				longD.write(TF.calcularLongitud().toString());
			    indInv.write(indiceInvertido.toString());
			    
			    longD.close();
			    indInv.close();
			} catch(Exception e){}
			
	}
	
	
	public void mostrarIndexing(int i){
		double porcentaje = (i*100.0/numFiles);
		int cuarto = (int)porcentaje/4;
		String barra = "<";
		for(int j = 0; j < cuarto; j++){
			barra = barra+"=";
		}
		for(int j = cuarto; j < 25; j++){
			barra = barra+" ";
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
		System.out.print("\r"+barra+">\t"+formatter.format(porcentaje)+"% de archivos indexados");
	}
	
}
