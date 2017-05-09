package Indexing;
import java.io.File;

import TFIDF.CalculoTFIDF;

public class pruebaIndexing {
	public static void test(){
		for (String name : CalculoTFIDF.indiceInvertido.keySet()) {
			String palabra = name.toString();
			System.out.print("Word:\t"+palabra+"\nIDF:\t"+CalculoTFIDF.indiceInvertido.get(palabra).IDF()+"\nTF: ");
			for (File archivo : CalculoTFIDF.indiceInvertido.get(name).docPeso().keySet()) {
				double value = CalculoTFIDF.indiceInvertido.get(name).docPeso().get(archivo);
				System.out.format("\t%f - %s\n", value , archivo.getName());
			}
			System.out.println("\n");
		}
	}
}
