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
    public static final String VISTA_ESPERADA_LISTA = "listaMascotas";
    public static final String MENSAJE_TIPO_INVALIDO = "Lista de animal invalida";
    private HistoriaClinica historiaClinica ;
    private String nombreMascota;
    private Integer peso;
    private Integer edad;
    private String tratamiento;
    private String medicamentos;


    @Before
    public void init(){
        servicioMascota= mock(ServicioMascota.class);
        controladorMascota= new ControladorMascota(servicioMascota);

        historiaClinica = new HistoriaClinica();
        historiaClinica.setDetalleTratamientos("tratamientos");
        historiaClinica.setEdad(15);
        historiaClinica.setNombre(NOMBRE_MASCOTA);
        historiaClinica.setPeso(PESO);
        historiaClinica.setSintomas("medicamentos");
    }





    @Test
    public void AlPedirLaListaDeMascotasMeDevuelveLaListaCompleta(){ //ademas puede servir para listar sintomas

        //preparacion
        dadoQueExisteCiertaCantidadDeMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad(), historiaClinica.getSintomas(),  historiaClinica.getDetalleTratamientos(), CANTIDAD_MASCOTA);



        //ejecucion
        ModelAndView mav = cuandoBuscoMascota();


        //validacion
        entoncesEncuentroLista(mav, 10);
        entoncesMeLlevaALaVista(VISTA_ESPERADA_LISTA, mav.getViewName());

    }


    @Test
    public void LlevarALaHistoriaClinicaDeLaMascota(){

        //preparacion
        Mascota mascota = dadoQueExisteMascota(historiaClinica.getNombre(), historiaClinica.getPeso(), historiaClinica.getEdad(), historiaClinica.getSintomas(),  historiaClinica.getDetalleTratamientos());



        //ejecucion
        ModelAndView mav= cuandoVoyAdetalle(mascota.getId());

        //validacion
        entoncesEncuentroDetalleDeMascota(mav);
        entoncesMeLlevaALaVista("historiaClinica", mav.getViewName());


    }







    private void entoncesEncuentroDetalleDeMascota(ModelAndView mav) {

        assertThat(mav.getModel().get("historiaclinica")).isNotNull();

    }

    private ModelAndView cuandoVoyAdetalle(Long idMascota) {

       return controladorMascota.irAHistoriaClinica(idMascota);

    }


//    @Test
//    public void alPedirUnaListaInvalidaLlevaAPantallaDeError(){
//
//        String nombreInvalido= "numeros";
//        Integer pesoInvalido= 888;
//        Integer edadInvalida= 888;
//
//        when(servicioMascota.buscarMascota(nombreInvalido,pesoInvalido, edadInvalida)).thenThrow(new RuntimeException());
//
//        ModelAndView mav = cuandoBuscoMascota(nombreInvalido,pesoInvalido);
//
//
//        //validacion
//
//        entoncesMeLlevaALaVista(VISTA_ESPERADA_LISTA, mav.getViewName());
//
//        entoncesRecibeMensaje(MENSAJE_TIPO_INVALIDO, mav.getModel());
//    }


    private void entoncesRecibeMensaje(String mensajeInvalidoEsperado, Map<String, Object> model){

        assertThat(model.get("msg-error")).isEqualTo(mensajeInvalidoEsperado);

    }

    private void entoncesEncuentroLista(ModelAndView mav, int cantidadEsperada) {
        List<Mascota> lista = (List<Mascota>) mav.getModel().get("listarmascotas");

        assertThat(lista).hasSize(cantidadEsperada);

    }

    private ModelAndView cuandoBuscoMascota() {
        return controladorMascota.listarMascotas();
    }


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

        return mascota;

        //cuando (pase todo esto) . tiene que devolver (esto)
    }



}
