package modelos;


public class Cliente {
	private String nif;
	private String id;
	private String id_emple;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;


	public Cliente(String nif, String id, String id_emple, String nombre, String apellido, String direccion,
			String telefono) {
		super();
		this.nif = nif;
		this.id = id;
		this.id_emple = id_emple;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
	}


	public String getNif() {
		return nif;
	}


	public String getId() {
		return id;
	}


	public String getId_emple() {
		return id_emple;
	}


	public String getNombre() {
		return nombre;
	}


	public String getApellido() {
		return apellido;
	}


	public String getDireccion() {
		return direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	@Override
	public String toString() {
		return "Cliente [nif=" + nif + ", id=" + id + ", id_emple=" + id_emple + ", nombre=" + nombre + ", apellido="
				+ apellido + ", direccion=" + direccion + ", telefono=" + telefono + "]";
	}
	
	
	
	
	
	
	
	
}
