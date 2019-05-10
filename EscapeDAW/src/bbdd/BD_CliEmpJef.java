package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import modelos.*;

/**
 * Clase que contiene todos los métodos que acceden a la tablas: "clientes", "empleados", "jefe" de la base de datos.
 * Se realizan las tareas de mostrar, crear, modificar y eliminar usuarios
 * 
 * @author Daniel González
 *
 */
public class BD_CliEmpJef extends BD_Conector{
	private static Statement s;	
	private static ResultSet reg;
	
	public BD_CliEmpJef() {
		super();
	}
	
	/**
	 * Clase que devuelve un vector con los clientes de la tabla "clientes" de la base de datos
	 * 
	 * @return vector con los clientes de la base de datos. Null en caso de fallo en la base de datos.
	 */
	public Vector<Cliente>  mostrarClientes(){
		Vector<Cliente> v=new Vector<Cliente>();
		String cadena="SELECT * FROM clientes ";
		try{
			
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){						
				 v.add(new Cliente(reg.getString("nif"),reg.getString("id"), reg.getString("id_emple"),reg.getString("nombre"),reg.getString("apellido"),reg.getString("direccion"),reg.getString("telefono")));
			}
			
			s.close();
			this.cerrar();
			return v;
		}
		catch ( SQLException e){
	
			return null;

		}
	}
	
	/**
	 * Método que se le pasa como parámetro un objeto de tipo cliente y lo añade en la tabla clientes de la base de datos
	 * 
	 * @param cli Objeto de tipo cliente
	 * @return Devuelve las filas insertadas: 1 en caso de insertarlo correctamente, 0 en caso de no insertar ninguna y -1 en caso de fallo.
	 */
	public int añadirCliente(Cliente cli) {
		String cadenaSQL = "INSERT INTO clientes VALUES('" + cli.getNif() + "','" + cli.getId() + "','" + cli.getId_emple() + "','"
				+ cli.getNombre() + "','" + cli.getApellido() + "','" + cli.getDireccion() + "','" + cli.getTelefono() + "')";

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
	 * Método que elimina un cliente de la base de datos
	 * 
	 * @param id id del cliente que queremos eliminar
	 * @return Devuelve las filas eliminadas: 1 en caso de eliminarlo correctamente, 0 en caso de no eliminar ninguna y -1 en caso de fallo.
	 */
	public int eliminarCliente(String id) {
		String cadena = "DELETE FROM clientes WHERE ID='" + id + "'";

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
	 * Método que actualiza el teléfono de un cliente de la base de datos
	 * 
	 * @param id id del cliente que se desea modificar
	 * @param telefono nuevo teléfono que queremos actualizar
	 * @return Devuelve las filas eliminadas: 1 en caso de modificarlo correctamente, 0 en caso de no modificar ninguna y -1 en caso de fallo.
	 */
	public int actualizar_Cliente(String id, String telefono) {
		String cadena = "UPDATE clientes SET TELEFONO='" + telefono + "' WHERE ID='"+id+"'";

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
	 * Clase que devuelve un vector con los clientes de la tabla "empleados" de la base de datos
	 * 
	 * @return vector con los empleados de la base de datos. Null en caso de fallo en la base de datos.
	 */
	public Vector<Empleado>  mostrarEmpleados(){
		Vector<Empleado> v=new Vector<Empleado>();
		String cadena="SELECT * FROM empleados ";
		try{
			
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){						
				 v.add(new Empleado(reg.getString("nif"),reg.getString("id"),reg.getString("nombre"),reg.getString("apellido"),reg.getString("telefono"),reg.getString("direccion"),reg.getInt("sueldo"),reg.getString("id_jefe")));
			}
			
			s.close();
			this.cerrar();
			return v;
		}
		catch ( SQLException e){
			System.out.println(e.getMessage());
			return null;

		}
	}
	
	/**
	 * Método que nos devuelve un vector de string con los id de todos los empleados de la base de datos
	 * 
	 * @return Vector con elementos de tipo String con todos los ids. Null en caso de fallo
	 */
	public Vector<String>  mostrarIdEmpleados(){
		Vector<String> v=new Vector<String>();
		String cadena="SELECT ID FROM empleados ";
		try{
			
			this.abrir();
			s=c.createStatement();
			reg=s.executeQuery(cadena);
			while ( reg.next()){						
				 v.add(reg.getString(1));
			}
			
			s.close();
			this.cerrar();
			return v;
		}
		catch ( SQLException e){
			System.out.println(e.getMessage());
			return null;

		}
	}
	
	/**
	 * Método que se le pasa como parámetro un objeto de tipo empleado y lo añade en la tabla empleados de la base de datos
	 * 
	 * @param emp Objeto de tipo empleado
	 * @return Devuelve las filas insertadas: 1 en caso de insertarlo correctamente, 0 en caso de no insertar ninguna fila y -1 en caso de fallo.
	 */
	public int añadirEmpleado(Empleado emp) {
		String cadenaSQL = "INSERT INTO empleados VALUES('" + emp.getNif() + "','" + emp.getId() + "','"
				+ emp.getNombre() + "','" + emp.getApellido() + "','" + emp.getTelefono() + "','" + emp.getDireccion() + "','" + emp.getSueldo() + "','" + emp.getId_jefe() + "')";

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
	 * Método que modifica el salario de un empleado
	 * 
	 * @param id id del empleado a modificar
	 * @param sueldo sueldo nuevo
	 * @return Devuelve el número de filas modificadas: 1 en caso de modificarlo correctamente, 0 en caso de no modificar ninguna fila y -1 en caso de fallo.
	 */
	public int actualizar_Empleado(String id, int sueldo) {
		String cadena = "UPDATE empleados SET SUELDO='" + sueldo + "' WHERE ID='"+id+"'";

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
	 * Método que elimina un empleado de la tabla empleados de la base de datos
	 * 
	 * @param id id del empleado que se quiere borrar
	 * @return Devuelve el número de filas eliminadas: 1 en caso de eliminarla correctamente, 0 si no elimina ninguno y -1 en caso de fallo.
	 */
	public int eliminarEmpleado(String id) {
		String cadena = "DELETE FROM empleados WHERE ID='" + id + "'";

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
