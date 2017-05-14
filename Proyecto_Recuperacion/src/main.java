import Indexing.*;
import Recuperation.Recuperation;

import java.io.IOException;

public class main {
	
	public static void main(String[] args) throws IOException{
		Indexing i = new Indexing();
		i.execute();
		//pruebaIndexing.test();
		Recuperation R = new Recuperation();
		R.read(args);
		R.execute();
	}
	
	
}
