package clinicacanina.controladores;

import clinicacanina.modelo.Usuario;
import clinicacanina.servicios.ServicioLogin;
import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ControladorLoginTest {

    private static final String MENSAJE_USUARIO_O_CLAVE_INCORRECTA = "Usuario o clave incorrecta";

    private DatosLogin datosLogin;
    private ServicioLogin servicioLogin;
    private ControladorLogin controladorLogin;

    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void setUp() {
        datosLogin = mock(DatosLogin.class);
        servicioLogin = mock(ServicioLogin.class);
        controladorLogin = new ControladorLogin(servicioLogin);
        request = mock(HttpServletRequest.class);
        session = mock(HttpSession.class);
    }

    @Test
    public void testIrAlLoginMeLlevaALaVistaDelLogin() {

        // Given
        ModelMap modelo = new ModelMap();
        modelo.put("login", datosLogin);
        ModelAndView vistaEsperada = new ModelAndView("login", modelo);
        // When
        ModelAndView vistaObtenida = controladorLogin.irALogin(session);

        // Then
        assertThat(vistaEsperada.getViewName()).isEqualTo(vistaObtenida.getViewName());
        assertThat(vistaEsperada.getModel()).isInstanceOf(vistaObtenida.getModel().getClass());
    }

    @Test
    public void testValidarLoginSiElUsuarioYLaContraseniaSonValidos() {
        //Given
        ModelAndView vistaEsperada = new ModelAndView("redirect:/usuarioHome");

        // When
        when(request.getSession()).thenReturn(session);
        when(datosLogin.getEmail()).thenReturn("");
        when(datosLogin.getPassword()).thenReturn("");
        when(servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword())).thenReturn(new Usuario());
        ModelAndView vistaObtenida = controladorLogin.validarLogin(datosLogin, request);

        // Then
        assertThat(vistaObtenida.getViewName()).isEqualTo(vistaEsperada.getViewName());
    }

    @Test
    public void testValidarLoginSiElUsuarioYLaContraseniaNoSonValidos() {
        // Given
        ModelMap modeloEsperado = new ModelMap();
        modeloEsperado.put("error", MENSAJE_USUARIO_O_CLAVE_INCORRECTA);
        ModelAndView vistaEsperada = new ModelAndView("login", modeloEsperado);

        // Then
        when(datosLogin.getEmail()).thenReturn("");
        when(datosLogin.getPassword()).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword())).thenReturn(null);
        ModelAndView vistaObtenida = controladorLogin.validarLogin(datosLogin, request);

        // When
        assertThat(vistaObtenida.getViewName()).isEqualTo(vistaEsperada.getViewName());
        assertThat(vistaObtenida.getModel().get("error")).isEqualTo(MENSAJE_USUARIO_O_CLAVE_INCORRECTA);
    }


    @Test
    public void testMeLlevaALaVistaDelLogin() {
        // Given
        ModelAndView modeloEsperado = new ModelAndView("login");
        // When
        ModelAndView modeloObtenido = controladorLogin.irALogin(session);
        // Then
        assertThat(modeloEsperado.getViewName()).isEqualTo(modeloObtenido.getViewName());
    }

}