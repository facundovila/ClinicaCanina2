package clinicacanina.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Turno;

public class RepositorioTurnosTest extends SpringTest{
	
	@Autowired
	private RepositorioTurnos repositorioTurnos;
	
	 @Test
	    @Transactional
	    @Rollback
	    public void guardarUnTurnoDeberiaPersistirlo(){

	        //preparacion
	        Turno turno = dadoQueExisteTurno("fecha");

	        //ejecucion
	        String idTurno = cuandoGuardoMascota(turno);


	        //validacion
	        entoncesEncuentroLaMascota(idTurno);

	    }

	private void entoncesEncuentroLaMascota(String idTurno) {
		Turno turno = repositorioTurnos.buscarPorId(idTurno);
        assertThat(turno).isNotNull();		
	}

	private String cuandoGuardoMascota(Turno turno) {
		 repositorioTurnos.guardarTurno(turno);

	        return turno.getFecha();
	}

	private Turno dadoQueExisteTurno(String fecha) {
		   Turno turno = new Turno();
		   turno.setFecha(fecha);
           turno.setEstado(false);
           return turno;
	}

}
