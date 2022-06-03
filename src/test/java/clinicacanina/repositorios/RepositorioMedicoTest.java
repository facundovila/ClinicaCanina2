package clinicacanina.repositorios;



import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.SpringTest;

public class RepositorioMedicoTest extends SpringTest{
	
	
	@Autowired
	private RepositorioMedico repositorioMedico;
	
	@Test
	@Transactional
	@Rollback
	public void nada() {
		
	}

}
