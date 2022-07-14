package clinicacanina.servicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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
	private  ServicioValidadorFecha servicioValidadorFecha;



    @Autowired
    public ServicioTurnosImpl(RepositorioTurnos repositorioTurnos,ServicioLogin servicioLogin,RepositorioUsuario repositorioUsuario,ServicioMascota servicioMascota,ServicioValidadorFecha servicioValidadorFecha){
		this.servicioMascota=servicioMascota;
		this.repositorioUsuario=repositorioUsuario;
			this.servicioLogin=servicioLogin;
			this.setRepositorioTurnos(repositorioTurnos);
			this.servicioValidadorFecha=servicioValidadorFecha;
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

		return repositorioTurnos.cancelarTurnoPorId(id);

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

		List<Turno> lista =new ArrayList<>();
		Turno turno=repositorioTurnos.buscarProximoTurnoLibre();
		if (turno==null){
			lista =new ArrayList<>();
			return lista;
		}
		lista=repositorioTurnos.buscarTurnosPorFecha(turno.getFechaTurno());
		if (lista==null){
			lista =new ArrayList<>();
			return lista;
		}
		return lista;
	}

	@Override
	public void tomarTurnoUsuario(Long idUsuario, Long idTurno) {
		Usuario usuario= servicioLogin.consultarUsuarioPorID(idUsuario);
		repositorioTurnos.tomarTurnoUsuario(usuario,idTurno);
	}

	@Override
	public List<Turno> buscarTurnoPorFecha(String fecha) {
		List <Turno>lista= new ArrayList<>();
		if(servicioValidadorFecha.validarFecha(fecha)==false){
			return lista;
		}
		Calendar calendar=servicioValidadorFecha.StringACalendar(fecha);
		Calendar actual=Calendar.getInstance();
		if(actual.compareTo(calendar)<1){
		lista=repositorioTurnos.buscarTurnosPorFecha(calendar);
		if (lista==null){
			lista =new ArrayList<>();
			return lista;
		}}
		return lista;
	}

	@Override
	public Turno buscarTurnoPorId(Long id) {
		return repositorioTurnos.buscarTurnoPorId(id);
	}

}
