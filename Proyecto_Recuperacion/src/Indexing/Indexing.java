package Indexing;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import Filtros.*;
import Preprocesadores.*;
import SeparacionPalabras.Divisor;
import TFIDF.*;

public class Indexing {
	
	public void execute() throws IOException{

		File file = new File(AppPath.DATAWindows);
		String texto = "";
		ArrayList<String> vText;
		ArrayList<String> vTextProcesado = new ArrayList<String>();
		Filtrado F;
		Preprocesado P;
		Divisor D;
		CalculoTFIDF TF = new CalculoTFIDF();
		HashMap<String, Double> textFrecuencia;
		HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido = CalculoTFIDF.indiceInvertido;
		

		if(file.isDirectory())
			for (File f : file.listFiles()) {
				
				texto = new String(Files.readAllBytes(Paths.get(f.getPath())));
				
		    	//Filtrado
				F = new Filtrado(texto);
				texto = F.execute();

				//Division en Lista
				D =  new Divisor();
				vText = D.ejecutar(texto);

				//Preprocesado
				P = new Preprocesado(vText);
		    	vTextProcesado = P.execute();
				
				//Calculo TF
				textFrecuencia = TF.calcularTF1(vTextProcesado);
				TF.calcularTF2(textFrecuencia,f);
			}

			TF.calcularIDF();
			try{ 
				PrintWriter indInv = new PrintWriter(AppPath.RESWindows+"indiceInvertido");
				PrintWriter longD = new PrintWriter(AppPath.RESWindows+"longDocumentos");
				longD.write(TF.calcularLongitud().toString());
			    indInv.write(indiceInvertido.toString());
			    longD.close();
			    indInv.close();
			} catch(Exception e){}
			
	}
	
}
