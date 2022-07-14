package clinicacanina.servicios;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import clinicacanina.controladores.ControladorAmbulancia;
import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Navegador;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.RepositorioNavegacion;
import clinicacanina.repositorios.Trayecto;

public class ServicioNavegacionTest {
	
	private ServicioNavegacion servicioNavegacion;
	private RepositorioNavegacion repositorioNavegacion;
	private final LocalDateTime AHORA = LocalDateTime.now();
	

	@Before
	public void init() {
		repositorioNavegacion = mock(RepositorioNavegacion.class);
		servicioNavegacion = new ServicioNavegacionImpl(repositorioNavegacion);
	}
	
	@Test
	public void queSePuedaObtenerLaHoraActual() {
		
		LocalDateTime horarioActual = servicioNavegacion.obtenerHoraActual();
		
		System.out.println(horarioActual.getHour() + ":" + horarioActual.getMinute()+ ":" + horarioActual.getSecond());
		assertThat(horarioActual.getHour()).isEqualTo(AHORA.getHour());
		assertThat(horarioActual.getMinute()).isEqualTo(AHORA.getMinute());
		assertThat(horarioActual.getSecond()).isEqualTo(AHORA.getSecond());
		System.out.println("Anio : " +horarioActual.getYear());
		System.out.println("Mes : " + horarioActual.getMonthValue());
        System.out.println("Dia : " +horarioActual.getDayOfMonth());
        System.out.println(horarioActual.getYear() + "-"+horarioActual.getMonthValue()+"-"+horarioActual.getDayOfMonth()+" "+horarioActual.getHour()+":"+horarioActual.getMinute());
		
	}
	
	@Test
	public void queSePuedaGuardarLosDatosDeNavegacion() {
		Navegador navegador = dadoQueExisteUnSeguimientoDeUnaReserva();
		String patente = cuandoGuardoUnaNavegacion(navegador);
		entoncesObtengoLaPatenteDeLaNavegacionGuardada(patente);
	}
	
	@Test
	public void queSePuedaBuscarUnaNavegacion() {
		dadoQueExisteUnaNavegacion();
		Navegador navegador = cuandoBuscoUnaNavegacion("ABC123");
		entoncesObtengoUnObjetoNavegador(navegador);
	}
	
	@Test
	public void queSePuedaCalcularElHorarioEstimadoDeLlegadaDeLaAmabulancia() {
		String patente = "ABC123";
		dadoQueExisteUnaNavegacion();
		LocalDateTime horarioDeLlegada = cuandoConsultoElHorarioEstimadoDeLlegada(patente);
		entoncesObtengoElHorarioDeArrivo(horarioDeLlegada);
	}
	
	@Test
	public void queSePuedaCalcularElTiempoRestanteDeLlegada() {
		String patente = "ABC123";
		dadoQueExisteUnaNavegacionConSuHorarioDeSolicitud();
		String tiempoRestante = cuandoConsultoElTiempoRestanteDeLlegada(patente);
		entoncesObtengoCuantoFaltaParaQueArribeLaAmbulancia(tiempoRestante);
	}
	
	
	private void entoncesObtengoCuantoFaltaParaQueArribeLaAmbulancia(String tiempoRestante) {
		Integer minutosActuales = AHORA.getMinute();
		Integer minutosEstimados = 45;
		Integer diferencia = minutosEstimados - minutosActuales;
		String diferenciaString = String.valueOf(diferencia);
		System.out.println("Tiempo Restante : " + tiempoRestante);
		assertThat(tiempoRestante).isEqualTo(diferenciaString);
		
	}

	private String cuandoConsultoElTiempoRestanteDeLlegada(String patente) {
		return servicioNavegacion.calcularTiempoRestanteDeLlegada(patente);
	}

	private void dadoQueExisteUnaNavegacionConSuHorarioDeSolicitud() {
		Navegador navegador = crearNavegador();
		navegador.setHorarioDeSolicitud("2022-07-10 19:00");
		when(repositorioNavegacion.buscarNavegacion(navegador.getPatente())).thenReturn(navegador);
		
	}

	private void entoncesObtengoElHorarioDeArrivo(LocalDateTime horarioDeLlegada) {
		System.out.println("Horario de llegada : " + horarioDeLlegada);
		assertThat(horarioDeLlegada).isNotNull();
		
	}

	private LocalDateTime cuandoConsultoElHorarioEstimadoDeLlegada(String patente) {
		return servicioNavegacion.calcularHorarioDeLlegada(patente);
	}

	private void entoncesObtengoUnObjetoNavegador(Navegador navegador) {
		assertThat(navegador).isNotNull();
		assertThat(navegador.getPatente()).isEqualTo("ABC123");
		
	}

	private Navegador cuandoBuscoUnaNavegacion(String patente) {
		Navegador navegador = servicioNavegacion.buscarNavegacion(patente);
		return navegador;
	}

	private void dadoQueExisteUnaNavegacion() {
		Navegador navegador = crearNavegador();
		navegador.setHorarioDeSolicitud("2022-07-10 19:00");
		when(repositorioNavegacion.buscarNavegacion("ABC123")).thenReturn(navegador);
		
	}

	private void entoncesObtengoLaPatenteDeLaNavegacionGuardada(String patente) {
		System.out.println(patente);
		assertThat(patente).isEqualTo("ABC123");
		
	}

	private String cuandoGuardoUnaNavegacion(Navegador navegador) {
		Trayecto trayecto = new Trayecto();
		trayecto.setLocalidadOrigen(navegador.getLocalidadOrigen());
		trayecto.setLocalidadDestino(navegador.getLocalidadDestino());
		trayecto.setDistancia(navegador.getDistancia());
		trayecto.setTiempo(navegador.getTiempoEstimado());
		return servicioNavegacion.guardarNavegacion(navegador.getReserva(), trayecto);
	}

	private Navegador dadoQueExisteUnSeguimientoDeUnaReserva() {
		Navegador navegador = crearNavegador();
        
        //-----------------MOCK Repositorio----------------
        when(repositorioNavegacion.buscarNavegacion(navegador.getPatente())).thenReturn(navegador);
        
        return navegador;
		
	}


	private Navegador crearNavegador() {
		ReservaDeAmbulancia reserva = new ReservaDeAmbulancia();
		Trayecto trayecto = new Trayecto();
		Ambulancia ambulancia = new Ambulancia();
		Navegador navegador = new Navegador();
	    
		//-------------- Datos de Ambulancia ---------------
        ambulancia.setPatente("ABC123");
        ambulancia.setDisponibilidad(false);
        //-------------- Datos Reserva ---------------------
        reserva.setAmbulancia(ambulancia);
        reserva.setDireccion("alem171montegrande");
        reserva.setMotivo("Fiebre y vomitos");
        reserva.setTelefono("1122334455");
        //-------------- Datos Trayecto --------------------
        trayecto.setLocalidadOrigen("FlorencioVarela1903SanJusto");
        trayecto.setLocalidadDestino(reserva.getDireccion());
        trayecto.setDistancia("30 km");
        trayecto.setTiempo("45 min");
        //-------------- Datos Navegador -------------------
        navegador.setReserva(reserva);
        navegador.setLocalidadOrigen(trayecto.getLocalidadOrigen());
        navegador.setLocalidadDestino(trayecto.getLocalidadDestino());
        navegador.setDistancia(trayecto.getDistancia());
        navegador.setTiempoEstimado(trayecto.getTiempo());
        navegador.setPatente(ambulancia.getPatente());
        
        return navegador;
	}
	
	
}
