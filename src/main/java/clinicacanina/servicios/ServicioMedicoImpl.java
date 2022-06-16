package clinicacanina.servicios;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Medico buscarMedicoLogin(Integer dni, String password){
//		Medico medico;
//
//		if (repositorioMedico.buscarPorMailYPassword(dni, password) == null) {
//
//			throw new MedicoInexistenteExepcion();
//
//		}else{
//				medico =repositorioMedico.buscarPorMailYPassword(dni, password);
//		}
		return repositorioMedico.buscarPorMailYPassword(dni, password);

	}

	@Override
	public Medico getMedico(Long idUsuario) {
		return repositorioMedico.getById(idUsuario);
	}

	@Override
	public Long guardarMedico(Medico medico){
		repositorioMedico.guardarMedico(medico);

		return medico.getId();
	}






}
