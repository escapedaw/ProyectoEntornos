package modelos;


public class Cliente {
	private String nif;
	private String id_emple;
	private String nombre;
	private String apellido;
	private String direccion;
	private String telefono;

	public Cliente(String nif, String id_emple, String nombre, String apellido, String direccion, String telefono) {
		super();
		this.nif = nif;
		this.id_emple = id_emple;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getId_emple() {
		return id_emple;
	}
	public void setId_emple(String id_emple) {
		this.id_emple = id_emple;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	
	
	
	
}
