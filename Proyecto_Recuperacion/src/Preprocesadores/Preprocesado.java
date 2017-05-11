package Preprocesadores;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Preprocesado {
	ArrayList<String> vText;
	
	public Preprocesado(ArrayList<String> s){
		vText = s;
	}
	
	
	public ArrayList<String> execute() throws IOException{
		CadenaPreprocesadores CdP = new CadenaPreprocesadores();
		CdP.add(new preprocesadorPalabrasVacias());
		CdP.add(new preprocesadorStemming());
		preprocesadorLongitud.setLength(-2);
		CdP.add(new preprocesadorLongitud());
		return CdP.ejecutar(vText);
	}
}
