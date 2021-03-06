import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class main {
	public static Connection conn;
	public static void main(String[] args) throws SQLException{
		long time_start, tiempoConexion, tiempoInsercion, tiempoLimpieza, tiempoDesconexion;
		time_start = System.currentTimeMillis();
		conectar("jdbc:mysql://"+args[0], args[1], args[2]);
		tiempoConexion = System.currentTimeMillis() - time_start;
		
//		for(int i = 1; i < 351; i+=10) testinsercion(i);

		time_start = System.currentTimeMillis();
		desconectar();
		tiempoDesconexion= System.currentTimeMillis() - time_start;
		
		System.out.println("Tiempo de Conexion: "+tiempoConexion+
        			"ms\nTiempo de Desconexion: "+tiempoDesconexion+"ms");
	}
	public static void testinsercion(int n) throws SQLException{
		long time_start, tiempoInsercion, tiempoLimpieza;
		time_start = System.currentTimeMillis();
		limpiar();
		tiempoLimpieza= System.currentTimeMillis() - time_start;
		
		time_start = System.currentTimeMillis();
		insertar(n);
		tiempoInsercion = System.currentTimeMillis() - time_start;
		
		System.out.println(n+"\t"+tiempoInsercion);
	}
	
	
	public static void conectar(String url, String user, String pass)throws SQLException{
		conn = DriverManager.getConnection(url,user,pass);
        if (conn != null) {
            System.out.println("Connected to the database banco");
        }
	}
	
	public static void desconectar()throws SQLException{
		conn.close();
	}
	
	public static void insertar(int n)throws SQLException{
    	String query = " insert into cuenta (nCuenta, nombre, apellidos, saldo)"
                + " values (?, ?, ?, ?)";
    	PreparedStatement preparedStmt = conn.prepareStatement(query);
    	for(int i = 0; i<n; i++){
	        preparedStmt.setString (1, "12345678901234567"+Integer.toString(i));
	        preparedStmt.setString (2, "Jesus"+Integer.toString(i));
	      	preparedStmt.setString (3, "Iniguez");
	      	preparedStmt.setDouble (4, Math.round((Math.random()*100000)*100d)/100d);
	      	preparedStmt.execute();
    	}
    }
    
    public static void transferir(int n)throws SQLException{
    	String query = "{call transaccion(?,?,?,?)}";
    	CallableStatement preparedStmt = conn.prepareCall(query);
    	
    	for(int i = 0; i<n; i++){
	        preparedStmt.setString (1, "12345678901234567"+Integer.toString(i));
	        preparedStmt.setString (2, "12345678901234567"+Integer.toString(i+1));
	      	preparedStmt.setDouble (3, Math.round((Math.random()*1000)*100d)/100d);
	      	preparedStmt.setString (4, "prueba"+Integer.toString(i));
	      	preparedStmt.execute();
    	}
    }
    
    public static void mostrar(int n) throws SQLException{
    Statement st = conn.createStatement();
    String query;
	
	    for(int i=0; i<n; i++){
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

    public static void limpiar() throws SQLException{
    	conn.createStatement().executeUpdate("DELETE FROM operacion");
    	conn.createStatement().executeUpdate("DELETE FROM cuenta");
    }
}
