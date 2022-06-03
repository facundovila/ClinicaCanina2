package clinicacanina.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Turno {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String fecha;

    @Column(nullable = false)
    @NotNull
    private Boolean estado;


	public Turno(String fecha) {
		this.fecha = fecha;
		this.estado = false;
	}

	public Turno() {
		
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
