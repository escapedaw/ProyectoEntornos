package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public int anadir_Usuario(Credencial cre) {
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
}