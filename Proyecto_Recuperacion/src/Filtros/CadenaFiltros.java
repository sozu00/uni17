package Filtros;
import java.util.ArrayList;

public class CadenaFiltros {
	
	private ArrayList<filtroGenerico> Filtros = new ArrayList<filtroGenerico>();
	
	public void add(filtroGenerico f){
		Filtros.add(f);
	}
	
	public String ejecutar(String s){
		String t = s;
		for(filtroGenerico F : Filtros)
			t = F.ejecutar(t);
		return t;
	}
	
}
