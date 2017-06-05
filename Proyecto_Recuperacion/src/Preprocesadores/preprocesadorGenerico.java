package Preprocesadores;
import java.io.IOException;
import java.util.ArrayList;

interface preprocesadorGenerico {
	ArrayList<String> execute(ArrayList<String> vText) throws IOException;
}
