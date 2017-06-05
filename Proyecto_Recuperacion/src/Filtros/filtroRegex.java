package Filtros;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class filtroRegex implements filtroGenerico {
	
	private final String expresion;
	private final String sustituto;
	public filtroRegex(String e, String s){
		expresion = e;
		sustituto = s;
	}
	public String ejecutar(String s) {
		Pattern pat = Pattern.compile(expresion);
		Matcher mat = pat.matcher(s);
	    return mat.replaceAll(sustituto);
	}

}
