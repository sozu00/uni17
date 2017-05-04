import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import Filtros.CadenaFiltros;
import Filtros.filtroMayusculas;
import Filtros.filtroRegex;
import Preprocesadores.CadenaPreprocesadores;
import Preprocesadores.preprocesadorPalabrasVacias;
import SeparacionPalabras.Divisor;

public class Indexing {
	
	public void execute() throws IOException{
		/*
		 * TODO:
		 * 	Main de Indexacion:
		 * 		Leer archivos
		 * 		Aplicar filtros
		 * 		Separar palabras
		 * 		Quitar vacías
		 * 			 
		 * 
		 */
		File file = new File(AppPath.DATA); 
		String texto = "";
		ArrayList<String> vText = new ArrayList<String>();
		ArrayList<String> vTextProcesado = new ArrayList<String>();
		CadenaFiltros CdF = new CadenaFiltros();
		Divisor d = new Divisor();
		CadenaPreprocesadores CdP = new CadenaPreprocesadores();
		
		CdF.add(new filtroMayusculas());
		CdF.add(new filtroRegex("[^-\\w]"));
		CdF.add(new filtroRegex("\\b[0-9]+\\b"));
		CdF.add(new filtroRegex("-+ | -+"));
		CdF.add(new filtroRegex(" +"));
		
		CdP.add(new preprocesadorPalabrasVacias());
		if(file.isDirectory()) {
			for (File f : file.listFiles()) {
		    	texto = new String(Files.readAllBytes(Paths.get(f.getPath())));
		    	texto = CdF.ejecutar(texto);
		    	
		    	vText = new ArrayList<String>(d.extraerPalabras(texto));
		    	vTextProcesado = CdP.ejecutar(vText);
			}
		}
		
		System.out.print(vTextProcesado.toString());

		
		
	}
	
}
