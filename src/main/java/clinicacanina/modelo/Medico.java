package clinicacanina.modelo;

import javax.persistence.*;

@Entity(name = "medico")
public class Medico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String contrasenia;
	private Integer dni;

	
	private String nombre;

	private Integer horarioEntrada; 
	
	private Integer horarioSalida;
	
	private Boolean disponibilidad;

	
	public Medico() {
		
	}
	

	public Medico(String nombre, Integer horarioEntrada, Integer horarioSalida) {
		this.nombre = nombre;
		this.horarioEntrada = horarioEntrada;
		this.horarioSalida = horarioSalida;
		this.disponibilidad = true;
	}

	public Medico(Integer dni, String contrasenia) {

		this.contrasenia = contrasenia;
		this.dni = dni;
	}




	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getHorarioEntrada() {
		return horarioEntrada;
	}
	public void setHorarioEntrada(Integer horarioEntrada) {
		this.horarioEntrada = horarioEntrada;
	}
	public Integer getHorarioSalida() {
		return horarioSalida;
	}
	public void setHorarioSalida(Integer horarioSalida) {
		this.horarioSalida = horarioSalida;
	}

	public Boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
}
