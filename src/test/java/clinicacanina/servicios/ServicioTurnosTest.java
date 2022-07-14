package clinicacanina.servicios;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Usuario;
import clinicacanina.repositorios.RepositorioUsuario;
import org.junit.Before;
import org.junit.Test;

import clinicacanina.modelo.Turno;
import clinicacanina.repositorios.RepositorioTurnos;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class ServicioTurnosTest {

    private RepositorioTurnos repositorioTurnos;
    private ServicioTurnos servicioTurnos;
    Calendar calendar;
    private ServicioValidadorFecha servicioValidadorFecha;
    private ServicioLogin servicioLogin;
    private RepositorioUsuario repositorioUsuario;
    private ServicioMascota servicioMascota;


    @Before
    public void init() {
        servicioValidadorFecha = mock(ServicioValidadorFecha.class);
        servicioMascota = mock(ServicioMascota.class);
        repositorioTurnos = mock(RepositorioTurnos.class);
        repositorioUsuario = mock(RepositorioUsuario.class);
        servicioLogin = new ServicioLoginImpl(repositorioUsuario);
        servicioTurnos = new ServicioTurnosImpl(repositorioTurnos, servicioLogin, repositorioUsuario, servicioMascota, servicioValidadorFecha);

    }

    @Test
    public void quePuedaBuscarUnTurno() {
        Long id = 1L;
        dadoQueExisteTurno(id);
        Turno turno = cuandoBuscoUnTurno(id);
        entoncesEncuentroTurno(turno);
    }

    @Test
    public void queSePuedaCancelarUnTurno() {
        Long id = 1L;
        dadoQueExisteTurno(id);
        Boolean turnoCancelado = cuandoCanceloUnTurno(id);
        entoncesNoEncuentroTurno(turnoCancelado);
    }


    private void entoncesNoEncuentroTurno(Boolean turnoCancelado) {
        assertThat(turnoCancelado).isTrue();
    }

    private Boolean cuandoCanceloUnTurno(Long id) {
        when(repositorioTurnos.cancelarTurnoPorId(id)).thenReturn(true);
        return repositorioTurnos.cancelarTurnoPorId(id);
    }

    private void dadoQueExisteTurno(Long id) {

        when(repositorioTurnos.buscarTurnoPorId(id)).thenReturn(new Turno());
    }

    private Turno cuandoBuscoUnTurno(Long id) {
        return servicioTurnos.buscarTurnoPorId(id);
    }

    private void entoncesEncuentroTurno(Turno turno) {
        assertThat(turno).isNotNull();
    }

    @Test
    public void listarTodosLosTurnosDisponiblesEnFecha() {
        Integer cantidadTurnosEsperados = 2;
        Integer cantidadTurnos = 2;
        dadoQueExisteTurnoEnFecha("fecha", cantidadTurnos);

        List<Turno> turnosEncontrados = cuandoBuscoTodosLosTurnos("fecha");

        entoncesEncuentroTodosLosTurnos(turnosEncontrados, cantidadTurnosEsperados);
    }


    private void dadoQueExisteTurnoEnFecha(String fecha, Integer cantidadTurnos) {

        List<Turno> lista = new ArrayList<>();
        for (int i = 0; i < cantidadTurnos; i++) {
            lista.add(new Turno(fecha));
        }
        when(repositorioTurnos.mostrarTurnoDisponible(fecha)).thenReturn(lista);
    }

    private List<Turno> cuandoBuscoTodosLosTurnos(String fecha) {

        return servicioTurnos.buscarTurno(fecha);
    }


    private void entoncesEncuentroTodosLosTurnos(List<Turno> turnosEncontrados, Integer cantidadTurnosEsperados) {

        assertThat(turnosEncontrados).hasSize(cantidadTurnosEsperados);
    }

    @Test
    public void CuandoBuscoLosTurnosPorUsuarioRegresanTodosLosTurnos() {
        //preparacion
        List<Turno> lista = new ArrayList<>();
        Turno turno = new Turno();
        lista.add(turno);
        when(repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1L)).thenReturn(lista);
        // ejecucion
        List<Turno> listaEsperada = repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1L);
        //copararacion
        assertThat(lista).isEqualTo(listaEsperada);

    }

    @Test
    public void CuandoBuscoLosTurnosDelUsuarioYnoTieneRegresaListaVacia() {
        // preparacion
        List<Turno> lista = new ArrayList<>();
        when(repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1L)).thenReturn(lista);
        List listaEsperada = new ArrayList();
        // ejecucion
        listaEsperada = servicioTurnos.turnosDelUsuario(1L);
        //comparacion
        assertThat(listaEsperada.isEmpty()).isTrue();
        assertThat(listaEsperada.size()).isEqualTo(0);
        assertThat(listaEsperada).isNotNull();
    }

    @Test
    public void puedoBuscarLosTurnosDisponiblesElDiaDedeHoyNoNULL() {
        // preparacion
        List<Turno> lista = new ArrayList<>();
        Calendar fecha = new GregorianCalendar();
        when(repositorioTurnos.mostarTurnosDisponiblesFechaHoy()).thenReturn(lista);
        List<Turno> listaEsperada = new ArrayList();
        // ejecucion
        listaEsperada = servicioTurnos.buscarTurnoPorFechaDeHoy();
        // comparacion
        assertThat(listaEsperada).isNotNull();
    }

    @Test
    public void puedoTomarUnTurno() {
        // preparacion
        long idTurno = 1L;
        Usuario usuario = new Usuario();
        Mascota mascota = new Mascota();
        mascota.setUsuario(usuario);
        when(servicioLogin.consultarUsuarioPorID(1L)).thenReturn(usuario);
        when(servicioMascota.buscarMascotaPorId(1l)).thenReturn(mascota);
        when(repositorioTurnos.tomarTurno(mascota, usuario, idTurno)).thenReturn(true);
        // ejecucion
        boolean estado = servicioTurnos.tomarTurno(1L, 1L, idTurno);
        //validacion
        assertThat(estado).isTrue();

    }

    @Test
    public void sibuscarProximoTurnoLibreNoEncuentraFechasRetornaListaEmpy() {
        dadoqueNoTengoTurnosCargados();
        List<Turno> turnosEsperados = cuandoUsoBuscarProximoTurnoDisponible();
        encuentroUnaListaEmpy(turnosEsperados);

    }

    private void encuentroUnaListaEmpy(List<Turno> turnosEsperados) {
        assertThat(turnosEsperados.isEmpty()).isTrue();
        assertThat(turnosEsperados).isNotNull();
    }

    private List<Turno> cuandoUsoBuscarProximoTurnoDisponible() {
        return servicioTurnos.buscarProximosTurnos();
    }

    private void dadoqueNoTengoTurnosCargados() {
        when(repositorioTurnos.buscarProximoTurnoLibre()).thenReturn(null);
    }

    @Test
    public void cuandoBuscoPorFEchasYnoTEngoRegresaListaVacia() {
        dadoQueNoTEngoTurnosCArgados();
        List<Turno> turnosEsperados = cuandoUsoBuscarProximoTurnoDisponible();
        entoncesMeTraeUnaListaLimpia(turnosEsperados);
    }

    private void dadoQueNoTEngoTurnosCArgados() {
        Calendar calendario = Calendar.getInstance();
        Turno turno = new Turno();
        when(repositorioTurnos.buscarProximoTurnoLibre()).thenReturn(turno);
        when(repositorioTurnos.buscarTurnosPorFecha(calendario)).thenReturn(null);
    }

    private void entoncesMeTraeUnaListaLimpia(List<Turno> turnosEsperados) {
        assertThat(turnosEsperados).isNotNull();
        assertThat(turnosEsperados.isEmpty()).isTrue();
        verify(repositorioTurnos, times(1)).buscarProximoTurnoLibre();
    }

    @Test
    public void puedoTomarTurnoSoloConIdUsuario() {
        Usuario usuario1 = new Usuario();
        usuario1.setId(1L);
        Turno turno1 = new Turno();
        dadoQueTengounUsuarioYunTurno(turno1,usuario1);
        servicioTurnos.tomarTurnoUsuario(usuario1.getId(), turno1.getId());
        getEntoncesVerificoQUeSeTomeLlameLeMetodo(usuario1);

    }
    private void getEntoncesVerificoQUeSeTomeLlameLeMetodo(Usuario usuario1) {
        verify(repositorioUsuario, times(1)).consultarUsuarioPorID(usuario1.getId());
    }
    private void dadoQueTengounUsuarioYunTurno(Turno turno1, Usuario usuario1) {
        when(repositorioUsuario.consultarUsuarioPorID(usuario1.getId())).thenReturn(usuario1);
        when(repositorioTurnos.tomarTurnoUsuario(usuario1,turno1.getId())).thenReturn(true);

    }
}