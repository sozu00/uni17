package Indexing;

public class AppPath {
	
	public static String DATA;
	public static String Empty;
	public static String RES;
	public static String Corpus;
	public static final String DATALinux = "/home/sozu00/REC-INF/uni17/Proyecto_Recuperacion/data";
	public static final String EmptyLinux = "/home/sozu00/REC-INF/uni17/Proyecto_Recuperacion/emptywords/emptywords";
	public static final String RESLinux = "/home/sozu00/REC-INF/uni17/Proyecto_Recuperacion/results/";
	public static final String CorpusLinux = "/home/sozu00/REC-INF/uni17/Proyecto_Recuperacion/corpus/";
	
	public static final String DATAWindows = "D:\\uni17\\Proyecto_Recuperacion\\data";
	public static final String EmptyWindows = "D:\\uni17\\Proyecto_Recuperacion\\emptywords\\emptywords";
	public static final String RESWindows = "D:\\uni17\\Proyecto_Recuperacion\\results\\";
	public static final String CorpusWindows ="D:\\uni17\\Proyecto_Recuperacion\\corpus\\";


	public static void setData(int WL){
		if(WL == 0){
			DATA = DATALinux;
			Empty = EmptyLinux;
			RES = RESLinux;
			Corpus = CorpusLinux;
		}
		else{
			DATA = DATAWindows;
			Empty = EmptyWindows;
			RES = RESWindows;
			Corpus = CorpusWindows;
		}
	}
}
