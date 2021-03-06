package Filtros;

public class Filtrado {
	private final String texto;
	
	public Filtrado(String s){
		texto = s;
	}
	
	
	public String execute() {
		CadenaFiltros CdF = new CadenaFiltros();
		CdF.add(new filtroMayusculas());
		CdF.add(new filtroRegex("'", ""));
		CdF.add(new filtroRegex("[^-\\w]", " "));
		CdF.add(new filtroRegex("\\b[0-9]+\\b", " "));
		CdF.add(new filtroRegex("-+ | -+", " "));
		CdF.add(new filtroRegex(" +", " "));
		CdF.add(new filtroRegex("^-+", ""));
		return CdF.ejecutar(texto);
	}

	
}
