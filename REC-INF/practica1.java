import java.util.regex.*;
import java.io.*;
import java.util.*;
import java.text.Normalizer;

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
    Pattern pat = Pattern.compile("www[\\x2E].*[\\x2E]es");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean nueve(String cadena){
    Pattern pat = Pattern.compile("[0-9]{1,2}[\\x2F][0-9]{1,2}[\\x2F][0-9]{1,2}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean diez(String cadena){
    Pattern pat = Pattern.compile("[0-9]{1,3}[\\x2E][0-9]{1,3}[\\x2E][0-9]{1,3}[\\x2E][0-9]{1,3}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean diez2(String cadena){
    Pattern pat = Pattern.compile("([0-9]{1,3}[\\x2E]){3}[0-9]{1,3}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean once(String cadena){
    Pattern pat = Pattern.compile("[\\x2B]34[ ][0-9]{2}[ ][0-9]{7}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean doce(String cadena){
    Pattern pat = Pattern.compile("P[ \\x23\\x2D]([ 0-9\\x2D]){5,8}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean trece(String cadena){
    Pattern pat = Pattern.compile("v[i1\\x21][a\\x40]gr[a\\x40]");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean catorce(String cadena) throws Exception{
    String t = Leer.readFile(cadena);
    Pattern pat = Pattern.compile(".*[\\x3C]img.*[\\x3E].*");
    Matcher mat = pat.matcher(t);
    return mat.matches();
  }

  public static boolean quince(String cadena){
    String s = "<a>uno</a><b>dos</b><c>tres</c><d>cuatro</d><e>cinco</e>";
    Pattern pat = Pattern.compile("[\\x3C][^[\\x3E]]*[\\x3E]([^[\\x3C]]*)[\\x3C][\\x2F][^[\\x3E]]*[\\x3E]");
    //Hay una incongruencia en cada </ porque es y no es <.
    Pattern pat2 = Pattern.compile("[\\x3C].*[\\x3E](.*)[\\x3C].*[\\x3E]");
    Pattern pat3 = Pattern.compile("[\\x3C].*?[\\x3E](.*?)[\\x3C].*?[\\x3E]");
    String [] num = pat3.split(s);
    for(String x : num)  System.out.print(x);
    Matcher mat = pat.matcher(s);
    return mat.matches();
  }

  public static String dieciseis(String cadena) throws Exception{
    String t = Leer.readFile(cadena);
    Pattern pat = Pattern.compile("\\p{Punct}");
    Matcher mat = pat.matcher(cadena);
    String [] num = pat.split(t);
    String resultado = "";
    for(String x : num)  resultado = resultado.concat(x);
    return resultado;
  }

  public static String diecisiete(String cadena) throws Exception{
    String t = dieciseis(cadena);
    //Sustituyo el codigo ascii de mierda que me imprime por la letra que corresponde
    Pattern patA = Pattern.compile("[\\u00A1]");
    Matcher matA = patA.matcher(t);
    String resultado = matA.replaceAll("a");

    Pattern patE = Pattern.compile("[\\u00A9]");
    Matcher matE = patE.matcher(resultado);
    resultado = matE.replaceAll("e");

    Pattern patI = Pattern.compile("[\\u00aD]");
    Matcher matI = patI.matcher(resultado);
    resultado = matI.replaceAll("i");

    Pattern patO = Pattern.compile("[\\u00B3]");
    Matcher matO = patO.matcher(resultado);
    resultado = matO.replaceAll("o");

    Pattern patU = Pattern.compile("[\\u00BA]");
    Matcher matU = patU.matcher(resultado);
    resultado = matU.replaceAll("u");

    Pattern patN = Pattern.compile("[\\u00B1]");
    Matcher matN = patN.matcher(resultado);
    resultado = matN.replaceAll("n");


    //Borro toda la basura sobrante (tildes raras)
    Pattern patG = Pattern.compile("[^\\p{ASCII}]");
    Matcher matG = patG.matcher(resultado);
    resultado = matG.replaceAll("");

    return resultado;
  }

  public static String dieciocho(String cadena) throws Exception{
    String t = diecisiete(cadena);
    Pattern pat = Pattern.compile(" [0-9]+ ");
    Matcher mat = pat.matcher(t);
    String resultado = mat.replaceAll(" ");
    return resultado;
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
    Pattern patA = Pattern.compile("  ");
    Matcher mat = patA.matcher(t);
    String resultado = mat.replaceAll(" ");
    return resultado;
  }

}
