import java.util.regex.*;

public class filtroRegex implements filtroGenerico {
	
	private String expresion;
	public filtroRegex(String s){
		expresion = s;
	}
	public String ejecutar(String s) {
		Pattern pat = Pattern.compile(expresion);
		Matcher mat = pat.matcher(s);
	    return mat.replaceAll(" ");
	}

}
