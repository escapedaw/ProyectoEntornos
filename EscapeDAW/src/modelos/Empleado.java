package modelos;

public class Empleado {
	private String nif;
	private String id;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	private int sueldo;
	private String id_jefe;
	
	public Empleado(String nif, String id, String nombre, String apellido, String telefono, String direccion,
			int sueldo, String id_jefe) {
		super();
		this.nif = nif;
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
		this.sueldo = sueldo;
		this.id_jefe = id_jefe;
	}

	public String getNif() {
		return nif;
	}

	public String getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public int getSueldo() {
		return sueldo;
	}

	public String getId_jefe() {
		return id_jefe;
	}

	@Override
	public String toString() {
		return "Empleado [nif=" + nif + ", id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", telefono="
				+ telefono + ", direccion=" + direccion + ", sueldo=" + sueldo + ", id_jefe=" + id_jefe + "]";
	}
	
	

}
