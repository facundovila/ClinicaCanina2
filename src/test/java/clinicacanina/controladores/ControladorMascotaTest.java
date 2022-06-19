package clinicacanina.controladores;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Medico;
import clinicacanina.repositorios.RepositorioMascota;
import clinicacanina.repositorios.RepositorioMascotaImpl;
import clinicacanina.servicios.ServicioLogin;
import clinicacanina.servicios.ServicioMascota;
import clinicacanina.servicios.ServicioMedico;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ControladorMascotaTest {

    private ControladorMascota controladorMascota;
    private ServicioMascota servicioMascota;
    private ServicioMedico servicioMedico;
    public static final String NOMBRE_MASCOTA = "goten";
    public static final Integer PESO = 50;
    public static final String VISTA_ESPERADA_DETALLE = "detalle-mascota";
    public static final Integer CANTIDAD_MASCOTA = 10;
    public static final String VISTA_ESPERADA_LISTA = "listaMascotas";
    public static final String MENSAJE_TIPO_INVALIDO = "Lista de animal invalida";
    private HistoriaClinica historiaClinica;
    private RepositorioMascota repositorioMascota;
    private ControladorLogin controladorLogin;
    private  ServicioLogin servicioLogin;

    //private HttpSession session = mock(HttpSession.class);
    //  HttpServletRequest request = mock(HttpServletRequest.class);
    @Before
    public void init() {
        repositorioMascota = mock(RepositorioMascota.class);
        servicioMascota = mock(ServicioMascota.class);
        servicioMedico = mock(ServicioMedico.class);
        controladorLogin = new ControladorLogin(servicioLogin);
        controladorMascota = new ControladorMascota(servicioMascota,servicioMedico);

        historiaClinica = new HistoriaClinica();
        historiaClinica.setDetalleTratamientos("tratamientos");
        historiaClinica.setEdad(15);
        historiaClinica.setNombre(NOMBRE_MASCOTA);
        historiaClinica.setPeso(PESO);
        historiaClinica.setSintomas("medicamentos");
    }
/* esto lo comento porque va a estar en e el serviciode Login
    @Test
    public void queMePermitaLoguearUnUsuario() {

        DatosLogin datosLogin = new DatosLogin();
        datosLogin.setContrasenia("admin");
        datosLogin.setDni(10);

       Medico medico= new Medico();
        medico.setId(1l);
        medico.setContrasenia(datosLogin.getContrasenia());
        medico.setDni(datosLogin.getDni());

      Medico medicomock = mock(Medico.class);
        when(servicioMedico.buscarMedicoLogin(datosLogin.getDni(), datosLogin.getContrasenia())).thenReturn(medicomock);// mock el medico usuario
      //  when(servicioMedico.getMedico(medico.getId())).thenReturn(medico);

        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
//
  //       when(request.getSession().getAttribute("usuarioId")).thenReturn(medico.getId());
     //   when(request.getSession().getAttribute("usuarioId")).thenReturn(1l);


        ModelAndView mav = controladorLogin.validarLogin(datosLogin, request);


        entoncesMeLlevaALaVista(VISTA_ESPERADA_LISTA, mav.getViewName());

    }*/



    @Test
    public void AlPedirLaListaDeMascotasMeDevuelveLaListaCompletaSiEstoyLogeado() {

        //preparacion
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("usuarioId")).thenReturn(1L);
        dadoQueExisteCiertaCantidadDeMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad(), historiaClinica.getSintomas(), historiaClinica.getDetalleTratamientos(), CANTIDAD_MASCOTA);
//        Medico medico = new Medico();
//        medico.setId(1l);

        //ejecucion
        ModelAndView mav = controladorMascota.listarMascotas(request);


        //validacion
        entoncesEncuentroLista(mav, 10);
        entoncesMeLlevaALaVista(VISTA_ESPERADA_LISTA, mav.getViewName());

    }


    @Test
    public void IrALaHistoriaClinicaDeLaMascotaCuandoEstoyLogeado() {

        //preparacion
        Mascota mascota = dadoQueExisteMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad(), historiaClinica.getSintomas(), historiaClinica.getDetalleTratamientos());

        mascota.setId(1L);

        HttpSession session = mock(HttpSession.class);
        when(session.getAttribute("usuarioId")).thenReturn(1L);

        //ejecucion
        ModelAndView mav = cuandoVoyAdetalle(mascota.getId(), session);

        //validacion
        // entoncesEncuentroDetalleDeMascota(mav);
        entoncesMeLlevaALaVista("historiaClinica", mav.getViewName());


    }

    @Test
    public void noPuedoVerLaListaDeAnimalesSiNoEstoyLogeadoYmeMandaALaVistaError() {

        //preparacion
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("usuarioId")).thenReturn(null);
        dadoQueExisteCiertaCantidadDeMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad(), historiaClinica.getSintomas(), historiaClinica.getDetalleTratamientos(), CANTIDAD_MASCOTA);

        //ejecucion
        ModelAndView mav = controladorMascota.listarMascotas(request);


        //validacion

        entoncesMeLlevaALaVista("error", mav.getViewName());



    }





    private ModelAndView cuandoVoyAdetalle(Long idMascota, HttpSession session) {

        return controladorMascota.irAHistoriaClinica(idMascota, session);

    }




    private void entoncesEncuentroLista(ModelAndView mav, int cantidadEsperada) {
        List<Mascota> lista = (List<Mascota>) mav.getModel().get("listarmascotas");

        assertThat(lista).hasSize(cantidadEsperada);

    }

//    private ModelAndView cuandoBuscoMascota() {
//
//         return controladorMascota.listarMascotas(request);
//    }


    private void dadoQueExisteCiertaCantidadDeMascota(String nombreMascota, Integer peso, Integer edad, String medicamentos, String tratamientos, Integer cantidadMascota) {
        List<Mascota> lista= new LinkedList<>();
        HistoriaClinica historia = new HistoriaClinica();
        historia.setDetalleTratamientos(tratamientos);
        historia.setEdad(edad);
        historia.setNombre(nombreMascota);
        historia.setPeso(peso);
        historia.setSintomas(medicamentos);
        for(int i=0; i<cantidadMascota; i++){
               lista.add(new Mascota(historia));
       }

        when(servicioMascota.listarMascotas()).thenReturn(lista);

    }


    private void entoncesMeLlevaALaVista(String vistaEsperada, String vistaRecibida) {

        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }


    private void entoncesEncuentro(ModelAndView mav) {

        Mascota mascota = (Mascota) mav.getModel().get("mascota");
        assertThat(mascota).isNotNull();
    }




    private Mascota dadoQueExisteMascota(String nombreMascota, Integer peso, Integer edad, String tratamiento, String medicamentos) {

        HistoriaClinica historiaClinica = new HistoriaClinica();
        historiaClinica.setDetalleTratamientos(tratamiento);
        historiaClinica.setEdad(edad);
        historiaClinica.setNombre(nombreMascota);
        historiaClinica.setPeso(peso);
        historiaClinica.setSintomas(medicamentos);

        Mascota mascota = new Mascota(historiaClinica);


        when(servicioMascota.crearMascota(historiaClinica)).thenReturn( mascota );
        mascota.setId(1l);
        when(repositorioMascota.buscarPorId(mascota.getId())).thenReturn(mascota);


        return mascota;

        //cuando (pase todo esto) . tiene que devolver (esto)
    }



}
