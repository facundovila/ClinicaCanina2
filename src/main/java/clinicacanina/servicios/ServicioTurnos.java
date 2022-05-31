package clinicacanina.servicios;

import java.util.Date;
import java.util.List;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Turno;

public interface ServicioTurnos {

	void crearTurno(String fecha, Boolean estado);

	Turno buscarTurno(Turno turno);

}
