package Recuperation;

import Filtros.Filtrado;
import Preprocesadores.Preprocesado;
import SeparacionPalabras.Divisor;

import java.io.IOException;
import java.util.ArrayList;

public class Recuperation {
	
	private String terminos;
	ArrayList<String> vText;
	public static ArrayList<String> vTextProcesado = new ArrayList<String>();
	Filtrado F;
	Preprocesado P;
	Divisor D;
	Lector L = new Lector();
	OrdenacionDoc oD = new OrdenacionDoc();

	public void execute() throws IOException{
		System.out.println("Aplicando filtros a terminos de consulta...");
    	//Filtrado
		F = new Filtrado(terminos);
		terminos = F.execute();

		//Division en Lista
		D =  new Divisor();
		vText = D.execute(terminos);

		//Preprocesado
		P = new Preprocesado(vText);
    	vTextProcesado = P.execute();

		oD.createList();
		oD.showList();
	}
	
	public void getIndex() throws IOException{
		L.readDocs();
	}
	
	public void read(String[] args){
		terminos = "";
		for(String a : args)
			terminos = terminos+a+" ";
	}
	public void read(String s){
		terminos = s;
	}
}
