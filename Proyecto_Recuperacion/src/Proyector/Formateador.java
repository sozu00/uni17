package Proyector;

import Recuperation.OrdenacionDoc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

/**
 * Created by sozu on 21/05/2017.
 */
public class Formateador {
    List<Map.Entry<File, Double>> ListaDocs;


    public Formateador(){
        ListaDocs = OrdenacionDoc.ListaDocs;
    }

    public void showResults(){
        try{
            for(Map.Entry<File, Double> FD : ListaDocs){
                System.out.println("Document ID: "+FD.getKey().getName() + "\t\t(weight: "+FD.getValue()+")");
                System.out.println("Summary: "+new BufferedReader(new FileReader(FD.getKey())).readLine()+"\n");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
