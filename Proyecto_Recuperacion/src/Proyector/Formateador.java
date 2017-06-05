package Proyector;

import Indexing.Indexing;
import Recuperation.OrdenacionDoc;
import Recuperation.Recuperation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;

public class Formateador {

	@SuppressWarnings("resource")
	private void showResults() {
		try {
			int Limit = 10;

			if (OrdenacionDoc.ListaDocs.size() < Limit)
				Limit = OrdenacionDoc.ListaDocs.size();
			for (int i = 0; i < Limit; i++) {
				Entry<File, Double> FD = OrdenacionDoc.ListaDocs.get(i);
				System.out.println("Document ID: " + FD.getKey().getName() + "\t\t(weight: " + FD.getValue() + ")");
				System.out.println("Summary: " + new BufferedReader(new FileReader(FD.getKey())).readLine() + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showMenu() throws IOException {

		Scanner scan = new Scanner(System.in);

		int choice;
		do {
			System.out.println("Seleccione qué desea hacer:\n1)Indexación\n2)Recuperación\n3)Salir");
			choice = Integer.valueOf(scan.nextLine());
			switch (choice) {
			case 1:
				Indexing i = new Indexing();
				i.execute();
				break;
			case 2:
				Recuperation R = new Recuperation();
				System.out.println("Leyendo indice invertido...");
				R.getIndex();
				String consulta;
				do {
					System.out.println("Inserte consulta (Ctrl^Z para salir): ");
					consulta = scan.nextLine();
					R.read(consulta);
					R.execute();
					showResults();
				} while (!Objects.equals(consulta, "exit"));
			case 3:
				break;
			default:
				System.out.println("Error, inserte de nuevo elección.");
			}
		} while (choice != 3);

	}
}
