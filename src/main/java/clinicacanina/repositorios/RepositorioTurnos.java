package clinicacanina.repositorios;

import java.util.Calendar;
import java.util.List;

import clinicacanina.modelo.Turno;

public interface RepositorioTurnos {

	void guardarTurno(Turno turnoCreado);

	List<Turno> mostrarTurnoDisponible(String fecha);

	List<Turno> buscarPorFecha(String fecha);

	List<Turno> mostrarTurnoUsuarioDesdeHoy(long usuarioId);

    List<Turno> mostarTodosTurnosDelUsuario(long usuarioId);

    Turno buscarTurnoPorId(Long id);

	Boolean cancelarTurnoPorId(Long id);

	List<Turno> mostarTurnosDisponiblesFechaHoy();

    boolean tomarTurno(long idMascota, long idUsuario, long idTurno);
}
