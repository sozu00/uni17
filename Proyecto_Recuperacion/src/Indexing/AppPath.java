package Indexing;

import java.io.File;

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
	public static final String[] Relativo = {
			"data",
			"emptywords"+File.separator+"emptywords",
			"results"+File.separator,
			"corpus"
	};

	/*
	 * Param: int 0 for Linux, 1 for Windows
	 */
	public static void setData(int WL){
		switch(WL){
			case 1:
				DATA = Relativo[0];
				Empty = Relativo[1];
				RES = Relativo[2];
				Corpus = Relativo[3];
				break;
			case 2:
				DATA = Windows[0];
				Empty = Windows[1];
				RES = Windows[2];
				Corpus = Windows[3];
				break;
			default: break;
		}
	}
}
