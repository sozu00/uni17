package Preprocesadores;
import java.io.IOException;
import java.util.ArrayList;

public interface preprocesadorGenerico {
	public ArrayList<String> execute(ArrayList<String> vText) throws IOException;
}
