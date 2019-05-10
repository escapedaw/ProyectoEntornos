package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Cliente;
import modelos.Credencial;

/**
 * Clase que contiene todos los métodos que acceden a la tabla "credenciales" de la base de datos.
 * Se realizan las tareas de crear, modificar y eliminar credenciales
 * 
 * @author Daniel González
 *
 */
public class BD_Credenciales extends BD_Conector{
	private static Statement s;	 
	private static ResultSet reg;
	
	public BD_Credenciales() {
		super();
	}

	/**
	 * Método utilizado para conectarse/logearse. Se le pasa como parámetro el usuario y contraseña introducida por teclado en el main y devuelve el rol
	 * 
	 * @param usuario Usuario
	 * @param pass Contraseña
	 * @return Devuelve el rol al que pertenece. En caso de no encontrar el credencial, devuelve una cadena vacía. En caso de error devolve null
	 */
	public String conectarse(String usuario, String pass) {
		String rol="";
		String cadenaSQL = "SELECT * from credenciales WHERE USUARIO='" + usuario + "' AND PASSWORD=MD5('" + pass+"')";
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			if (reg.next()) {
				rol= reg.getString("rol");
			}
			s.close();
			this.cerrar();
			return rol;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Método que nos devuelve el id del usuario pasándole como parámetro el dni y la tabla en la que está el usuario.
	 * 
	 * @param dni DNI del usuario introducido por teclado
	 * @param tabla Tabla de nuestra base de datos en la que buscar el id por el dni introducido
	 * @return Devuelte el id del usuario
	 */
	public String buscarId(String dni, String tabla) {
		String id="";
		String cadenaSQL = "SELECT ID from "+tabla+" WHERE NIF='" + dni + "'";
		try {
			this.abrir();
			s = c.createStatement();
			reg = s.executeQuery(cadenaSQL);
			if (reg.next()) {
				id= reg.getString(1);
			}
			s.close();
			this.cerrar();
			return id;
		} catch (SQLException e) {
			return null;
		}
	}
	
	/**
	 * Método que da de alta los credenciales de un usuario. La contraseña será encriptada en MD5
	 * 
	 * @param cre Objeto de tipo "Credencial" con los datos necesarios para poder dar de alta los credenciales de un usuario
	 * @return Devuelve 1 en caso de realizarse bien la operación. 0 si no ha hecho nada. -1 en caso de error en la base de datos.
	 */
	public int anadir_Usuario(Credencial cre) {
		String cadenaSQL = "INSERT INTO credenciales VALUES('" + cre.getUsuario() + "',MD5('" + cre.getPassword() + "'),'"
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
	
	/**
	 * Método que elimina de la tabla "Credenciales" los credenciales de un usuario. Recibe como parámetro el nombre de usuario
	 * 
	 * @param usuario Nombre de usuario
	 * @return Devuelve 1 en caso de realizarse bien la operación. 0 si no ha hecho nada. -1 en caso de error en la base de datos.
	 */
	public int eliminar_Usuario(String usuario) {
		String cadena = "DELETE FROM credenciales WHERE USUARIO='" + usuario + "'";

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
	
	/**
	 * Método para cambiar la clave de un usuario. Será encriptada en MD5
	 * 
	 * @param nombre Nombre del usuario que se desea cambiar la clave
	 * @param pass Clave nueva
	 * @return Devuelve 1 en caso de realizarse bien la operación. 0 si no ha hecho nada. -1 en caso de error en la base de datos.
	 */
	public int cambiar_clave(String nombre, String pass) {
		String cadena = "UPDATE credenciales SET PASSWORD=MD5('" + pass + "') WHERE USUARIO='"+nombre+"'";

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
}