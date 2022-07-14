package clinicacanina.servicios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import clinicacanina.modelo.Navegador;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.RepositorioAmbulancia;
import clinicacanina.repositorios.RepositorioNavegacion;
import clinicacanina.repositorios.Trayecto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ServicioNavegacionImpl implements ServicioNavegacion {

	private RepositorioNavegacion repositorioNavegacion;
	private ServicioValidacionDatos servicioValidacionDatos;

	@Autowired
	public ServicioNavegacionImpl(RepositorioNavegacion repositorioNavegacion) {
		this.repositorioNavegacion = repositorioNavegacion;
		this.servicioValidacionDatos = new ServicioValidacionDatosImpl();
	}

	@Override
	public LocalDateTime obtenerHoraActual() {
		return LocalDateTime.now();

	}

	@Override
	public LocalDateTime calcularHorarioDeLlegada(String patente) {
		LocalDateTime horarioSolicitud;
		
		Navegador navegador = buscarNavegacion(patente);
		String tiempoEstimado = navegador.getTiempoEstimado();
		Integer tiempoEstimadoNumber = parseTiempoEstimadoDeLlegada(tiempoEstimado);
		horarioSolicitud = parseLocalDateTime(navegador.getHorarioDeSolicitud());
		LocalDateTime horarioLlegada = sumarTiempoEstimadoAHoraActual(tiempoEstimadoNumber, horarioSolicitud);
		
		// Formato de guardado : "2022-7-10 16:11"
		
		String horarioLLegadaString = horarioLlegada.getYear() + "-" + horarioLlegada.getMonthValue() + "-"
				+ horarioLlegada.getDayOfMonth() + " " + horarioLlegada.getHour() + ":" + horarioLlegada.getMinute();

		navegador.setHorarioDeLlegada(horarioLLegadaString);
		// ---------Se actualizan los datos del Navegador en la BD -------------------
		actualizarNavegacion(navegador);
		return horarioLlegada;

	}

	@Override
	public String calcularTiempoRestanteDeLlegada(String patente) {
		Integer diferenciaMinutos = 0;
		LocalDateTime horaActual = obtenerHoraActual();
		Navegador navegador = buscarNavegacion(patente);
		String tiempoEstimado = navegador.getTiempoEstimado();
		Integer tiempoEstimadoNumber = parseTiempoEstimadoDeLlegada(tiempoEstimado);
		String horaDeSolicitud = navegador.getHorarioDeSolicitud();
		LocalDateTime horaSolicitudParseada = parseLocalDateTime(horaDeSolicitud);
		Integer minutosSolicitud = horaSolicitudParseada.getMinute();
		Integer minutosActuales = horaActual.getMinute();

		if (minutosActuales > minutosSolicitud) {
			diferenciaMinutos = minutosActuales - minutosSolicitud;
			tiempoEstimadoNumber -= diferenciaMinutos;
		}
		tiempoEstimado = String.valueOf(tiempoEstimadoNumber);
		navegador.setTiempoRestante(tiempoEstimado);

		actualizarNavegacion(navegador);
		return tiempoEstimado;
	}

	// Nuevo metodo para guardar datos de seguimiento
	@Override
	public String guardarNavegacion(ReservaDeAmbulancia reserva, Trayecto trayecto) {
		LocalDateTime horarioActual = obtenerHoraActual();
		String horarioActualString = horarioActual.getYear() + "-" + horarioActual.getMonthValue() + "-"
				+ horarioActual.getDayOfMonth() + " " + horarioActual.getHour() + ":" + horarioActual.getMinute();
		Navegador navegador = new Navegador();
		navegador.setPatente(reserva.getAmbulancia().getPatente());
		navegador.setReserva(reserva);
		navegador.setLocalidadOrigen(trayecto.getLocalidadOrigen());
		navegador.setLocalidadDestino(trayecto.getLocalidadDestino());
		navegador.setDistancia(trayecto.getDistancia());
		navegador.setTiempoEstimado(trayecto.getTiempo());
		navegador.setHorarioDeSolicitud(servicioValidacionDatos.validarHorario(horarioActualString));
		repositorioNavegacion.guardarNavegacion(navegador);

		return navegador.getReserva().getAmbulancia().getPatente();

	}

	// Nuevo metodo para buscar datos de seguimiento
	@Override
	public Navegador buscarNavegacion(String patente) {
		Navegador navegadorEcontrado = repositorioNavegacion.buscarNavegacion(patente);
		return navegadorEcontrado;
	}

	@Override
	public void actualizarNavegacion(Navegador navegador) {
		repositorioNavegacion.actualizarNavegacion(navegador);

	}

	private LocalDateTime sumarTiempoEstimadoAHoraActual(Integer tiempoEstimadoNumber, LocalDateTime horarioActual) {

		LocalDateTime horarioLlegada = horarioActual.plusMinutes(tiempoEstimadoNumber);
		return horarioLlegada;
	}

	private Integer parseTiempoEstimadoDeLlegada(String tiempoEstimado) {
		char[] arrayCaracteres = tiempoEstimado.toCharArray();
		String tiempoEstimadoString;
		if(Character.isDigit(arrayCaracteres[1])) {
			tiempoEstimadoString = String.valueOf(arrayCaracteres[0]) + String.valueOf(arrayCaracteres[1]);
		}else {
			tiempoEstimadoString = String.valueOf(arrayCaracteres[0]);
		}
		 
		Integer tiempoEstimadoNumber = Integer.parseInt(tiempoEstimadoString);
		return tiempoEstimadoNumber;
	}

	private LocalDateTime parseLocalDateTime(String horaDeSolicitud) {
		DateTimeFormatter formatter;
		
		String formatoA = "yyyy-M-dd HH:mm";
		String formatoB = "yyyy-M-dd HH:m";
		char[] arrayCaracteres = horaDeSolicitud.toCharArray();
		
		if(Character.isDigit(arrayCaracteres[arrayCaracteres.length -2])) {
			formatter = DateTimeFormatter.ofPattern(formatoA);
		}else {
			formatter = DateTimeFormatter.ofPattern(formatoB);
		}
		
		LocalDateTime horaSolicitudParse = LocalDateTime.parse(horaDeSolicitud, formatter);
		return horaSolicitudParse;
	}

}
