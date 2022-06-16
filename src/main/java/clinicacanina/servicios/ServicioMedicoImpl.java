package clinicacanina.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import clinicacanina.controladores.DatosMedicos;
import clinicacanina.modelo.Medico;
import clinicacanina.repositorios.RepositorioMedico;

@Service
@Transactional
public class ServicioMedicoImpl implements ServicioMedico{
	
	private RepositorioMedico repositorioMedico;
	
	@Autowired
	public ServicioMedicoImpl(RepositorioMedico repositorioMedico) {
		this.repositorioMedico = repositorioMedico;
	}


	@Override
	public List<Medico> listarMedico() {
		return repositorioMedico.traerTodosLosMedicos();
	}


	@Override
	public void enviarMedico(Medico medico) {
		medico.setDisponibilidad(false);
		repositorioMedico.modificarDisponibilidadMedico(medico);
	}
	
	public Medico buscarMedicosPorNombre(Integer dni) {
		return repositorioMedico.buscarMedicoPorDni(dni);
	}





}
