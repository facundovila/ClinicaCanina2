package clinicacanina.servicios;

import java.util.List;

import clinicacanina.controladores.DatosMedicos;
import clinicacanina.modelo.Medico;

public interface ServicioMedico {

	List<Medico> listarMedico();


	Medico buscarMedicoLogin(Integer dni, String password);

	Medico getMedico(Long idUsuario);

	Long guardarMedico(Medico medico);

	void enviarMedico(Medico medico);
	
	Medico buscarMedicosPorNombre(Integer dni);


}
