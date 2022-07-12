package clinicacanina.repositorios;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.Medico;
import clinicacanina.modelo.Usuario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import clinicacanina.SpringTest;
import clinicacanina.modelo.Turno;

public class RepositorioTurnosTest extends SpringTest {

	@Autowired
	private RepositorioTurnos repositorioTurnos;

	private Turno turno1= new Turno();
	private Turno turno2= new Turno();
	private Turno turno3= new Turno();
	private  Turno turno4= new Turno();



	@Test @Transactional @Rollback
	public void guardarUnTurnoDeberiaPersistirlo() {
		//preparacion
		Turno turno = dadoQueExisteTurno("fecha");
		//ejecucion
		String fecha = cuandoGuardoTurno(turno);
		//validacion
		entoncesEncuentroTurno(fecha);
	}

	@Test @Transactional @Rollback
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


	@Test @Transactional @Rollback
	public void puedoBuscarLosTurnosPorelIdDelUsuario() {
		List <Turno> lista= new ArrayList<>();
		lista= repositorioTurnos.mostrarTurnoUsuarioDesdeHoy(1l);
		assertThat(lista.isEmpty()).isTrue();
	}
	@Test @Transactional @Rollback
	public void GuardoTurnosYBuscoPorIdDeUnUsuarioRegresaListaDETurnos(){
		long idUsuario=1L;
		CuandoCArgoDosTurnosAUnUsuario(idUsuario);
		List<Turno> lista = cuandoBuscoAlUsuarioConDosTurnos(idUsuario);
		testeoQueREgresenDosTurnos(lista);
	}

	private void testeoQueREgresenDosTurnos(List<Turno> lista) {
		assertThat(lista.size()).isEqualTo(2);
	}

	private void CuandoCArgoDosTurnosAUnUsuario(long idUsuario) {
		Usuario usuario1= new Usuario();
		usuario1.setId(idUsuario);
		Usuario usuario2= new Usuario();
		usuario1.setId(2L);
		session().save(usuario1);
		session().save(usuario2);

		Turno turno1 = new Turno();
		turno1.setUsuario(usuario1);
		turno1.setId(1L);
		Turno turno2 = new Turno();
		turno2.setId(2L);
		turno2.setUsuario(usuario2);
		Turno turno3 = new Turno();
		turno3.setId(3L);
		turno3.setUsuario(usuario1);

		session().save(turno1);
		session().save(turno2);
		session().save(turno3);
	}
	private List<Turno>cuandoBuscoAlUsuarioConDosTurnos(long idUsuario){
		return repositorioTurnos.mostarTodosTurnosDelUsuario(idUsuario);
	}
	@Test @Transactional @Rollback
	public void puedoCancelarTurnoSinModificarFechaNiMedico(){
		// preparacion
		long idTurno=1l;
		long idMedico=1L;
		Calendar calendario = new GregorianCalendar(2022,06,06);
		Turno turno = CuandoCargoUnTurnoAUnUsuario(idTurno, idMedico,calendario);
		// ejecucion
		alCancelarElturnoDelUsuario(turno.getId());
		//comparacion
		entoncesBuscoElTurnoYMeMuestraSoloIdFechaYmeDico(turno.getId(), idMedico,calendario);
	}

	private void entoncesBuscoElTurnoYMeMuestraSoloIdFechaYmeDico(long idTurno, long idMedico, Calendar calendario) {
		Turno turnobuscado= repositorioTurnos.buscarTurnoPorId(idTurno);
		assertThat(turnobuscado.getFechaTurno()).isEqualTo(calendario);
		assertThat(turnobuscado.getMedico().getId()).isEqualTo(idMedico);
		assertThat(turnobuscado.getEstado()).isTrue();
		assertThat(turnobuscado.getMascota()).isNull();
		assertThat(turnobuscado.getUsuario()).isNull();
	}

	private void alCancelarElturnoDelUsuario(long idTurno) {
		repositorioTurnos.cancelarTurnoPorId(idTurno);
	}

	private Turno CuandoCargoUnTurnoAUnUsuario(long idTurno, long idMedico, Calendar calendario) {
		Usuario usuario1 = new Usuario();
		usuario1.setId(1L);
		session().save(usuario1);
		Mascota mascota1 = new Mascota();
		mascota1.setId(1L);
		mascota1.setUsuario(usuario1);
		session().save(mascota1);

		Medico medico= new Medico();
		medico.setId(idMedico);
		session().save(medico);

		Turno turno1 = new Turno();
		turno1.setId(idTurno);
		turno1.setUsuario(usuario1);
		turno1.setMedico(medico);
		turno1.setMascota(mascota1);
		turno1.setEstado(true);
		turno1.setFechaTurno(calendario);
		session().save(turno1);
		return turno1;
	}

	@Test @Transactional @Rollback
	public void puedoBuscarElPrimerTurnoLibre(){
		dadoQueCargoVariosTurnos();
		Turno turnoproximo=cuandoBuscoelTurnoMasProximo();
		encuentroSoloUnTurno(turnoproximo);
	}
	private void encuentroSoloUnTurno(Turno turnoproximo) {
		Calendar fechaHoy =Calendar.getInstance();
		int anio2=fechaHoy.get(Calendar.YEAR);
		assertThat(turnoproximo.getFechaTurno().get(Calendar.YEAR)).isEqualTo(anio2+1);
		assertThat(turnoproximo.getEstado()).isTrue();
		assertThat(turnoproximo.getId()).isEqualTo(2L);
	}
	private Turno cuandoBuscoelTurnoMasProximo() {
		return repositorioTurnos.buscarProximoTurnoLibre();
	}

	private void dadoQueCargoVariosTurnos() {
		Turno turno1= new Turno();
		Turno turno2= new Turno();
		Turno turno3= new Turno();
		Turno turno4= new Turno();
		turno1.setId(1L);
		turno2.setId(2L);
		turno3.setId(3L);
		turno4.setId(4L);
		turno1.setEstado(true);
		turno2.setEstado(true);
		turno3.setEstado(true);
		turno4.setEstado(true);

		turno1.setFechaTurno(new GregorianCalendar(2022,01,01,10,00));
		Calendar calenda =Calendar.getInstance();
		int anio=calenda.get(Calendar.YEAR);
		anio+=1;
		turno2.setFechaTurno(new GregorianCalendar(anio,01,01,10,00));
		anio+=1;
		turno3.setFechaTurno(new GregorianCalendar(anio,01,01,10,00));
		anio+=1;
		turno4.setFechaTurno(new GregorianCalendar(anio,01,01,10,00));
		session().save(turno1);
		session().save(turno2);
		session().save(turno3);
		session().save(turno4);
	}
	@Test @Transactional @Rollback
	public void puedoBuscarLosTurnosPorFecha(){
		Calendar calendario =new GregorianCalendar(2022,1,10,10,00);
		dadoQueCargoVariosTurnosDisponibles(calendario);
		List <Turno> retorno= cuandoBuscoTurnosPorFechaPasandoUnCalendarMeRegresaUnaListaDeTurnosDeLaFecha(calendario);
		entoncesEncuentroTurnosConLasMismasFechas(calendario,retorno);
	}

	private void entoncesEncuentroTurnosConLasMismasFechas(Calendar calendario,List <Turno> retorno) {
		assertThat(retorno.size()).isEqualTo(2);
	}

	private List<Turno> cuandoBuscoTurnosPorFechaPasandoUnCalendarMeRegresaUnaListaDeTurnosDeLaFecha(Calendar calendario) {
		return repositorioTurnos.buscarTurnosPorFecha(calendario);
	}
	private void dadoQueCargoVariosTurnosDisponibles(Calendar calendario) {
		Turno turno1= new Turno();
		Turno turno2= new Turno();
		Turno turno3= new Turno();
		Turno turno4= new Turno();
		turno1.setEstado(true);
		turno2.setEstado(true);
		turno3.setEstado(true);
		turno4.setEstado(true);
		turno1.setFechaTurno(new GregorianCalendar(2022,1,1,10,00));
		turno2.setFechaTurno(calendario);
		turno3.setFechaTurno(calendario);
		turno4.setFechaTurno(new GregorianCalendar(2023,1,1,10,00));
		session().save(turno1);
		session().save(turno2);
		session().save(turno3);
		session().save(turno4);
	}
}
