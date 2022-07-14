package clinicacanina.repositorios;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Medico;
import clinicacanina.modelo.Usuario;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Turno;

public class RepositorioTurnosTest extends SpringTest {

    @Autowired
    private RepositorioTurnos repositorioTurnos;

    @Test
    @Transactional
    @Rollback
    public void guardarUnTurnoDeberiaPersistirlo() {
        //preparacion
        Turno turno = dadoQueExisteTurno("fecha");
        //ejecucion
        String fecha = cuandoGuardoTurno(turno);
        //validacion
        entoncesEncuentroTurno(fecha);
    }

    @Test
    @Transactional
    @Rollback
    public void creaUnTurnoYLoBuscaPorFecha() {

        Turno turno1 = dadoQueExisteTurno("fecha1");
        Turno turno2 = dadoQueExisteTurno("fecha1");
        Turno turno3 = dadoQueExisteTurno("fecha3");
        cuandoGuardoTurno(turno1);
        cuandoGuardoTurno(turno2);
        cuandoGuardoTurno(turno3);
        List<Turno> turnoBuscado = cuandoBuscoTurnoPorFecha("fecha1");
        Integer cantidadEsperada = 2;
        entoncesEncuentroTurno(turnoBuscado, cantidadEsperada);
    }

    private void entoncesEncuentroTurno(List<Turno> turnoBuscado, Integer cantidadEsperada) {
        // TODO Auto-generated method stub
        assertThat(turnoBuscado).hasSize(cantidadEsperada);
    }

    private List<Turno> cuandoBuscoTurnoPorFecha(String fecha) {
        return repositorioTurnos.buscarPorFecha(fecha);
    }

    private void entoncesEncuentroTurno(String fecha) {
        List<Turno> turno = repositorioTurnos.buscarPorFecha(fecha);
        assertThat(turno).isNotNull();
    }

    private String cuandoGuardoTurno(Turno turno) {
        turno.setEstado(true);
        repositorioTurnos.guardarTurno(turno);
        return turno.getFecha();
    }

    private Turno dadoQueExisteTurno(String fecha) {
        Turno turno = new Turno();
        turno.setFecha(fecha);
        turno.setEstado(false);
        return turno;
    }


    @Test
    @Transactional
    @Rollback
    public void puedoBuscarLosTurnosPorelIdDelUsuario() {
        List<Turno> lista = repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1l);
        EntoncesLaListaExisteYEstaEmpy(lista);
    }

    private void EntoncesLaListaExisteYEstaEmpy(List<Turno> lista) {
        assertThat(lista.isEmpty()).isTrue();
    }

    @Test
    @Transactional
    @Rollback
    public void GuardoTurnosYBuscoPorIdDeUnUsuarioRegresaListaDETurnos() {
        Usuario usuario1 = CuandoCArgoDosTurnosAUnUsuario();
        List<Turno> lista = cuandoBuscoAlUsuarioConDosTurnos(usuario1.getId());
        testeoQueREgresenDosTurnos(lista);
    }

    private void testeoQueREgresenDosTurnos(List<Turno> lista) {
        assertThat(lista.size()).isEqualTo(2);
    }

    private Usuario CuandoCArgoDosTurnosAUnUsuario() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        session().save(usuario1);
        session().save(usuario2);
        Turno turno1 = new Turno();
        turno1.setUsuario(usuario1);
        Turno turno2 = new Turno();
        turno2.setUsuario(usuario2);
        Turno turno3 = new Turno();
        turno3.setUsuario(usuario1);
        session().save(turno1);
        session().save(turno2);
        session().save(turno3);
        return usuario1;
    }

    private List<Turno> cuandoBuscoAlUsuarioConDosTurnos(long idUsuario) {
        return repositorioTurnos.mostarTodosTurnosDelUsuario(idUsuario);
    }


    @Test
    @Transactional
    @Rollback
    public void puedoBuscarElPrimerTurnoLibre() {
        Turno turnoParaComparar = dadoQueCargoVariosTurnos();
        Turno turnoproximo = cuandoBuscoelTurnoMasProximo();
        encuentroSoloUnTurno(turnoproximo, turnoParaComparar);
    }

    private void encuentroSoloUnTurno(Turno turnoproximo, Turno turnoParaComparar) {
        assertThat(turnoproximo.getFechaTurno().get(Calendar.YEAR)).isEqualTo(turnoParaComparar.getFechaTurno().get(Calendar.YEAR));
        assertThat(turnoproximo.getEstado()).isTrue();
        assertThat(turnoproximo.getId()).isEqualTo(turnoParaComparar.getId());
    }

    private Turno cuandoBuscoelTurnoMasProximo() {
        return repositorioTurnos.buscarProximoTurnoLibre();
    }

    private Turno dadoQueCargoVariosTurnos() {
        Turno turno1 = new Turno();
        Turno turno2 = new Turno();
        Turno turno3 = new Turno();
        Turno turno4 = new Turno();
        turno1.setEstado(true);
        turno2.setEstado(true);
        turno3.setEstado(true);
        turno4.setEstado(true);
        turno1.setFechaTurno(new GregorianCalendar(2022, 01, 01, 10, 00));
        Calendar calenda = Calendar.getInstance();
        int anio = calenda.get(Calendar.YEAR);
        anio += 1;
        turno2.setFechaTurno(new GregorianCalendar(anio, 01, 01, 10, 00));
        anio += 1;
        turno3.setFechaTurno(new GregorianCalendar(anio, 01, 01, 10, 00));
        anio += 1;
        turno4.setFechaTurno(new GregorianCalendar(anio, 01, 01, 10, 00));
        session().save(turno1);
        session().save(turno2);
        session().save(turno3);
        session().save(turno4);
        return turno2;
    }

    @Test
    @Transactional
    @Rollback
    public void puedoBuscarLosTurnosPorFecha() {
        Calendar calendario = new GregorianCalendar(2023, 1, 10, 10, 00);
        dadoQueCargoVariosTurnosDisponibles(calendario);
        List<Turno> retorno = cuandoBuscoTurnosPorFechaPasandoUnCalendarMeRegresaUnaListaDeTurnosDeLaFecha(calendario);
        entoncesEncuentroTurnosConLasMismasFechas(calendario, retorno);
    }

    private void entoncesEncuentroTurnosConLasMismasFechas(Calendar calendario, List<Turno> retorno) {
        assertThat(retorno.size()).isEqualTo(3);
    }

    private List<Turno> cuandoBuscoTurnosPorFechaPasandoUnCalendarMeRegresaUnaListaDeTurnosDeLaFecha(Calendar calendario) {
        return repositorioTurnos.buscarTurnosPorFecha(calendario);
    }

    private void dadoQueCargoVariosTurnosDisponibles(Calendar calendario) {
        Turno turno1 = new Turno();
        Turno turno2 = new Turno();
        Turno turno3 = new Turno();
        Turno turno4 = new Turno();
        turno1.setEstado(true);
        turno2.setEstado(true);
        turno3.setEstado(true);
        turno4.setEstado(true);
        turno1.setFechaTurno(new GregorianCalendar(2020, 1, 1, 10, 00));
        turno2.setFechaTurno(calendario);
        turno3.setFechaTurno(calendario);
        turno4.setFechaTurno(new GregorianCalendar(2030, 1, 1, 10, 00));
        session().save(turno1);
        session().save(turno2);
        session().save(turno3);
        session().save(turno4);
    }


    @Test
    @Transactional
    @Rollback
    public void puedoCancelarTurnoSinModificarFechaNiMedico() {

        Turno turno = CuandoCargoUnTurnoAUnUsuario();
        alCancelarElturnoDelUsuario(turno.getId());
        entoncesBuscoElTurnoYMeMuestraSoloIdFechaYmeDico(turno.getId());
    }

    private void entoncesBuscoElTurnoYMeMuestraSoloIdFechaYmeDico(Long idTurno) {
        Turno turnobuscado = repositorioTurnos.buscarTurnoPorId(idTurno);
        assertThat(turnobuscado.getFechaTurno()).isNotNull();
        assertThat(turnobuscado.getEstado()).isTrue();
        //assertThat(turnobuscado.getMascota()).isNull();
        //assertThat(turnobuscado.getUsuario()).isNull();
    }

    private void alCancelarElturnoDelUsuario(Long idTurno) {
        repositorioTurnos.cancelarTurnoPorId(idTurno);
    }

    private Turno CuandoCargoUnTurnoAUnUsuario() {
        Calendar calendario = new GregorianCalendar(2022, 06, 06, 10, 10, 10);
        Usuario usuario1 = new Usuario();
        session().save(usuario1);

        Mascota mascota1 = new Mascota();
        mascota1.setUsuario(usuario1);
        session().save(mascota1);

        Medico medico = new Medico();
        session().save(medico);

        Turno turno1 = new Turno();
        turno1.setUsuario(usuario1);
        turno1.setMedico(medico);
        turno1.setMascota(mascota1);
        turno1.setEstado(true);
        turno1.setFechaTurno(calendario);
        session().save(turno1);
        return turno1;
    }
}
