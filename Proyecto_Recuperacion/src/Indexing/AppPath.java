package Indexing;

public class AppPath {
	
	public static String DATA;
	public static String Empty;
	public static String RES;
	public static String Corpus;
	public static final String[] Linux = {"/home/sozu00/REC-INF/uni17/Proyecto_Recuperacion/data", 
			"/home/sozu00/REC-INF/uni17/Proyecto_Recuperacion/emptywords/emptywords",
			"/home/sozu00/REC-INF/uni17/Proyecto_Recuperacion/results/",
			"/home/sozu00/REC-INF/uni17/Proyecto_Recuperacion/corpus/"
			};
	public static final String[] Windows = {
			"D:\\uni17\\Proyecto_Recuperacion\\data",
			"D:\\uni17\\Proyecto_Recuperacion\\emptywords\\emptywords",
			"D:\\uni17\\Proyecto_Recuperacion\\results\\",
			"D:\\uni17\\Proyecto_Recuperacion\\corpus\\"
	};

	/*
	 * Param: int 0 for Linux, 1 for Windows
	 */
	public static void setData(int WL){
		switch(WL){
			case 0:
				DATA = Linux[0];
				Empty = Linux[1];
				RES = Linux[2];
				Corpus = Linux[3];
				break;
			case 1:
				DATA = Windows[0];
				Empty = Windows[1];
				RES = Windows[2];
				Corpus = Windows[3];
				break;
			default: break;
		}
	}
}
