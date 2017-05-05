package Preprocesadores;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

import Indexing.AppPath;

public class preprocesadorPalabrasVacias implements preprocesadorGenerico{
	

	public ArrayList<String> ejecutar(ArrayList<String> vText) throws IOException{
		String texto = new String(Files.readAllBytes(Paths.get(AppPath.EmptyWindows)));
		ArrayList<String> palabrasVacias = new 
				ArrayList<String>(Arrays.asList(texto.split(" ")));
		
		for(String p : palabrasVacias){
			while(vText.contains(p)){
			    vText.remove(p);
		    }
		}
		
		return vText;
	}
}