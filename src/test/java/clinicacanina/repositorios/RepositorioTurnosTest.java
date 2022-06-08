package clinicacanina.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.hibernate.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import clinicacanina.SpringTest;
import clinicacanina.modelo.Turno;

@Repository
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
	        String fecha = cuandoGuardoTurno(turno);


	        //validacion
	        entoncesEncuentroTurno(fecha);

	    }
		
		@Test
		@Transactional @Rollback()
		public void creaUnTurnoYLoBuscaPorFecha() {
			
			
			Turno turno1= dadoQueExisteTurno("27-05-2022");
			Turno turno2= dadoQueExisteTurno("26-05-2022");
			Turno turno3= dadoQueExisteTurno("26-05-2022");
			
			cuandoGuardoTurno(turno1);
			cuandoGuardoTurno(turno2);
			cuandoGuardoTurno(turno3);
			
			List<Turno>turnoBuscado=cuandoBuscoTurnoPorFecha("25-02-2022");
			
			Integer cantidadEsperada=2;
			
			entoncesEncuentroTurno(turnoBuscado, cantidadEsperada);

		
		}

	private void entoncesEncuentroTurno(List<Turno> turnoBuscado, Integer cantidadEsperada) {
			assertThat(turnoBuscado).hasSize(cantidadEsperada);
		}

	private List<Turno> cuandoBuscoTurnoPorFecha(String fecha) {
			return repositorioTurnos.buscarPorFecha(fecha);
		}

	private void entoncesEncuentroTurno(String fecha) {
		List<Turno> turno = repositorioTurnos.buscarPorFecha(fecha);
        assertThat(turno).isNotNull();		
	}

	private String cuandoGuardoTurno(Turno turno) {
			turno.setEstado(true);
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
