package clinicacanina.modelo;

import com.sun.istack.NotNull;
import org.hsqldb.HsqlDateTime;

import javax.persistence.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity
public class Turno {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String fecha;


    private Boolean estado;

	private long usuarioID;
	private Calendar FechaTurno = new GregorianCalendar();


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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getUsuarioID() {
		return usuarioID;
	}

	public void setUsuarioID(long usuarioID) {
		this.usuarioID = usuarioID;
	}

	public Calendar getFechaTurno() {
		return FechaTurno;
	}

	public void setFechaTurno(Calendar fechaTurno) {
		FechaTurno = fechaTurno;
	}
}
