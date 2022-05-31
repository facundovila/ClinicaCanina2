package clinicacanina.controladores;

import clinicacanina.modelo.Mascota;
import clinicacanina.servicios.ServicioMascota;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

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
    public static final String NOMBRE_MASCOTA = "goten";
    public static final Integer PESO = 50;
    public static final String VISTA_ESPERADA_DETALLE = "detalle-mascota";
    public static final Integer CANTIDAD_MASCOTA= 10;
    public static final String VISTA_ESPERADA_LISTA = "listaDeMascotas";
    public static final String MENSAJE_TIPO_INVALIDO = "Lista de animal invalida";

    @Before
    public void init(){
        servicioMascota= mock(ServicioMascota.class);
        controladorMascota= new ControladorMascota(servicioMascota);
    }



    @Test
    public void mostrarMascotaDetalle() { //crea un objeto mascota nuevo y lo lleva a su pantalla de descripcion.
        //preparacion
        dadoQueExisteMascota(NOMBRE_MASCOTA, PESO,15);

        ModelAndView mav = cuandoCreoMascota(NOMBRE_MASCOTA, PESO,15);

        //ejecucion

        entoncesEncuentro(mav);

        // validacion
        entoncesMeLlevaALaVista(VISTA_ESPERADA_DETALLE, mav.getViewName());

    }

    @Test
    public void AlPedirLaListaDeMascotasMeDevuelveLaListaCompleta(){ //ademas puede servir para listar sintomas

        //preparacion
        dadoQueExisteCiertaCantidadDeMascota(NOMBRE_MASCOTA, PESO, CANTIDAD_MASCOTA,15);

        ModelAndView mav = cuandoBuscoMascota(NOMBRE_MASCOTA, PESO);

        //ejecucion

        entoncesEncuentroLista(mav, 10);

        //validacion

        entoncesMeLlevaALaVista(VISTA_ESPERADA_LISTA, mav.getViewName());

    }


    @Test
    public void alPedirUnaListaInvalidaLlevaAPantallaDeError(){

        String nombreInvalido= "numeros";
        Integer pesoInvalido= 888;
        Integer edadInvalida= 888;

        when(servicioMascota.buscarMascota(nombreInvalido,pesoInvalido, edadInvalida)).thenThrow(new RuntimeException());

        ModelAndView mav = cuandoBuscoMascota(nombreInvalido,pesoInvalido);


        //validacion

        entoncesMeLlevaALaVista(VISTA_ESPERADA_LISTA, mav.getViewName());

        entoncesRecibeMensaje(MENSAJE_TIPO_INVALIDO, mav.getModel());
    }


    private void entoncesRecibeMensaje(String mensajeInvalidoEsperado, Map<String, Object> model){

        assertThat(model.get("msg-error")).isEqualTo(mensajeInvalidoEsperado);

    }

    private void entoncesEncuentroLista(ModelAndView mav, int cantidadEsperada) {
        List<Mascota> lista = (List<Mascota>) mav.getModel().get("listaDeMascotas");

        assertThat(lista).hasSize(cantidadEsperada);

    }

    private ModelAndView cuandoBuscoMascota(String nombreMascota, Integer peso) {
        return controladorMascota.listarMascotas();
    }


    private void dadoQueExisteCiertaCantidadDeMascota(String nombreMascota, Integer peso, Integer cantidadMascota, Integer edad) {
        List<Mascota> lista= new LinkedList<>();

        for(int i=0; i<cantidadMascota; i++){
               lista.add(new Mascota(nombreMascota, peso, edad));
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

    private ModelAndView cuandoCreoMascota(String nombreMascota, Integer peso, Integer edad) {

        return controladorMascota.mostrarMascotaCreada(nombreMascota,peso,edad);
    }

    private void dadoQueExisteMascota(String nombreMascota, Integer peso, Integer edad) {

        when(servicioMascota.crearMascota(nombreMascota, peso, edad)).thenReturn(new Mascota(nombreMascota,peso, edad));
        //cuando (pase todo esto) . tiene que devolver (esto)
    }





}
