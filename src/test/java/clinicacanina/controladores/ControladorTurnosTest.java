package clinicacanina.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
	
	@Test
	public void siHayTurnosEnUnaFechaDeberiaListarlos() {
		
		dadoQueHayTurnosParaLaFecha("26-05-2022");
		
		ModelAndView mav  = cuandoBuscaUnTurnoDeLaFecha("26-05-2022");//aca llamas al metodo del controlador
		
		entoncesEncuentro(2, mav);
				
	}
			
	private void dadoQueHayTurnosParaLaFecha(String fecha) {
		
		List<Turno>lista=new ArrayList<>();
		for(int i=0; i<2;i++) {
			lista.add(new Turno(fecha));
		}

		when(servicioTurnos.buscarTurno("26-05-2022")).thenReturn(lista); //donde lista tiene 2 turnos adentro		
	}
	
	private ModelAndView cuandoBuscaUnTurnoDeLaFecha(String fecha) {

		return controladorTurnos.buscarTurno(fecha);
	}

	private void entoncesEncuentro(Integer cantidad, ModelAndView mav) {
		
		List<Turno>lista=(List<Turno>)mav.getModel().get("msg");
		assertThat(lista).hasSize(cantidad);
				
	}

}
