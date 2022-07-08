package clinicacanina.servicios;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.modelo.Turno;
import clinicacanina.repositorios.RepositorioTurnos;

import static java.util.Calendar.YEAR;


@Service @Transactional 
public class ServicioTurnosImpl implements ServicioTurnos {
	
	private RepositorioTurnos repositorioTurnos;


    @Autowired
    public ServicioTurnosImpl(RepositorioTurnos repositorioTurnos){

		this.setRepositorioTurnos(repositorioTurnos);
    }

	@Override
	public List<Turno> buscarTurno(String fecha) {

		return repositorioTurnos.mostrarTurnoDisponible(fecha);

	}

	@Override
	public List<Turno> turnosDelUsuario(long usuarioId) {
		return repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(usuarioId);
	}

	public RepositorioTurnos getRepositorioTurnos() {
		return repositorioTurnos;
	}

	public void setRepositorioTurnos(RepositorioTurnos repositorioTurnos) {

		this.repositorioTurnos = repositorioTurnos;
	}
	
	public Boolean cancelarTurnoPorId(Long id) {

		//esto tira error
		//org.springframework.web.util.NestedServletException: Request processing failed; nested exception is org.hibernate.NonUniqueObjectException: A different object with the same identifier value was already associated with the session : [clinicacanina.modelo.Turno#1]

		Turno turnoEsperado = repositorioTurnos.buscarTurnoPorId(id);

		if(turnoEsperado == null) {
			return false;}
		repositorioTurnos.cancelarTurnoPorId(id);
		return true;

		//return repositorioTurnos.cancelarTurnoPorId(id);
		}

	@Override
	public List<Turno> buscarTurnoPorFecha(Calendar fecha) {
		return null;
	}

	@Override
	public List<Turno> buscarTurnoPorFechaDeHoy() {
		/*
		List<Turno> turno = new ArrayList<>();
		repositorioTurnos.mostarTurnosDisponiblesFechaHoy();
		if(turno.isEmpty()){
			return turno;
		}*/
		return repositorioTurnos.mostarTurnosDisponiblesFechaHoy();
	}

	@Override
	public Turno buscarTurnoPorId(Long id) {
		return repositorioTurnos.buscarTurnoPorId(id);
	}

}
