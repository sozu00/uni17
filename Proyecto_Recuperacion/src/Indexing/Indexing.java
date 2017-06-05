package Indexing;

import Filtros.Filtrado;
import Preprocesadores.Preprocesado;
import SeparacionPalabras.Divisor;
import TFIDF.CalculoTFIDF;
import TFIDF.tupla;
import main.AppPath;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class Indexing {
	public static double numFiles;
	
	public void execute() throws IOException{

		File file = new File(AppPath.Corpus);
		String texto;
		ArrayList<String> vText;
		ArrayList<String> vTextProcesado;
		Filtrado F;
		Preprocesado P;
		Divisor D;
		CalculoTFIDF TF = new CalculoTFIDF();
		HashMap<String, Double> textFrecuencia;
		HashMap<String,tupla<Double, HashMap<File, Double>>> indiceInvertido = CalculoTFIDF.indiceInvertido;
		System.out.println("Aplicando filtros a documentos...");
		int i= 0;
		
		if(file.isDirectory())
			numFiles = file.list().length;
			for (File f : file.listFiles()) {

				mostrarIndexing(i);

				texto = new String(Files.readAllBytes(Paths.get(f.getPath())));

				//Filtrado
				F = new Filtrado(texto);
				texto = F.execute();

				//Division en Lista
				D = new Divisor();
				vText = D.execute(texto);

				//Preprocesado
				P = new Preprocesado(vText);
				vTextProcesado = P.execute();

				//Calculo TF
				textFrecuencia = TF.calcularTF1(vTextProcesado);
				TF.calcularTF2(textFrecuencia, f);
				i++;
			}

			System.out.println();
			TF.calcularIDFyLongitud();
			try {
				PrintWriter indInv = new PrintWriter(AppPath.RES + "indiceInvertido");
				PrintWriter longD = new PrintWriter(AppPath.RES + "longDocumentos");

				longD.write(CalculoTFIDF.Longitudes.toString());
				indInv.write(indiceInvertido.toString());

				longD.close();
				indInv.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	private void mostrarIndexing(int i){
		double porcentaje = (i*100.0/numFiles);
		int cuarto = (int)porcentaje/4;
		StringBuilder barra = new StringBuilder("<");
		for(int j = 0; j < cuarto; j++){
			barra.append("=");
		}
		for(int j = cuarto; j < 25; j++){
			barra.append(" ");
		}
		NumberFormat formatter = new DecimalFormat("#0.00");
		System.out.print("\r"+barra+">\t"+formatter.format(porcentaje)+"% de archivos indexados");
	}
	
}
