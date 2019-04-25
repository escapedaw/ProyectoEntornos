package modelos;
/**
 * 
 * @author Sandra
 *
 */
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
	
	public String getIdEmple() {
		return idEmple;
	}
	
	public String getIdJefe() {
		return idJefe;
	}
	
	public String getTipo() {
		return tipo;
	}

	public String getDificultad() {
		return dificultad;
	}
	
	public int getNPersonas() {
		return nPersonas;
	}

	public int getPrecio() {
		return precio;
	}

	@Override
	public String toString() {
		return "Sala [nSala=" + nSala + ", idEmple=" + idEmple + ", idJefe=" + idJefe + ", tipo=" + tipo
				+ ", dificultad=" + dificultad + ", nPersonas=" + nPersonas + ", precio=" + precio + "]";
	}
	
	

}
