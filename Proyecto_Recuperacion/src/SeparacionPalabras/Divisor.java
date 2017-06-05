package SeparacionPalabras;
import java.util.ArrayList;
import java.util.Arrays;


public class Divisor {
	
	public ArrayList<String> execute(String s){
		return new ArrayList<>(Arrays.asList(s.split(" ")));
	}
}
