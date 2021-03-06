package clinicacanina.servicios;

import java.util.Calendar;
import java.util.List;

import clinicacanina.modelo.Turno;

public interface ServicioTurnos {


	List<Turno> buscarTurno(String fecha);


    List<Turno> turnosDelUsuario(long l);



    Turno buscarTurnoPorId(Long id);

	Boolean cancelarTurnoPorId(Long idTurno);

    List<Turno> buscarTurnoPorFecha(String fecha);

    List<Turno> buscarTurnoPorFechaDeHoy();

    boolean tomarTurno(long idMascota, long idUsuario, long idTurno);

    List<Turno> buscarProximosTurnos();

    void tomarTurnoUsuario(Long idUsuario, Long idTurno);
}
