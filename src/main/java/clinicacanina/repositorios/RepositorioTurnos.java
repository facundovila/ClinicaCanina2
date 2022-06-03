package clinicacanina.repositorios;

import java.util.List;

import clinicacanina.modelo.Turno;

public interface RepositorioTurnos {

	void guardarTurno(Turno turnoCreado);

	List<Turno> mostrarTurnoDisponible(String fecha);

	List<Turno> buscarPorFecha(String fecha);
	

}
