package clinicacanina.repositorios;

import java.util.List;

import clinicacanina.controladores.DatosMedicos;
import clinicacanina.modelo.Medico;

public interface RepositorioMedico {

	List<Medico> traerTodosLosMedicos();

	void guardarMedico(Medico medico);

	void modificarDisponibilidadMedico(Medico medico);
	
	Medico buscarMedicoPorDni(Integer dni);

	

}
