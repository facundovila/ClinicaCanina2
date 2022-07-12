package clinicacanina.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;
import clinicacanina.repositorios.RepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.modelo.Turno;
import clinicacanina.repositorios.RepositorioTurnos;


@Service @Transactional 
public class ServicioTurnosImpl implements ServicioTurnos {
	
	private RepositorioTurnos repositorioTurnos;
	private RepositorioUsuario repositorioUsuario;
	private ServicioLogin servicioLogin;
	private ServicioMascota servicioMascota;



    @Autowired
    public ServicioTurnosImpl(RepositorioTurnos repositorioTurnos,ServicioLogin servicioLogin,RepositorioUsuario repositorioUsuario,ServicioMascota servicioMascota){
		this.servicioMascota=servicioMascota;
		this.repositorioUsuario=repositorioUsuario;
			this.servicioLogin=servicioLogin;
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
		return repositorioTurnos.mostarTurnosDisponiblesFechaHoy();
	}

	@Override
	public boolean tomarTurno(long idMascota, long idUsuario, long idTurno) {
		Usuario u= servicioLogin.consultarUsuarioPorID(idUsuario);
		Mascota m= servicioMascota.buscarMascotaPorId(idMascota);
		boolean estado=repositorioTurnos.tomarTurno(m, u,idTurno);
		return estado;
	}

	@Override
	public List<Turno> buscarProximosTurnos() {
		Calendar calendar =repositorioTurnos.buscarProximoTurnoLibre().getFechaTurno();
		List<Turno> lista =repositorioTurnos.buscarTurnosPorFecha(calendar);
		return lista;
	}
	@Override
	public Turno buscarTurnoPorId(Long id) {
		return repositorioTurnos.buscarTurnoPorId(id);
	}

}
