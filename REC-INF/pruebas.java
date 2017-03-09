public class pruebas {
  public static void main(String[] args) throws Exception{

    String cadena = "uca.html";
    String texto = Leer.readFile(cadena);
    /*if (practica1.dieciseis(cadena)) System.out.println("VERDADERO");
    else System.out.println("FALSO");*/
    System.out.println(practica1.veinte(texto));
  }
}
