package clinicacanina.servicios;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import org.junit.Before;
import org.junit.Test;

import clinicacanina.modelo.Turno;
import clinicacanina.repositorios.RepositorioTurnos;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ServicioTurnosTest {
	
	private RepositorioTurnos repositorioTurnos;
	private ServicioTurnos servicioTurnos;
	Calendar calendar;
	private ServicioFechaYhora servicioFechaYhora;

	
	@Before
	public void init() {
		mockStatic(Calendar.class);
		calendar= Mockito.mock(Calendar.class);
		servicioFechaYhora=mock(ServicioFechaYhora.class);
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
		when(repositorioTurnos.cancelarTurnoPorId(id)).thenReturn(true);
		return repositorioTurnos.cancelarTurnoPorId(id);
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

	@Test
	public void CuandoBuscoLosTurnosPorUsuarioRegresanTodosLosTurnos(){
		//preparacion
		List<Turno> lista= new ArrayList<>();
		Turno turno = new Turno();
		lista.add(turno);
		when(repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1L)).thenReturn(lista);
		// ejecucion
		List<Turno> listaEsperada= repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1L);
		//copararacion
		assertThat(lista).isEqualTo(listaEsperada);

	}

	@Test
	public void CuandoBuscoLosTurnosDelUsuarioYnoTieneRegresaListaVacia() {
	// preparacion
		List<Turno> lista = new ArrayList<>();
		when(repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1L)).thenReturn(lista);
		List listaEsperada = new ArrayList();
		// ejecucion
		listaEsperada = servicioTurnos.turnosDelUsuario(1L);
		//comparacion
		assertThat(listaEsperada.isEmpty()).isTrue();
		assertThat(listaEsperada.size()).isEqualTo(0);
		assertThat(listaEsperada).isNotNull();
	}


	/*@Test
	public void ElTurnoSoloSePuedeCancelarHastaSeisHorasDeAnticipacion(){
		Turno turnocargado= new Turno();
		turnocargado.setId(1L);
		Calendar fechaTurno = new GregorianCalendar(2022,06,24,20,00);
		turnocargado.setFechaTurno(fechaTurno);
		when(repositorioTurnos.buscarTurnoPorId(1L)).thenReturn(turnocargado);

		Calendar fechaActual= Calendar.getInstance();
		fechaActual.set(2022,06,24,15,00);
		//Calendar fechaActual= Calendar.getInstance();
		//Mockito.when(Calendar.getInstance()).thenReturn(fechaSistema);
		//Mockito.when(Calendar.getInstance()).thenReturn(fechaSistema);

		//Calendar.getInstance() para obtener la fecha y hora actual en Java

		when(Calendar.getInstance()).thenReturn(fechaActual);
		assertThat(servicioTurnos.cancelarTurnoPorId(1L)).isFalse();

	}
	@Test
	public void ElTurnoSoloSePuedeCancelarHastaSeisHorasDeAnticipacion(){
		long id=1L;
		Turno turnocargado= new Turno();
		turnocargado.setId(1L);
		turnocargado.setFecha("2022-06-26");
		turnocargado.setHora("17:00");
		when(repositorioTurnos.buscarTurnoPorId(id)).thenReturn(turnocargado);
		Calendar fechaSistema = new GregorianCalendar(2022,06,24,10,00);
		when(servicioFechaYhora.fechaDelSistema()).thenReturn(fechaSistema);
		//ejecucion
		//comparacion
		assertThat(servicioTurnos.cancelarTurnoPorId(id)).isFalse();

	}*/




}
