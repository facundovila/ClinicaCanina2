package clinicacanina.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name= "Turno")
public class Turno {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(nullable = false)
    @NotNull
    @Size(min = 3, message = "fecha invalida")
    private String fecha;

    @Column(nullable = false)
    @NotNull
    private Boolean estado;

//    @Column(nullable = false)
//    @NotNull
//    private Mascota mascota;
//
//	private String fecha;
//	private Boolean estado;
//	private Mascota mascota;
	

	public Turno(String fecha) {
		this.fecha = fecha;
		this.estado = false;
		//this.mascota=mascota;
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
