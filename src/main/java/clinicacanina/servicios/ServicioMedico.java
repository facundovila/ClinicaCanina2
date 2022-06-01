package clinicacanina.servicios;

import java.util.List;

import clinicacanina.modelo.Medico;

public interface ServicioMedico {

	List<Medico> traerMedicos();

	Medico crearMedico(String nombre, Integer horarioEntrada, Integer horarioSalida);

}
