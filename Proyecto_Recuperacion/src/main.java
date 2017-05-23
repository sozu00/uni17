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
		AppPath.setData(0);
		//Obtengo las rutas para Linux
		
		String consulta = scan.nextLine();
		Indexing i = new Indexing();

		preprocesadorLongitud.setLength(0);
		
		i.execute();

		Recuperation R = new Recuperation();

		R.read(consulta);
		R.execute();

		Formateador F = new Formateador();
		F.showResults();
	}
}
