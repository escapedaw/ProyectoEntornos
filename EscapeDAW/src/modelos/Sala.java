package modelos;

public class Sala {
	
	
	private String nSala;
	private String idEmple;
	private String idJefe;
	private String tipo;
	private String dificultad;
	private int nPersonas;
	private int precio;
	private static int numSalas;
	
	public Sala(String idEmple, String idJefe, String tipo, String dificultad, int nPersonas,
			int precio) {
		super();
		numSalas++;
		this.nSala = "S" + numSalas;
		this.idEmple = idEmple;
		this.idJefe = idJefe;
		this.tipo = tipo;
		this.dificultad = dificultad;
		this.nPersonas = nPersonas;
		this.precio = precio;
	}
	
	public Sala(String nSala, String idEmple, String idJefe, String tipo, String dificultad, int nPersonas,
			int precio) {
		super();
		this.nSala = nSala;
		this.idEmple = idEmple;
		this.idJefe = idJefe;
		this.tipo = tipo;
		this.dificultad = dificultad;
		this.nPersonas = nPersonas;
		this.precio = precio;
	}



	public String getNsala() {
		return nSala;
	}
	
	public void setNsala(String nSala) {
		this.nSala = nSala;
	}
	
	public String getIdEmple() {
		return idEmple;
	}
	public void setIdEmple(String idEmple) {
		this.idEmple = idEmple;
	}
	
	public String getIdJefe() {
		return idJefe;
	}
	
	public void setIdJefe(String idJefe) {
		this.idJefe = idJefe;
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
	
	public int getNPersonas() {
		return nPersonas;
	}
	
	public void setNPersonas(int nPersonas) {
		this.nPersonas = nPersonas;
	}
	
	public int getPrecio() {
		return precio;
	}
	
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	

}
