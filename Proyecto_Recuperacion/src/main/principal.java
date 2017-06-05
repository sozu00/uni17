package main;

import Preprocesadores.preprocesadorLongitud;
import Proyector.Formateador;

import java.io.IOException;

class principal {
	
	public static void main(final String[] args) throws IOException{
		preprocesadorLongitud.setLength(2);
		Formateador F = new Formateador();
		F.showMenu();
	}
}
