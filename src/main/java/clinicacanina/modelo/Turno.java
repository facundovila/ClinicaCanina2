package clinicacanina.modelo;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;
import org.hsqldb.HsqlDateTime;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Entity (name = "turno")
public class Turno {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date FechaTurno;
	private Time HoraTurno;
    private String fecha;
    private Boolean estado; // true = disponible


	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;//muchos turnos van a tener un usuario
	@ManyToOne
	@JoinColumn(name = "mascota_id")
	private Mascota mascota;
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	
	
	public Turno(String fecha) {
		this.fecha = fecha;
		this.estado = false;
	}
	
	public Turno() {
		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public Date   getFechaTurno() {
		return FechaTurno;
	}

	public void setFechaTurno(Date   fechaTurno) {
		FechaTurno = fechaTurno;
	}

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Time getHoraTurno() {
		return HoraTurno;
	}

	public void setHoraTurno(Time horaTurno) {
		HoraTurno = horaTurno;
	}
}
