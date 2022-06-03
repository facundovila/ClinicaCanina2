package clinicacanina.repositorios;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Medico;

public class RepositorioMedicoTest extends SpringTest{
	
	
	@Autowired
	private RepositorioMedico repositorioMedico;
	
	@Test
	@Transactional
	@Rollback
	public void poderTraerTodosLosMedicos() {
		//preparacion
		Medico medico = dadoQueExisteMedico("Marcelo", 8, 20); 
		dadoQueGuardoMedicos(medico);
		//ejecucion
		List<Medico> listaDeMedicos = cuandoObtengoTodosLosMedicos();
		//validacion
		entoncesEncuentroMedicos(listaDeMedicos);
	}


	private Medico dadoQueExisteMedico(String nombre, Integer horarioEntrada, Integer horarioSalida) {
		return new Medico(nombre, horarioEntrada, horarioSalida);
	}
	
	private List<Medico> cuandoObtengoTodosLosMedicos() {
		return repositorioMedico.traerTodosLosMedicos();
	}


	private void entoncesEncuentroMedicos(List<Medico> listaDeMedicos) {
		assertThat(listaDeMedicos).isEmpty();
	}

	private void dadoQueGuardoMedicos(Medico medico) {
		repositorioMedico.guardarMedico(medico);
	}


}
