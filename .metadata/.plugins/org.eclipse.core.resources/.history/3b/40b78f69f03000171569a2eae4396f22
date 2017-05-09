import java.util.regex.*;

public class filtroPuntuacion implements filtroGenerico {

	@Override
	public String ejecutar(String s) {
		Pattern pat = Pattern.compile("\\p{Punct}");
	    String [] num = pat.split(s);
	    String texto = "";
	    for(String x : num)  texto = texto.concat(x);
	    return texto;
	}

}
