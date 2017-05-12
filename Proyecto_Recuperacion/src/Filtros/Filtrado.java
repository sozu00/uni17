package Filtros;

import java.io.IOException;
import java.util.ArrayList;

import Preprocesadores.CadenaPreprocesadores;
import Preprocesadores.preprocesadorLongitud;
import Preprocesadores.preprocesadorPalabrasVacias;
import Preprocesadores.preprocesadorStemming;

public class Filtrado {
	String texto;
	
	public Filtrado(String s){
		texto = s;
	}
	
	
	public String execute() throws IOException{
		CadenaFiltros CdF = new CadenaFiltros();
		CdF.add(new filtroMayusculas());
		CdF.add(new filtroRegex("'", ""));
		CdF.add(new filtroRegex("[^-\\w]", " "));
		CdF.add(new filtroRegex("\\b[0-9]+\\b", " "));
		CdF.add(new filtroRegex("-+ | -+", " "));
		CdF.add(new filtroRegex(" +", " "));
		CdF.add(new filtroRegex("^-+", ""));
		return CdF.ejecutar(texto);
	}

	
}
