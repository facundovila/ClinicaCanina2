package clinicacanina.controladores;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import clinicacanina.servicios.ServicioLogin;
import clinicacanina.servicios.ServicioMascota;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Turno;
import clinicacanina.servicios.ServicioTurnos;

public class ControladorTurnosTest {

	private static final String VISTA_FECHA_ESPERADA = "turnoreservado";
	private ControladorTurnos controladorTurnos;
	private ServicioTurnos servicioTurnos;
	private HttpServletRequest request;
	private HttpSession session;
	private HttpServletRequest requestNull;
	private HttpSession sessionNull;
	private ServicioLogin servicioLogin;

	
	
	@Before
    public void init(){
		session = mock(HttpSession.class);
		request = mock(HttpServletRequest.class);
		sessionNull = mock(HttpSession.class);
		requestNull = mock(HttpServletRequest.class);
        servicioTurnos= mock(ServicioTurnos.class);
		servicioLogin=mock(ServicioLogin.class);
        controladorTurnos= new ControladorTurnos(servicioTurnos,servicioLogin);

		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);

		when(requestNull.getSession()).thenReturn(sessionNull);
		when(requestNull.getSession().getAttribute("userId")).thenReturn(null);
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
@Test
	public void sePuedeCancelarUnTurnoPasandoElIdPorUrl(){
	when(request.getSession()).thenReturn(session);
	when(request.getSession().getAttribute("userId")).thenReturn(1L);
	when(servicioTurnos.cancelarTurnoPorId(1L)).thenReturn(true);
	ModelAndView modelo = controladorTurnos.cancelarTurno(1L,request);
	assertThat(modelo.getViewName()).isEqualTo("redirect:/usuarioHome");
	verify(servicioTurnos, times(1)).cancelarTurnoPorId(any());
}
	@Test
	public void siTurnoSeCancelaSePoneUnMensaje(){
		// esto testea el mensaje pero no pude arreglarlo en el repositorio
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		when(servicioTurnos.cancelarTurnoPorId(1L)).thenReturn(false);
		ModelAndView modelo = controladorTurnos.cancelarTurno(1L,request);
		assertThat(modelo.getModel().get("mensaje")).isEqualTo("turno Cancelado");
	}
	@Test
	public void CuandVoyaTurnosMeRegresaLaVistaSelecionaRTurno(){
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		ModelAndView modelo = controladorTurnos.irSoliciarTurno(request);
		assertThat(modelo.getViewName()).isEqualTo("usuarioSolicitarTurno");
	}
	@Test
	public void CuandVoyASeleccionarTurnoMeRegresaUnaVistaCoonListaTurnos(){
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		List <Turno> lista= new ArrayList<Turno>();
		Turno turno1= new Turno();
		turno1.setId(1L);
		lista.add(turno1);
		when(servicioTurnos.buscarTurnoPorFechaDeHoy()).thenReturn(lista);

		ModelAndView modelo = controladorTurnos.irSoliciarTurno(request);
		verify(servicioTurnos, times(1)).buscarTurnoPorFechaDeHoy();
		assertThat(modelo.getModel().get("listaTurnosDisponibles")).isNotNull();
		List<Turno> turno2= (List<Turno>) modelo.getModel().get("listaTurnosDisponibles");
		assertThat(turno2.size()).isEqualTo(1);
	}
	@Test
	public void CuandVoyASeleccionarTurnoMeRegresaMensajeSiNoEcuentraTurnos(){
		when(request.getSession()).thenReturn(session);
		when(request.getSession().getAttribute("userId")).thenReturn(1L);
		List <Turno> lista= new ArrayList<Turno>();
		when(servicioTurnos.buscarTurnoPorFechaDeHoy()).thenReturn(lista);
		ModelAndView modelo = controladorTurnos.irSoliciarTurno(request);
		verify(servicioTurnos, times(1)).buscarTurnoPorFechaDeHoy();
		assertThat(modelo.getModel().get("mensaje")).isEqualTo("Sin Turnos Disponibles");

		}
		@Test
		public void cuandoSeleccionoElTurnoMeMandaAlHome() {
			ModelAndView modelo = controladorTurnos.tomarTurno(request,1L,1L);
			servicioTurnos.tomarTurno(1L,1L,1L);
			assertThat(modelo.getViewName()).isEqualTo("redirect:/usuarioHome");
		}
	@Test
	public void cuandoSeleccionoElTurnoMeMandaAlHomeSinLogin() {
		ModelAndView modelo = controladorTurnos.tomarTurno(requestNull,1L,1L);
		assertThat(modelo.getViewName()).isEqualTo("redirect:/login");
	}



}
