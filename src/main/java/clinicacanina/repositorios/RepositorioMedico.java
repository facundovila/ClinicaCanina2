package clinicacanina.repositorios;

import java.util.List;

import clinicacanina.controladores.DatosMedicos;
import clinicacanina.modelo.Medico;

public interface RepositorioMedico {

	List<Medico> traerTodosLosMedicos();

	void guardarMedico(Medico medico);


	Medico buscarPorMailYPassword(Integer dni, String password);

	Medico getById(Long idUsuario);


	void modificarDisponibilidadMedico(Medico medico);
	
	Medico buscarMedicoPorDni(Integer dni);

	


}
