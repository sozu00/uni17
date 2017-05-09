package Filtros;
import java.util.regex.*;

public class filtroRegex implements filtroGenerico {
	
	private String expresion;
	public filtroRegex(String s){
		expresion = s;
	}
	public String ejecutar(String s) {
		Pattern pat = Pattern.compile(expresion);
		Matcher mat = pat.matcher(s);
		if(s == "'" || s=="^-+") return mat.replaceAll("");
		//Caso especial en inglés, el apostrofe no debe ser sustituido por un espacio, pues crea palabras erroneas
	    return mat.replaceAll(" ");
	}

}
