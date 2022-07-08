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
	public void quePuedaBuscarUnTurno() {
		Long id = 1L;
		dadoQueExisteTurno(id);
		Turno turno = cuandoBuscoUnTurno(id);
		entoncesEncuentroTurno(turno);
	}
	
	@Test
	public void queSePuedaCancelarUnTurno() {
		Long id = 1L;
		dadoQueExisteTurno(id);
		Boolean turnoCancelado = cuandoCanceloUnTurno(id);
		entoncesNoEncuentroTurno(turnoCancelado);
	}


	private void entoncesNoEncuentroTurno(Boolean turnoCancelado) {
		assertThat(turnoCancelado).isTrue();
	}

	private Boolean cuandoCanceloUnTurno(Long id) {
		return servicioTurnos.cancelarTurnoPorId(id);
	}

	private void dadoQueExisteTurno(Long id) {

		when(repositorioTurnos.buscarTurnoPorId(id)).thenReturn(new Turno());
	}
	
	private Turno cuandoBuscoUnTurno(Long id) {
		return servicioTurnos.buscarTurnoPorId(id);
	}
	
	private void entoncesEncuentroTurno(Turno turno) {
		assertThat(turno).isNotNull();
	}
	
//	@Test
//	public void listarTodosLosTurnosDisponiblesEnFecha() {
//		Integer cantidadTurnosEsperados=2;
//		Integer cantidadTurnos=2;
//		dadoQueExisteTurnoEnFecha("fecha", cantidadTurnos);
//		
//		List<Turno>turnosEncontrados= cuandoBuscoTodosLosTurnos("fecha");
//		
//		entoncesEncuentroTodosLosTurnos(turnosEncontrados, cantidadTurnosEsperados);
//	}
//
//
//	private void dadoQueExisteTurnoEnFecha(String fecha, Integer cantidadTurnos) {
//
//		List<Turno>lista=new ArrayList<>();
//		for(int i=0; i<cantidadTurnos; i++) {
//			lista.add(new Turno(fecha));
//		}
//		when(repositorioTurnos.mostrarTurnoDisponible(fecha)).thenReturn(lista);
//	}
//	
//	private List<Turno> cuandoBuscoTodosLosTurnos(String fecha) {
//		
//		return servicioTurnos.buscarTurno(fecha);
//	}
//
//
//	private void entoncesEncuentroTodosLosTurnos(List<Turno> turnosEncontrados, Integer cantidadTurnosEsperados) {
//
//		assertThat(turnosEncontrados).hasSize(cantidadTurnosEsperados);
//	}
//
//	@Test
//	public void CuandoBuscoLosTurnosPorUsuarioRegresanTodosLosTurnos(){
//		//preparacion
//		List<Turno> lista= new ArrayList<>();
//		Turno turno = new Turno();
//		lista.add(turno);
//		when(repositorioTurnos.mostarTurnosDelUsuario(1L)).thenReturn(lista);
//		// ejecucion
//		List<Turno> listaEsperada= repositorioTurnos.mostarTurnosDelUsuario(1L);
//		//copararacion
//		assertThat(lista).isEqualTo(listaEsperada);
//
//	}
//
//	@Test
//	public void CuandoBuscoLosTurnosDelUsuarioYnoTieneRegresaListaVacia() {
//		// preparacion
//		List<Turno> lista = new ArrayList<>();
//		when(repositorioTurnos.mostarTurnosDelUsuario(1L)).thenReturn(lista);
//		List listaEsperada = new ArrayList();
//		// ejecucion
//		listaEsperada = servicioTurnos.turnosDelUsuario(1L);
//		//comparacion
//		assertThat(listaEsperada.isEmpty()).isTrue();
//		assertThat(listaEsperada.size()).isEqualTo(0);
//		assertThat(listaEsperada).isNotNull();
//	}
//
//



}
