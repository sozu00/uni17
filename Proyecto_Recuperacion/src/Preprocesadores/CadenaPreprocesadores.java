package Preprocesadores;
import java.io.IOException;
import java.util.ArrayList;

public class CadenaPreprocesadores {
	private ArrayList<preprocesadorGenerico> preprocesadores = new ArrayList<preprocesadorGenerico>();
	
	public void add(preprocesadorGenerico p){
		preprocesadores.add(p);
	}
	
	public ArrayList<String> execute(ArrayList<String> s) throws IOException{
		ArrayList<String> t = s;
		for(preprocesadorGenerico P : preprocesadores)
			t = P.execute(t);
		return t;
	}
}
