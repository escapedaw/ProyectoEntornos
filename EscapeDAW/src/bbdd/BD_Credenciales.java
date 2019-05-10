package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelos.Cliente;
import modelos.Credencial;

/**
 * Clase que contiene todos los m�todos que acceden a la tabla "credenciales" de la base de datos.
 * Se realizan las tareas de crear, modificar y eliminar credenciales
 * 
 * @author Daniel Gonz�lez
 *
 */
public class BD_Credenciales extends BD_Conector{
	private static Statement s;	 
	private static ResultSet reg;
	
	public BD_Credenciales() {
		super();
	}

	/**
	 * M�todo utilizado para conectarse/logearse. Se le pasa como par�metro el usuario y contrase�a introducida por teclado en el main y devuelve el rol
	 * 
	 * @param usuario Usuario
	 * @param pass Contrase�a
	 * @return Devuelve el rol al que pertenece. En caso de no encontrar el credencial, devuelve una cadena vac�a. En caso de error devolve null
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
	 * M�todo que nos devuelve el id del usuario pas�ndole como par�metro el dni y la tabla en la que est� el usuario.
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
	 * M�todo que da de alta los credenciales de un usuario. La contrase�a ser� encriptada en MD5
	 * 
	 * @param cre Objeto de tipo "Credencial" con los datos necesarios para poder dar de alta los credenciales de un usuario
	 * @return Devuelve 1 en caso de realizarse bien la operaci�n. 0 si no ha hecho nada. -1 en caso de error en la base de datos.
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
	 * M�todo que elimina de la tabla "Credenciales" los credenciales de un usuario. Recibe como par�metro el nombre de usuario
	 * 
	 * @param usuario Nombre de usuario
	 * @return Devuelve 1 en caso de realizarse bien la operaci�n. 0 si no ha hecho nada. -1 en caso de error en la base de datos.
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
	 * M�todo para cambiar la clave de un usuario. Ser� encriptada en MD5
	 * 
	 * @param nombre Nombre del usuario que se desea cambiar la clave
	 * @param pass Clave nueva
	 * @return Devuelve 1 en caso de realizarse bien la operaci�n. 0 si no ha hecho nada. -1 en caso de error en la base de datos.
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