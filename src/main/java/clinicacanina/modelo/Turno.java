package clinicacanina.modelo;


public class Turno {
	
	private String fecha;
	private Boolean estado;
	private Mascota mascota;
	

	public Turno(String fecha) {
		this.fecha = fecha;
		this.estado = false;
		this.mascota=mascota;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	
	
	

}
