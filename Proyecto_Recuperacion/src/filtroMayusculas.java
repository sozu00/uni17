
public class filtroMayusculas implements filtroGenerico{

	@Override
	public String ejecutar(String s) {
		return s.toUpperCase();
	}

}
