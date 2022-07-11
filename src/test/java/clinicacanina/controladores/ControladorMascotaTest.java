package clinicacanina.controladores;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;
import clinicacanina.repositorios.RepositorioMascota;
import clinicacanina.servicios.ServicioLogin;
import clinicacanina.servicios.ServicioMascota;
import clinicacanina.servicios.ServicioMedico;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;

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
    HttpSession session = mock(HttpSession.class);

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



    @Test
    public void AlPedirLaListaDeMascotasMeDevuelveLaListaCompletaSiEstoyLogeado() {

        //preparacion
        when(session.getAttribute("userId")).thenReturn(1l);

        dadoQueExisteCiertaCantidadDeMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad(), historiaClinica.getSintomas(), historiaClinica.getDetalleTratamientos(), CANTIDAD_MASCOTA);


        //ejecucion
        ModelAndView mav = cuandoListoMascota();



        //validacion
        entoncesEncuentroLista(mav, 10);
        entoncesMeLlevaALaVista(VISTA_ESPERADA_LISTA, mav.getViewName());

    }

    private ModelAndView cuandoListoMascota() {

        return controladorMascota.listarMascotas(session);
    }

    @Test
    public void noPuedoVerLaListaDeAnimalesSiNoEstoyLogeadoYmeMandaALaVistaError() {

        //preparacion
        when(session.getAttribute("userId")).thenReturn(null);

        dadoQueExisteCiertaCantidadDeMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad(), historiaClinica.getSintomas(), historiaClinica.getDetalleTratamientos(), CANTIDAD_MASCOTA);

        //ejecucion
        ModelAndView mav = controladorMascota.listarMascotas(session);


        //validacion

        entoncesMeLlevaALaVista("error", mav.getViewName());

    }

    @Test
    public void IrALaHistoriaClinicaDeLaMascotaCuandoEstoyLogeado() {


        when(session.getAttribute("userId")).thenReturn(1L);

        Mascota mascota = dadoQueExisteMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad());




        //ejecucion
        ModelAndView mav = cuandoVoyAdetalle(mascota);

        //validacion
        entoncesEncuentroLaMascota(mav, mascota);
        entoncesMeLlevaALaVista("historiaClinica", mav.getViewName());


    }



    @Test
    public void IrAAgregarVisitaMedicaDeLaMascotaCuandoEstoyLogeado() {


        when(session.getAttribute("userId")).thenReturn(1L);

        Mascota mascota = dadoQueExisteMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad());

        mascota.setId(1L);

        //ejecucion
        ModelAndView mav = cuandoVoyAModificar(mascota);

        //validacion
        entoncesMeLlevaALaVista("modificarMascota", mav.getViewName());


    }




    @Test
    public void sePuedeModificarLaHistoriaClinica(){

        when(session.getAttribute("userId")).thenReturn(1L);

        Mascota mascotaBase = dadoQueExisteMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad());

        Mascota mascotaModificada = dadoQueExisteMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad());

        mascotaModificada.setId(1L);

        ModelAndView mav = cuandoModificoLaHistoriaClinica(mascotaModificada);

        ModelAndView historiaClinica  = controladorMascota.irAHistoriaClinica(mascotaModificada.getId(), session);


        entoncesSeModificoLaMascota(historiaClinica, mascotaModificada);
        entoncesMeLlevaALaVista("historiaClinica",mav.getViewName());



    }

    private void entoncesEnLaVistaSeVeLaMascotaModificada(ModelAndView mav) {

        Mascota mascotaEncontrada = (Mascota) mav.getModel().get("mascota");

        ModelAndView mavHistoriaClinica = controladorMascota.irAHistoriaClinica(1l,session);

        Mascota mascotaHistoriaClinica = (Mascota) mavHistoriaClinica.getModel().get("mascota");


        assertThat(mascotaEncontrada).isEqualTo(mascotaHistoriaClinica);







    }


    private ModelAndView cuandoVoyAdetalle(Mascota mascota) {


        return controladorMascota.irAHistoriaClinica(mascota.getId(), session);

    }

    private ModelAndView cuandoVoyAModificar(Mascota mascota) {

        when(servicioMascota.buscarMascotaPorId(mascota.getId())).thenReturn(mascota);
        return controladorMascota.irAModificarMascota(mascota.getId(), session);

    }

    private void entoncesEncuentroLaMascota(ModelAndView mav, Mascota mascota) {

        Mascota mascotaEncontrada = (Mascota) mav.getModel().get("mascota");

        assertThat(mascotaEncontrada).isEqualTo(mascota);


    }

    private void entoncesEncuentroLaMascotaAModificar(ModelAndView mav, Mascota mascota) {

        Mascota mascotaEncontrada = (Mascota) mav.getModel().get("historiaClinica");

        assertThat(mascotaEncontrada).isEqualTo(mascota);


    }


    private void entoncesSeModificoLaMascota(ModelAndView mav, Mascota mascota) {

        Mascota mascotaEncontrada = (Mascota) mav.getModel().get("mascota");


        assertThat(mascotaEncontrada).isEqualTo(mascota);


       // assertThat(mascotaEncontrada.getDetalleTratamientos()).isEqualTo("se cambiaron los sintomas");

    }

    private ModelAndView cuandoModificoLaHistoriaClinica(Mascota mascota) {
        when(servicioMascota.buscarMascotaPorId(mascota.getId())).thenReturn(mascota);

        mascota.setId(1l);

    //    mascota.setDetalleTratamientos("se cambiaron los sintomas");

        return controladorMascota.modificarHistoriaClinica(mascota, session);

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




    private Mascota dadoQueExisteMascota(String nombreMascota, Integer peso, Integer edad) {

        HistoriaClinica datosMascota = new HistoriaClinica();
        datosMascota.setEdad(edad);
        datosMascota.setNombre(nombreMascota);
        datosMascota.setPeso(peso);

        VisitaClinica visitaClinica = new VisitaClinica();

        visitaClinica.setSintomas(historiaClinica.getSintomas());
        visitaClinica.setTratamiento(historiaClinica.getDetalleTratamientos());

        Mascota mascota = new Mascota(datosMascota);
        mascota.setId(1L);

        when(servicioMascota.buscarMascotaPorId(mascota.getId())).thenReturn(mascota);



        return mascota;

        //cuando (pase todo esto) . tiene que devolver (esto)
    }



}
