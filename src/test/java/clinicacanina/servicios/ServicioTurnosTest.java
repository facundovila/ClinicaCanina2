package clinicacanina.servicios;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import clinicacanina.modelo.Turno;
import clinicacanina.repositorios.RepositorioTurnos;


public class ServicioTurnosTest {
	
	private RepositorioTurnos repositorioTurnos;
	private ServicioTurnos servicioTurnos;
	
	@Before
	public void init() {
		repositorioTurnos = mock(RepositorioTurnos.class);
		servicioTurnos = new ServicioTurnosImpl(repositorioTurnos);
	}
	
	@Test
	public void listarTodosLosTurnosDisponiblesEnFecha() {
		
		Integer cantidadTurnosEsperados=2;
		Integer cantidadTurnos=2;
		
		dadoQueExisteTurnoEnFecha("fecha", cantidadTurnos);
		
		List<Turno>turnosEncontrados= cuandoBuscoTodosLosTurnos("fecha");
		
		entoncesEncuentroTodosLosTurnos(turnosEncontrados, cantidadTurnosEsperados);
	}


	private void dadoQueExisteTurnoEnFecha(String fecha, Integer cantidadTurnos) {

		List<Turno>lista=new ArrayList<>();
		for(int i=0; i<cantidadTurnos; i++) {
			lista.add(new Turno(fecha));
		}
		when(repositorioTurnos.mostrarTurnoDisponible(fecha)).thenReturn(lista);
	}
	
	private List<Turno> cuandoBuscoTodosLosTurnos(String fecha) {
		
		return servicioTurnos.buscarTurno(fecha);
	}


	private void entoncesEncuentroTodosLosTurnos(List<Turno> turnosEncontrados, Integer cantidadTurnosEsperados) {

		assertThat(turnosEncontrados).hasSize(cantidadTurnosEsperados);
	}
}
