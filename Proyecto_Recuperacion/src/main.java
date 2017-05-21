import Indexing.*;
import Preprocesadores.preprocesadorLongitud;
import Proyector.Formateador;
import Recuperation.Recuperation;

import java.io.IOException;
import java.util.Scanner;

public class main {
	
	public static void main(String[] args) throws IOException{
		System.out.println("Inserte consulta: ");
		Scanner scan = new Scanner(System.in);
		AppPath.setData(AppPath.CorpusWindows);
		String text = scan.nextLine();
		Indexing i = new Indexing();

		preprocesadorLongitud.setLength(0);
		i.execute();

		Recuperation R = new Recuperation();

		R.read(text);
		R.execute();

		Formateador F = new Formateador();
		F.Resultado();
	}
}
