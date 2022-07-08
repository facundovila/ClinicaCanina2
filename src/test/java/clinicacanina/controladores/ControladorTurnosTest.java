package clinicacanina.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Turno;
import clinicacanina.servicios.ServicioTurnos;

public class ControladorTurnosTest {

	private static final String VISTA_FECHA_ESPERADA = "turnoreservado";
	private ControladorTurnos controladorTurnos;
	private ServicioTurnos servicioTurnos;
	private HttpServletRequest request;
	private HttpSession session;
	
	
	@Before
    public void init(){
		request = mock(HttpServletRequest.class); //voy a mock la session, userId
		session = mock(HttpSession.class);
        servicioTurnos= mock(ServicioTurnos.class);
        controladorTurnos= new ControladorTurnos(servicioTurnos);
    }
	
//	@Test
//	public void siHayTurnosEnUnaFechaDeberiaListarlos() {
//		
//		dadoQueHayTurnosParaLaFecha("26-05-2022");
//		
//		ModelAndView mav  = cuandoBuscaUnTurnoDeLaFecha("26-05-2022");//aca llamas al metodo del controlador
//		
//		entoncesEncuentro(2, mav);
//				
//	}
	
	@Test
	public void queSePuedaCancelarUnTurno() {
		Long id = 1L;
		dadoQueExistaElTurno(id);
//		ModelAndView mav = cuandoBuscoElTurnoPorId(id);
//		entoncesCanceloElTurno(mav);
	}
	
	@Test
	public void queCancelarTurnoMeLleveAlLogin() {
		Long id = 1L;
		dadoQueNoExistaUsuario(id);
//		ModelAndView mav = cuandoBuscoElTurnoPorId(id);
//		entoncesMeLlevaAlaVistaLogin(mav);
	}

    private void entoncesMeLlevaAlaVistaLogin(ModelAndView mav) {
    	assertThat(mav.getViewName()).isEqualTo("redirect:/login");
	}

	private void dadoQueNoExistaUsuario(Long id) {
    	when(request.getSession()).thenReturn(session);
    	when(request.getSession().getAttribute("userId")).thenReturn(null);	
    	when(servicioTurnos.buscarTurnoPorId(id)).thenReturn(new Turno());		
	}

	private void dadoQueExistaElTurno(Long id) {
    	when(request.getSession()).thenReturn(session);
    	when(request.getSession().getAttribute("userId")).thenReturn(1L);	
    	when(servicioTurnos.buscarTurnoPorId(id)).thenReturn(new Turno());
    	
	}
    
//    private ModelAndView cuandoBuscoElTurnoPorId(Long id) {
//		return controladorTurnos.cancelarTurno(id,request, );
//	}
    
    private void entoncesCanceloElTurno(ModelAndView mav) {
    	assertThat(mav.getViewName()).isEqualTo("usuarioHome");
}
			
//	private void dadoQueHayTurnosParaLaFecha(String fecha) {
//		
//		List<Turno>lista=new ArrayList<>();
//		for(int i=0; i<2;i++) {
//			lista.add(new Turno(fecha));
//		}
//
//		when(servicioTurnos.buscarTurno("26-05-2022")).thenReturn(lista); //donde lista tiene 2 turnos adentro		
//	}
//	
//	private ModelAndView cuandoBuscaUnTurnoDeLaFecha(String fecha) {
//
//		return controladorTurnos.buscarTurno(fecha);
//	}
//
//	private void entoncesEncuentro(Integer cantidad, ModelAndView mav) {
//		
//		List<Turno>lista=(List<Turno>)mav.getModel().get("msg");
//		assertThat(lista).hasSize(cantidad);
//				
//	}

}
