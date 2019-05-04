package bbdd;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import modelos.*;

public class BD_CliEmpJef extends BD_Conector{
	private static Statement s;	
	private static ResultSet reg;
	
	public BD_CliEmpJef() {
		super();
	}
	
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
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
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
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
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
