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
	
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public char getRol() {
		return rol;
	}
	
	public void setRol(char rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Credencial [usuario=" + usuario + ", password=" + password + ", rol=" + rol + "]";
	}
	
	
	
	
	
}
