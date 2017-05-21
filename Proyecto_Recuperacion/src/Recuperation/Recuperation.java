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
import Indexing.pruebaIndexing;
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
	Lector L = new Lector();
	OrdenacionDoc oD = new OrdenacionDoc();

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

    	L.readDocs();

		oD.crearLista();
		oD.mostrarLista(2);

	}

	public void read(String[] args){
		terminos = "";
		for(String a : args)
			terminos = terminos+a+" ";
	}
}
