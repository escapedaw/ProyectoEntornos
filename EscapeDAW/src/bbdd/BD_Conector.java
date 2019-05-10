package bbdd;

import java.sql.*;


/**
 * Clase que conecta nuestra aplicación de java con la base de datos de mysql. Se encarga de abrir y cerrar la base de datos
 * 
 * @author Sandra Lobón
 *
 */
public class BD_Conector {
	static private String base;
	static private String usuario;
	static private String pass;
	static private String url;
	static protected Connection c;
	
	public static void BD_Ini(String bbdd){		
		base=bbdd;
		usuario="root";
		pass="";
		url="jdbc:mysql://localhost/"+base;
	}
	
	/**
	 * Método que abre la base de datos
	 */
	public void abrir(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		try{
		 	c=DriverManager.getConnection(url,usuario,pass);
		}
		catch (SQLException e ){
			System.out.println(e.getMessage());
		}
	
	}	
	
	/**
	 * Método que cierra la base de datos
	 */
	public void cerrar(){
		try{
			c.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
			
		}
	}
	
	/**
	 * Método que nos devuelve el último número del identificador de cada tabla. 
	 * 
	 * @param codigo Nombre de la columna
	 * @param tabla Nombre de la tabla de la base de datos
	 * @param cod Dos letras que identifican a cada codigo. Por ejemplo: "PI" en pistas
	 * @return Devuelve el último número del identificador de la tabla
	 */
	public int consultaNumeroSecuencial(String codigo, String tabla, String cod) {
		Statement s;	
		ResultSet reg;
		String cadenaSQL = "SELECT MAX(SUBSTRING("+ codigo+",3)) FROM "+ tabla +" WHERE SUBSTRING(" + codigo +  ",1,2) = '"+ cod +"'";
		int filas = 0;
		
		try {
			this.abrir();
			
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			if(reg.next()){
				filas = reg.getInt(1);
			}
			
			return filas;
		}catch(SQLException e) {
			this.cerrar();
			return -1;
		}
	} 
	
}
