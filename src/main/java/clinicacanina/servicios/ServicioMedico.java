package clinicacanina.servicios;

import java.util.List;

import clinicacanina.modelo.Medico;

public interface ServicioMedico {

	List<Medico> listarMedico();

	Medico buscarMedicoLogin(Integer dni, String password);

}
