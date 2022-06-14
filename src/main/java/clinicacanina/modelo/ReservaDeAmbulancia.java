package clinicacanina.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ReservaDeAmbulancia {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String direccion;
	 @OneToOne
	 @JoinColumn(name = "ambulancia_id")
	private Ambulancia ambulancia;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Ambulancia getAmbulancia() {
		return ambulancia;
	}
	public void setAmbulancia(Ambulancia ambulancia) {
		this.ambulancia = ambulancia;
	}
	
}
