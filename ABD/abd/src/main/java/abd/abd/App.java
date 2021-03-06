package abd.abd;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class App {
	
	public static Connection conn;
    public static void main(String[] args){
 
        // creates three different Connection objects
        conn = null;
 
        try {
            // connect way #1
            String url = "jdbc:mysql://localhost:3306/banco";
            String user = "root";
            String password = "jig30592";
 
            
            
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Connected to the database banco");
            }
            
            //insertar();
          	transferir();
          	mostrar();
          	conn.close();
          	
        } catch (SQLException ex) {
            System.out.println("Error ocurrido");
            ex.printStackTrace();
        }
    }
    
    public static void insertar()throws SQLException{
    	String query = " insert into cuenta (nCuenta, nombre, apellidos, saldo)"
                + " values (?, ?, ?, ?)";
    	PreparedStatement preparedStmt = conn.prepareStatement(query);
    	for(int i = 0; i<100; i++){
	        preparedStmt.setString (1, "12345678901234567"+Integer.toString(i));
	        preparedStmt.setString (2, "Jesus"+Integer.toString(i));
	      	preparedStmt.setString (3, "Iniguez");
	      	preparedStmt.setDouble (4, Math.round((Math.random()*100000)*100d)/100d);
	      	preparedStmt.execute();
    	}
    }
    
    public static void transferir()throws SQLException{
    	String query = "{call transaccion(?,?,?,?)}";
    	CallableStatement preparedStmt = conn.prepareCall(query);
    	
    	for(int i = 0; i<99; i++){
	        preparedStmt.setString (1, "12345678901234567"+Integer.toString(i));
	        preparedStmt.setString (2, "12345678901234567"+Integer.toString(i+1));
	      	preparedStmt.setDouble (3, Math.round((Math.random()*1000)*100d)/100d);
	      	preparedStmt.setString (4, "prueba"+Integer.toString(i));
	      	preparedStmt.execute();
    	}
    }
    
    public static void mostrar() throws SQLException{
    Statement st = conn.createStatement();
    String query;
	
    for(int i=0; i<100; i++){
		query = "SELECT * FROM cuenta WHERE nCuenta=12345678901234567"+Integer.toString(i);
	    ResultSet rs = st.executeQuery(query);
	    while (rs.next())
	    {
	      String nCuenta = rs.getString("nCuenta");
	      String nombre = rs.getString("nombre");
	      String apellidos = rs.getString("apellidos");
	      double saldo = rs.getDouble("saldo");
	      
	      // print the results
	      System.out.format("%s, %s, %s, %f\n", nCuenta, nombre, apellidos, saldo);
	    }
    }
    }
}
