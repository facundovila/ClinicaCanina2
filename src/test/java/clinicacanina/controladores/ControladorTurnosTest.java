package clinicacanina.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Turno;
import clinicacanina.servicios.ServicioTurnos;

public class ControladorTurnosTest {

	private static final String VISTA_FECHA_ESPERADA = "turnoreservado";
	private ControladorTurnos controladorTurnos;
	private ServicioTurnos servicioTurnos;
	
	
	@Before
    public void init(){
        servicioTurnos= mock(ServicioTurnos.class);
        controladorTurnos= new ControladorTurnos(servicioTurnos);
    }
	
	
	
//	@Test
//	public void queSePuedaReservarUnTurno() {
//		
//		dadoQueExisteUnTurno("fecha", false);
//		
//		
//	}
//	
	
	
	@Test 
	public void cuandoReservoUnTurnoOcupadoMeLLevaALaVistaTurnoOcupado() {
		
		String fecha="fecha";
		Boolean estado= true;
				
		dadoQueElTurnoEstaReservado("fecha", true);
				
		ModelAndView mav=cuandoBuscoElTurno(fecha, estado);
		
		entoncesMeDevuelveVistaTurnoReservado(VISTA_FECHA_ESPERADA, mav.getViewName());
		
	}


	private void dadoQueElTurnoEstaReservado(String fecha, Boolean ocupado) {
         Turno turnoReservado =new Turno(fecha, ocupado);
		when(servicioTurnos.buscarTurno(turnoReservado)).thenReturn(turnoReservado);
}


	private ModelAndView cuandoBuscoElTurno(String fecha, Boolean estado) {
		
		return controladorTurnos.buscarTurno(fecha, estado);
	}
	
	private void entoncesMeDevuelveVistaTurnoReservado(String VISTA_FECHA_ESPERADA, String vistaRecibida) {

		assertThat(vistaRecibida).isEqualTo(VISTA_FECHA_ESPERADA);
	}

}
