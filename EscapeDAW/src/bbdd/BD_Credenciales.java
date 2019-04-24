package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelos.Credencial;

public class BD_Credenciales extends BD_Conector{
	private static Statement s;	
	private static ResultSet reg;
	public BD_Credenciales(){
		super();
	}
	// TODO Auto-generated constructor stub
	
	/**
	 * añadir credenciales
	 * @param cr
	 * @return
	 */
	public  int añadirCredencial ( Credencial cr){	
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
			/*System.out.println("no se puede añadir a la base de datos");*/
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

