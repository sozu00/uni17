import java.util.regex.*;
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
    Pattern pat = Pattern.compile("([0-9]{1,3}[\\x2E][0-9]{1,3}){3}[0-9]{1,3}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean once(String cadena){
    Pattern pat = Pattern.compile("[\\x2B]34[ ][0-9]{2}[ ][0-9]{7}");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }

  public static boolean doce(String cadena){
    Pattern pat = Pattern.compile("");
    Matcher mat = pat.matcher(cadena);
    return mat.matches();
  }
}
