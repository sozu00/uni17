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
    String [] letras = {"á","â","à","é","ê","è","í","î","ì","ó","ô","ò","ú","û","ù","ñ"};
    Pattern p;
    Matcher m;
    String opcion;
    for(String c : letras){
      switch(c){
        case "á": case "â": case "à": opcion = "a"; break;
        case "é": case "ê": case "è": opcion = "e"; break;
        case "í": case "î": case "ì": opcion = "i"; break;
        case "ó": case "ô": case "ò": opcion = "o"; break;
        case "ú": case "û": case "ù": opcion = "u"; break;
        case "ñ": opcion = "n"; break;
        default: opcion = ""; break;
      }
      if(opcion != ""){
        p = Pattern.compile(c);
        m = p.matcher(t);
        t = m.replaceAll(opcion);
      }
    }
    return t;
  }

  public static String dieciocho(String cadena) throws Exception{
    String t = diecisiete(cadena);
    Pattern pat = Pattern.compile(" [0-9]+ ");
    Matcher mat = pat.matcher(t);
    return mat.replaceAll(" ");
  }

  public static String diecinueve(String cadena) throws Exception{
    String t = dieciocho(cadena);
    Pattern p = Pattern.compile("[a-z]");
    Matcher m = p.matcher(t);
    StringBuffer resultado = new StringBuffer(); //El append solo funciona con stringbuffer

    //Para TODAS LAS LETRAS
    while(m.find()){
      m.appendReplacement(resultado, m.group().toUpperCase()); //Añade al buffer el texto en caps
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
