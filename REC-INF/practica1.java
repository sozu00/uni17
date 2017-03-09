import java.util.regex.*;
import java.io.*;
import java.util.*;

public class practica1{
  public static boolean uno(String cadena){
    Pattern pat = Pattern.compile("abc.*");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean dos(String cadena){
    Pattern pat = Pattern.compile("abc.*|Abc.*");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean tres(String cadena){
    Pattern pat = Pattern.compile("\\D.*");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean cuatro(String cadena){
    Pattern pat = Pattern.compile(".*\\D");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean cinco(String cadena){
    Pattern pat = Pattern.compile("[al]");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean seis(String cadena){
    Pattern pat = Pattern.compile(".*2[^6].*");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean siete(String cadena){
    Pattern pat = Pattern.compile("[a-zA-z]{5,10}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean ocho(String cadena){
    Pattern pat = Pattern.compile("www\\..*\\.es");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean nueve(String cadena){
    Pattern pat = Pattern.compile("[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,2}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean diez2(String cadena){
    Pattern pat = Pattern.compile("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean diez(String cadena){
    Pattern pat = Pattern.compile("([0-9]{1,3}\\.){3}[0-9]{1,3}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean once(String cadena){
    Pattern pat = Pattern.compile("\\+34 [0-9]{2} [0-9]{7}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean doce(String cadena){
    Pattern pat = Pattern.compile("P[ \\#\\-(\\# )]\\d\\d[\\d\\- ]\\d{4,5}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean trece(String cadena){
    Pattern pat = Pattern.compile("v[i1\\!][a\\@]gr[a\\@]");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static int catorce(String cadena) throws Exception{
    Pattern pat = Pattern.compile("\\<img");
    Matcher mat = pat.matcher(cadena);
    int n=0;
    while(mat.find())n++;
    return n;
  }

  public static void quince(String cadena){
    String s = "<a>uno</a><b>dos</b><c>tres</c><d>cuatro</d><e>cinco</e>";
    Pattern pat = Pattern.compile("<.{1,2}>");

    Pattern pat2 = Pattern.compile("<.*>(.*)<\\/.*>");
    Pattern pat3 = Pattern.compile("<.*?>(.*?)<\\/.*?>");
    String [] num = pat.split(s);
    for(String x : num)  System.out.print(x);
  }

  public static String dieciseis(String cadena) throws Exception{
    Pattern pat = Pattern.compile("\\p{Punct}");
    String [] num = pat.split(cadena);
    String resultado = "";
    for(String x : num)  resultado = resultado.concat(x);
    return resultado;
  }

  public static String diecisiete(String cadena) throws Exception{
    String t = dieciseis(cadena);
    //Sustituyo el codigo ascii de mierda que me imprime por la letra que corresponde
    Pattern patA = Pattern.compile("[áâà]");
    Matcher matA = patA.matcher(t);
    String resultado = matA.replaceAll("a");

    Pattern patE = Pattern.compile("[éêè]");
    Matcher matE = patE.matcher(resultado);
    resultado = matE.replaceAll("e");

    Pattern patI = Pattern.compile("[íîì]");
    Matcher matI = patI.matcher(resultado);
    resultado = matI.replaceAll("i");

    Pattern patO = Pattern.compile("[óôò]");
    Matcher matO = patO.matcher(resultado);
    resultado = matO.replaceAll("o");

    Pattern patU = Pattern.compile("[úûù]");
    Matcher matU = patU.matcher(resultado);
    resultado = matU.replaceAll("u");

    Pattern patN = Pattern.compile("[ñ]");
    Matcher matN = patN.matcher(resultado);
    resultado = matN.replaceAll("n");

    return resultado;
  }

  public static String dieciocho(String cadena) throws Exception{
    String t = diecisiete(cadena);
    Pattern pat = Pattern.compile(" [0-9]+ ");
    Matcher mat = pat.matcher(t);
    return mat.replaceAll(" ");
  }

  public static String diecinueve(String cadena) throws Exception{
    String t = dieciocho(cadena);
    Pattern patA = Pattern.compile("[\\p{Alpha}]");
    Matcher mat = patA.matcher(t);
    StringBuffer resultado = new StringBuffer(); //El append sol funciona con stringbuffer

    //Para TODAS LAS LETRAS
    while(mat.find()){
      mat.appendReplacement(resultado, mat.group().toUpperCase()); //Añade al buffer el texto en caps
    }

    return resultado.toString();
  }

  public static String veinte(String cadena) throws Exception{
    String t = diecinueve(cadena);
    Pattern pat = Pattern.compile(" \\s");
    Matcher mat = pat.matcher(t);
    return mat.replaceAll(" ");
  }

}
