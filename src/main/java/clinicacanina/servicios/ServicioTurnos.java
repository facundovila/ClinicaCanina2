package clinicacanina.servicios;

import java.util.List;

import clinicacanina.modelo.Turno;

public interface ServicioTurnos {


	List<Turno> buscarTurno(String fecha);
	



}
