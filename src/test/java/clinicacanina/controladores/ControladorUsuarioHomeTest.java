package clinicacanina.controladores;

import clinicacanina.modelo.Turno;
import clinicacanina.modelo.Usuario;
import clinicacanina.servicios.ServicioTurnos;
import org.dom4j.rule.Mode;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;


import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ControladorUsuarioHomeTest {

    ControladorUsuarioHome controladorUsuarioHome;
    ServicioTurnos servicioTurnos;


    HttpServletRequest request;
    HttpSession session;

    /* para mockear la session y obtener el id se usan las dos lineas de arriba y se agregan estos mock en los test
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("userId")).thenReturn(1L);
     */

    @Before
    public void init(){
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        servicioTurnos=mock(ServicioTurnos.class);
        controladorUsuarioHome= new ControladorUsuarioHome(servicioTurnos);

    }
    @Test
    public void CuadnoElUsuarioVaAlHomeLogeadoMELLevaALavistaUsuarioHome(){
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("userId")).thenReturn(1L);
        ModelAndView modelAndView= controladorUsuarioHome.usuarioHome(request);
        assertThat(modelAndView.getViewName()).isEqualTo("usuarioHome");
    }

    @Test
    public void CuandoIngresoAlHomeSeMuestranLosTurnosDelUsuario(){
       // preparacion
        CuandoCargoUnUsuarioYLECArgoUnTurno();
        // ejecucion
        ModelAndView modelAndView = controladorUsuarioHome.usuarioHome(request);
        // comparacion
        entoncesLaVistaCargaUnaListaDeTUrnos(modelAndView);
    }

    private void entoncesLaVistaCargaUnaListaDeTUrnos(ModelAndView modelAndView) {
        assertThat(modelAndView.getModel().get("listaTurnosUsuario")).isNotNull();
    }

    private void CuandoCargoUnUsuarioYLECArgoUnTurno() {
        Usuario usuari1= new Usuario();
        usuari1.setId(1L);
        List<Turno> listaTurnos= new ArrayList<>();
        Turno turno=new Turno();
        turno.setId(1L);
        listaTurnos.add(turno);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("userId")).thenReturn(1L);
        Mockito.when(servicioTurnos.turnosDelUsuario(1L)).thenReturn(listaTurnos);
    }

}
