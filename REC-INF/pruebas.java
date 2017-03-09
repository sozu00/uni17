public class pruebas {
  public static void main(String[] args) throws Exception{

    String cadena = "EjercicioExpresiones.txt";
    String texto = Leer.readFile(cadena);
    //if (practica1.doce(cadena)) System.out.println("Cumple Patrón");
    //else System.out.println("No cumple el patrón");
    System.out.println(practica1.veinte(texto));
  }
}
