package Filtros;

public class filtroMayusculas implements filtroGenerico{

	public String ejecutar(String s) {
		return s.toLowerCase();
	}

}