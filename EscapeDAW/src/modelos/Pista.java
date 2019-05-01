package modelos;

import bbdd.BD_Conector;

/**
 * 
 * @author Sandra
 *
 */
public class Pista {

	private String cod_pista;
	private String nsala;
	private String descripcion;
	private boolean solicitado;
	private boolean confirmado;
	private static int numPistas;
	
	/**
	 * Constructor para crear objeto Pista
	 * @param nsala
	 * @param descripcion
	 */
	
	public Pista(String cod_pista, String nsala, String descripcion, boolean solicitado, boolean confirmado, String codigo, String tabla, String cod) {
		super();
		BD_Conector bc= new BD_Conector ();
		numPistas=bc.consultaNumeroSecuencial(codigo, tabla, cod);
		this.cod_pista = "PI" + numPistas;
		this.nsala = nsala;
		this.descripcion = descripcion;
	}
	
	public Pista(String cod_pista, String nsala, String descripcion, boolean solicitado, boolean confirmado) {
		super();
		this.cod_pista = cod_pista;
		this.nsala = nsala;
		this.descripcion = descripcion;
		this.solicitado = solicitado;
		this.confirmado = confirmado;
	}
	
	

	public Pista(String cod_pista, String nsala, String descripcion) {
		super();
		this.cod_pista = cod_pista;
		this.nsala = nsala;
		this.descripcion = descripcion;
	}

	public Pista(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public String getCod_pista() {
		return cod_pista;
	}
	
	public String getNsala() {
		return nsala;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public boolean getSolicitado() {
		return solicitado;
	}

	public boolean getConfirmado() {
		return confirmado;
	}

	@Override
	public String toString() {
		return "Pista [cod_pista=" + cod_pista + ", nsala=" + nsala + ", descripcion=" + descripcion + "]";
	}

	

	
	
}
