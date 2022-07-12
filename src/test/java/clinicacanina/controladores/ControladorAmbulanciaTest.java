package clinicacanina.controladores;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;


import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.ErrorDeReserva;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.servicios.ServicioAmbulancia;
import clinicacanina.servicios.ServicioGoogleDistanceMatrixAPI;
import clinicacanina.servicios.ServicioNavegacion;
import clinicacanina.servicios.ServicioValidacionDatos;

import static org.assertj.core.api.Assertions.*;

public class ControladorAmbulanciaTest {
	
	private Ambulancia ambulancia1 = new Ambulancia();
	private Ambulancia ambulancia2 = new Ambulancia();
	private Ambulancia ambulancia3 = new Ambulancia();
	private final String PATENTE_1 = "abc123";
	private final String PATENTE_2 = "def456";
	private final String PATENTE_3 = "ghi789";
	private final boolean DISPONIBLE = true;
	private final boolean NO_DISPONIBLE = false;
	private final String VISTA_AMBULANCIA_RESERVADA = "reservaAmbulancia"; 
	private final String MODEL_ERROR = "Ocurrio un error inesperado al intentar reservar."; 
	private ControladorAmbulancia controladorAmbulancia;
	private ServicioAmbulancia servicioAmbulancia;
	private ServicioNavegacion servicioNavegacion;
	//private ServicioValidacionDatos servicioValidacionDatos;
	//private ServicioGoogleDistanceMatrixAPI servicioGoogleDistanceMatrixAPI = new ServicioGoogleDistanceMatrixAPI();
	
	@Before
	public void init() {
		servicioAmbulancia = mock(ServicioAmbulancia.class);
		servicioNavegacion = mock(ServicioNavegacion.class);
		//servicioValidacionDatos = mock(ServicioValidacionDatos.class);
		//controladorAmbulancia = new ControladorAmbulancia(servicioAmbulancia, servicioValidacionDatos);
		controladorAmbulancia = new ControladorAmbulancia(servicioAmbulancia, servicioNavegacion);
	}
	
	@Test
	public void poderListarLasAmbulanciasDisponibles() {
		List <Ambulancia> ambulanciasDisponibles = new LinkedList<Ambulancia>();
		
		dadoQueHayAmbulanciasDisponibles(setearAmbulanciasEnDisponible(), 3);
		ModelAndView mav = cuandoPidoUnaAmbulancia();
		entoncesObtengoUnaListaDeAmbulanciasDisponibles((List<Ambulancia>)mav.getModel().get("AmbulanciaDisponible"), 3);
	}
	
	@Test
	public void poderReservarUnaAmbulanciaDisponible() {
		dadoQuePuedoReservarUnaAmbulancia();
		ModelAndView mav = cuandoReservoUnaAmbulancia();
		entoncesMeRedirigeALaVistaDeReservada(VISTA_AMBULANCIA_RESERVADA, mav.getViewName());
		
	}
	

	private void dadoQuePuedoReservarUnaAmbulancia() {
		when(servicioAmbulancia.buscarAmbulanciaPorPatente(PATENTE_1)).thenReturn(setearAmbulanciasEnDisponible().get(0));
		
	}

	private void entoncesMeRedirigeALaVistaDeReservada(String vistaEsperada, String vistaObtenida) {
		assertThat(vistaEsperada).isEqualTo(vistaObtenida);
		
	}

	private ModelAndView cuandoReservoUnaAmbulancia() {
		DatosReservaAmbulancia reservaDeAmbulancia = new DatosReservaAmbulancia();
		reservaDeAmbulancia.setPatente(setearAmbulanciasEnDisponible().get(0).getPatente());
		reservaDeAmbulancia.setDireccion("Independencia 333");
		reservaDeAmbulancia.setLocalidad("Monte Grande");
		reservaDeAmbulancia.setTelefono("1133804973");
		reservaDeAmbulancia.setMotivo("Fiebre y vomitos");
		return controladorAmbulancia.reservarAmbulancia(reservaDeAmbulancia);
		
	}

	private void dadoQueHayAmbulanciasDisponibles(List<Ambulancia> ambulanciasDisponibles, int cantidad) {
		
		when(servicioAmbulancia.buscarAmbulanciasDisponibles()).thenReturn(ambulanciasDisponibles);
		
	}

	private ModelAndView cuandoPidoUnaAmbulancia() {
		return controladorAmbulancia.listarAmbulanciasDisponibles();
	}
	
	private void entoncesObtengoUnaListaDeAmbulanciasDisponibles(List<Ambulancia> ambulanciasRecibidas, int cantidadEsperada) {
		assertThat(ambulanciasRecibidas.size()).isEqualTo(cantidadEsperada);
		assertThat(ambulanciasRecibidas.get(0).getDisponibilidad()).isEqualTo(DISPONIBLE);
		assertThat(ambulanciasRecibidas.get(1).getDisponibilidad()).isEqualTo(DISPONIBLE);
		assertThat(ambulanciasRecibidas.get(2).getDisponibilidad()).isEqualTo(DISPONIBLE);
		
	}
	
	private List <Ambulancia> setearAmbulanciasEnDisponible() {
		List <Ambulancia> ambulanciasDisponibles = new LinkedList<Ambulancia>();
		//Seteo valores de ambulancias
				ambulancia1.setPatente(PATENTE_1);
				ambulancia1.setDisponibilidad(DISPONIBLE);
				ambulancia2.setPatente(PATENTE_2);
				ambulancia2.setDisponibilidad(DISPONIBLE);
				ambulancia3.setPatente(PATENTE_3);
				ambulancia3.setDisponibilidad(DISPONIBLE);
				//----------------------------------------------------
				//Agrego ambulancias a la lista
				ambulanciasDisponibles.add(ambulancia1);
				ambulanciasDisponibles.add(ambulancia2);
				ambulanciasDisponibles.add(ambulancia2);
				//retorno
				return ambulanciasDisponibles;
	}
    
	private List <Ambulancia> setearAmbulanciasEnNoDisponible() {
		List <Ambulancia> ambulanciasDisponibles = new LinkedList<Ambulancia>();
		//Seteo valores de ambulancias
				ambulancia1.setPatente(PATENTE_1);
				ambulancia1.setDisponibilidad(NO_DISPONIBLE);
				ambulancia2.setPatente(PATENTE_2);
				ambulancia2.setDisponibilidad(NO_DISPONIBLE);
				ambulancia3.setPatente(PATENTE_3);
				ambulancia3.setDisponibilidad(NO_DISPONIBLE);
				//----------------------------------------------------
				//Agrego ambulancias a la lista
				ambulanciasDisponibles.add(ambulancia1);
				ambulanciasDisponibles.add(ambulancia2);
				ambulanciasDisponibles.add(ambulancia2);
				//retorno
				return ambulanciasDisponibles;
	}
}
