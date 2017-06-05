package Recuperation;

import Filtros.Filtrado;
import Preprocesadores.Preprocesado;
import SeparacionPalabras.Divisor;

import java.io.IOException;
import java.util.ArrayList;

public class Recuperation {

	private String terminos;
	public static ArrayList<String> vTextProcesado = new ArrayList<>();
	private final Lector L = new Lector();
	private final OrdenacionDoc oD = new OrdenacionDoc();

	public void execute() throws IOException {
		System.out.println("Aplicando filtros a terminos de consulta...");
		// Filtrado

		Filtrado f = new Filtrado(terminos);
		terminos = f.execute();

		// Division en Lista
		Divisor d = new Divisor();
		ArrayList<String> vText = d.execute(terminos);

		// Preprocesado
		Preprocesado p = new Preprocesado(vText);
		vTextProcesado = p.execute();

		oD.createList();
		oD.showList();
	}

	public void getIndex() throws IOException {
		L.readDocs();
	}

	public void read(String s) {
		terminos = s;
	}
}
