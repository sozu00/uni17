import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class preprocesadorPalabrasVacias implements preprocesadorGenerico{
	

	public ArrayList<String> ejecutar(ArrayList<String> vText) throws IOException{
		String texto = new String(Files.readAllBytes(Paths.get(AppPath.Empty)));
		ArrayList<String> palabrasVacias = new 
				ArrayList<String>(Arrays.asList(texto.split("\n")));
		
		for(String p : palabrasVacias){
			while(vText.contains(p)) 
				vText.remove(p);
		}
		
		return vText;
	}
}
