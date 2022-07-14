package clinicacanina.controladores;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;
import clinicacanina.repositorios.RepositorioMascota;
import clinicacanina.servicios.*;
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
    public static final Float PESO = 50F;
    public static final String VISTA_ESPERADA_DETALLE = "detalle-mascota";
    public static final Integer CANTIDAD_MASCOTA = 10;
    public static final String VISTA_ESPERADA_LISTA = "listaMascotas";
    public static final String MENSAJE_TIPO_INVALIDO = "Lista de animal invalida";
    private HistoriaClinica historiaClinica;
    private RepositorioMascota repositorioMascota;
    private ControladorLogin controladorLogin;
    private  ServicioLogin servicioLogin;
    HttpSession session = mock(HttpSession.class);



    @Before
    public void init() {
        repositorioMascota = mock(RepositorioMascota.class);
        servicioMascota = mock(ServicioMascota.class);
        servicioMedico = mock(ServicioMedico.class);
        controladorLogin = new ControladorLogin(servicioLogin);
        controladorMascota = new ControladorMascota(servicioMascota,servicioMedico);



        historiaClinica = new HistoriaClinica();

        historiaClinica.setEdad(15);
        historiaClinica.setNombre(NOMBRE_MASCOTA);
        historiaClinica.setPeso(PESO);


    }



    @Test
    public void AlPedirLaListaDeMascotasMeDevuelveLaListaCompletaSiEstoyLogeado() {

        //preparacion
        when(session.getAttribute("userId")).thenReturn(1l);

        dadoQueExisteCiertaCantidadDeMascota(CANTIDAD_MASCOTA);


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

        dadoQueExisteCiertaCantidadDeMascota( CANTIDAD_MASCOTA);

        //ejecucion
        ModelAndView mav = controladorMascota.listarMascotas(session);


        //validacion

        entoncesMeLlevaALaVista("error", mav.getViewName());

    }

    @Test
    public void IrALaHistoriaClinicaDeLaMascotaCuandoEstoyLogeado() {


        when(session.getAttribute("userId")).thenReturn(1L);

        Mascota mascota = dadoQueExisteMascota();

        //ejecucion
        ModelAndView mav = cuandoVoyAdetalle(mascota);

        //validacion
        entoncesEncuentroLaMascota(mav, mascota);
        entoncesMeLlevaALaVista("historiaClinica", mav.getViewName());


    }



    @Test
    public void IrAAgregarVisitaMedicaDeLaMascotaCuandoEstoyLogeado() {


        when(session.getAttribute("userId")).thenReturn(1L);

        Mascota mascota = dadoQueExisteMascota();

        mascota.setId(1L);

        //ejecucion
        ModelAndView mav = cuandoVoyAAgregarVisitas(mascota);

        //validacion
        entoncesMeLlevaALaVista("modificarMascota", mav.getViewName());



    }




    @Test
    public void sePuedeAgregarVisitasMedicasAUnaMascotaYMeLlevaALaVistaHistoriaClinica(){
        when(session.getAttribute("userId")).thenReturn(1L);

        Mascota mascota = dadoQueExisteMascota();
        VisitaClinica nuevaVisita = dadoQueCreoVisitaMedica(mascota);

        ModelAndView mav = cuandoAgregoVisitaMedica(mascota, nuevaVisita);

        entoncesMeLlevaALaVista("redirect:/historia-clinica?idMascota="+mascota.getId(), mav.getViewName());




    }

    @Test
    public void siLaEdadDeLaMascotaEsIncorrectaMeLlevaALaVistaDeError(){
        when(session.getAttribute("userId")).thenReturn(1L);

        Mascota mascota = dadoQueExisteMascota();
        VisitaClinica nuevaVisita = dadoQueCreoVisitaMedica(mascota);
        nuevaVisita.setEdad(156);



        ModelAndView mav = cuandoAgregoVisitaMedica(mascota, nuevaVisita);



        entoncesMeLlevaALaVista("error", mav.getViewName());




    }


    private Mascota dadoQueExisteMascota() {

        Mascota mascota = new Mascota();
        mascota.setId(1l);
        mascota.setNombre("hachi");

        when(servicioMascota.buscarMascotaPorId(mascota.getId())).thenReturn(mascota);



        return  mascota;

    }

    private VisitaClinica dadoQueCreoVisitaMedica(Mascota mascota) {

        VisitaClinica nuevaVisita = new VisitaClinica();
        nuevaVisita.setTratamiento("reposo");
        nuevaVisita.setSintomas("fiebre");
        nuevaVisita.setEdad(15);
        nuevaVisita.setId(5l);

        when(servicioMascota.modificarMascota(mascota.getId(),mascota.getEdad(), mascota.getPeso())).
                thenReturn(mascota);

        when(servicioMascota.guardarVisitaMedicaDeMascota(mascota.getId(), nuevaVisita)).
                thenReturn(nuevaVisita.getId());

        return nuevaVisita;


    }

    private ModelAndView cuandoAgregoVisitaMedica(Mascota mascota, VisitaClinica nuevaVisita) {
        nuevaVisita.setMascotaAsignada(mascota);

        return controladorMascota.agregarVisitaMedica(nuevaVisita, session);
    }





    private ModelAndView cuandoVoyAdetalle(Mascota mascota) {


        return controladorMascota.irAHistoriaClinica(mascota.getId(), session);

    }

    private ModelAndView cuandoVoyAAgregarVisitas(Mascota mascota) {

        when(servicioMascota.buscarMascotaPorId(mascota.getId())).thenReturn(mascota);
        return controladorMascota.agregarVisitas(mascota.getId(), session);

    }

    private void entoncesEncuentroLaMascota(ModelAndView mav, Mascota mascota) {

        Mascota mascotaEncontrada = (Mascota) mav.getModel().get("mascota");

        assertThat(mascotaEncontrada).isEqualTo(mascota);


    }





    private void entoncesEncuentroLista(ModelAndView mav, int cantidadEsperada) {
        List<Mascota> lista = (List<Mascota>) mav.getModel().get("listarmascotas");

        assertThat(lista).hasSize(cantidadEsperada);

    }



    private void dadoQueExisteCiertaCantidadDeMascota(Integer cantidadMascota) {
        List<Mascota> lista= new LinkedList<>();


        Mascota mascota = new Mascota();
        mascota.setId(1l);
        mascota.setNombre("hachi");
        mascota.setEdad(10);
        mascota.setPeso(15f);




        for(int i=0; i<cantidadMascota; i++){
               lista.add(new Mascota());
       }

        when(servicioMascota.listarMascotas()).thenReturn(lista);

    }


    private void entoncesMeLlevaALaVista(String vistaEsperada, String vistaRecibida) {

        assertThat(vistaRecibida).isEqualTo(vistaEsperada);
    }







}
