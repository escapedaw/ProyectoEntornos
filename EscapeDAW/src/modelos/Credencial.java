package modelos;

public class Credencial {

	private String usuario;
	private String password;
	private char rol;
	
	public Credencial(String usuario, String password, char rol) {
		super();
		this.usuario = usuario;
		this.password = password;
		this.rol = rol;
	}
	
	public String getUsuario() {
		return usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public char getRol() {
		return rol;
	}
	
}
