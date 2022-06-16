package clinicacanina.servicios;

import java.util.List;

import clinicacanina.controladores.DatosMedicos;
import clinicacanina.modelo.Medico;

public interface ServicioMedico {

	List<Medico> listarMedico();

	void enviarMedico(Medico medico);
	
	Medico buscarMedicosPorNombre(Integer dni);

}
