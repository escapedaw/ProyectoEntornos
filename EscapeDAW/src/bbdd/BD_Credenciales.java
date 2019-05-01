package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
<<<<<<< HEAD

import modelos.Cliente;
import modelos.Credencial;

public class BD_Credenciales extends BD_Conector{
	private static Statement s;	
	private static ResultSet reg;
	
	public BD_Credenciales() {
		super();
	}

	public String conectarse(String usuario, String pass) {
		String tipo="";
		String cadenaSQL = "SELECT * from credenciales WHERE usuario='" + usuario + "' AND password='" + pass+"'";
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			if (reg.next()) {
				tipo= reg.getString("tipo");
			}
			s.close();
			this.cerrar();
			return tipo;
		} catch (SQLException e) {
			return null;
		}
	}
	
	public int a�adir_Usuario(Credencial cre) {
		String cadenaSQL = "INSERT INTO credenciales VALUES('" + cre.getUsuario() + "','" + cre.getPassword() + "','"
				+ cre.getRol() + "')";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		} catch (SQLException e) {
			return -1;
		}
	}
	
	public int eliminar_Usuario(String usuario) {
		String cadena = "DELETE FROM credenciales WHERE usuario='" + usuario + "'";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return filas;

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
	
	public int cambiar_clave(String nombre, String pass) {
		String cadena = "UPDATE credenciales SET password='" + pass + "' WHERE usuario='"+nombre+"'";

		try {
			this.abrir();
			s = c.createStatement();
			int filas = s.executeUpdate(cadena);
			s.close();
			this.cerrar();
			return filas;

		} catch (SQLException e) {
			this.cerrar();
			return -1;
		}
	}
=======
import modelos.Credencial;
>>>>>>> branch 'master' of https://github.com/escapedaw/ProyectoEntornos.git

public class BD_Credenciales extends BD_Conector{
	private static Statement s;	
	private static ResultSet reg;
	public BD_Credenciales(){
		super();
	}
	// TODO Auto-generated constructor stub
	
	/**
	 * a�adir credenciales
	 * @param cr
	 * @return
	 */
	public  int a�adirCredencial ( Credencial cr){	
		String cadenaSQL="INSERT INTO credenciales VALUES('" + cr.getUsuario() + "','" +
				cr.getPassword()+"','"+ cr.getRol() +"')"; 	
		try{
			this.abrir();
			s=c.createStatement();
			int filas=s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		}
		catch ( SQLException e){
			/*System.out.println("no se puede a�adir a la base de datos");*/
			return -1;
		}
	}

	

	

	/**
	 * modificar credencial password
	 * @param password
	 * @return
	 */
	public  int modificarCredencialPassword(String password){	
		String cadenaSQL="UPDATE credenciales SET PASSWORD='" + password +"')";

		try{
			this.abrir();
			s=c.createStatement();
			int filas=s.executeUpdate(cadenaSQL);
			s.close();
			this.cerrar();
			return filas;
		}
		catch (SQLException e){			
			return -1;
		}
	}


	
	/**
	 * eliminar credencial
	 * @param cr
	 * @return
	 */
	public int eliminarCredencial (Credencial cr){
		String cadena="DELETE FROM credenciales WHERE USUARIO='" + cr.getUsuario()+"')";	

		try{
			this.abrir();
			s=c.createStatement();
			int filas=s.executeUpdate(cadena);	
			s.close();
			this.cerrar();
			return filas;

		}
		catch ( SQLException e){
			this.cerrar();
			return -1;
		}
	}
}

