package SeparacionPalabras;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;


public class Divisor {
	
	public ArrayList<String> execute(String s){
		return new ArrayList<String>(Arrays.asList(s.split(" ")));
	}
}
