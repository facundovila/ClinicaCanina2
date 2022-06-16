package clinicacanina.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ambulancia {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String patente;
	private Boolean disponible;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPatente() {
		return patente;
	}
	public void setPatente(String patente) {
		this.patente = patente;
	}
	public Boolean getDisponibilidad() {
		return disponible;
	}
	public void setDisponibilidad(Boolean disponible) {
		this.disponible = disponible;
	}
	
	
}
