package Proyector;

import Recuperation.OrdenacionDoc;
import Recuperation.Recuperation;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Indexing.AppPath;
import Indexing.Indexing;
import Preprocesadores.preprocesadorLongitud;

/**
 * Created by sozu on 21/05/2017.
 */
public class Formateador {
    Scanner scan;
    int choice;
    String consulta;
    
    public void showResults(){
        try{
            for(Map.Entry<File, Double> FD : OrdenacionDoc.ListaDocs){
                System.out.println("Document ID: "+FD.getKey().getName() + "\t\t(weight: "+FD.getValue()+")");
                System.out.println("Summary: "+new BufferedReader(new FileReader(FD.getKey())).readLine()+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void showMenu() throws IOException{
    	scan = new Scanner(System.in);
    	
    	AppPath.setData(1);
    	do{
    		System.out.println("Seleccione qué desea hacer:\n1)Indexación\n2)Recuperación\n3)Salir");
    		choice = Integer.valueOf(scan.nextLine());
        	
    		switch(choice){
    		case 1: Indexing i = new Indexing();
    				preprocesadorLongitud.setLength(0);
    				i.execute(); 
    				break;
    		case 2:	Recuperation R = new Recuperation();
		    		System.out.println("Inserte consulta: ");
		    		String consulta = scan.nextLine();
		    		R.read(consulta);
		    		R.execute();
		    		showResults();
    		case 3: break;
		    default: System.out.println("Error, inserte de nuevo elección.");
    		}
    	}while(choice != 3);
    	
    }
}
