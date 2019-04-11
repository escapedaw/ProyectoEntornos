package modelos;

public class Sala {
	
	
	private String nsala;
	private String id_emple;
	private String id_jefe;
	private String tipo;
	private String dificultad;
	private int npersonas;
	private int precio;
	
	public Sala(String nsala, String id_emple, String id_jefe, String tipo, String dificultad, int npersonas,
			int precio) {
		super();
		this.nsala = nsala;
		this.id_emple = id_emple;
		this.id_jefe = id_jefe;
		this.tipo = tipo;
		this.dificultad = dificultad;
		this.npersonas = npersonas;
		this.precio = precio;
	}
	
	public String getNsala() {
		return nsala;
	}
	public void setNsala(String nsala) {
		this.nsala = nsala;
	}
	public String getId_emple() {
		return id_emple;
	}
	public void setId_emple(String id_emple) {
		this.id_emple = id_emple;
	}
	public String getId_jefe() {
		return id_jefe;
	}
	public void setId_jefe(String id_jefe) {
		this.id_jefe = id_jefe;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public int getNpersonas() {
		return npersonas;
	}
	public void setNpersonas(int npersonas) {
		this.npersonas = npersonas;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
	
	
	

}
