package clinicacanina.controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import clinicacanina.modelo.DatosSolicitarTurno;
import clinicacanina.modelo.Mascota;
import clinicacanina.servicios.ServicioLogin;
import clinicacanina.servicios.ServicioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Turno;
import clinicacanina.servicios.ServicioTurnos;

@Controller
public class ControladorTurnos {

	private ServicioTurnos servicioTurnos;
	private ServicioLogin servicioLogin;

	@Autowired
	public ControladorTurnos(ServicioTurnos servicioTurnos,ServicioLogin servicioLogin) {
		this.servicioTurnos = servicioTurnos;
		this.servicioLogin=servicioLogin;
	}


	@RequestMapping(path = "/buscar-turno/{fecha}")
	public ModelAndView buscarTurno(@PathVariable("fecha") String fecha) { //este metodo deberia llamarse buscarTurnoRegistrado

		ModelMap model = new ModelMap();

		List<Turno> turnos = servicioTurnos.buscarTurno(fecha);

		if (!turnos.isEmpty()) {
			model.put("msg", turnos);

		}
		model.put("vacia", "hay turnos disponibles");

		return new ModelAndView("turnodisponible", model); //todos los turnos estan reservados

	}

	@RequestMapping(path = "/cancelarTurno/{idTurno}")
	public ModelAndView cancelarTurno(@PathVariable("idTurno") Long idTurno, HttpServletRequest request) {
		ModelMap mapa = new ModelMap();
		if (request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/login");
		}
		Boolean estado = servicioTurnos.cancelarTurnoPorId(idTurno);
		if (estado == false) {
			mapa.put("mensaje", "el turno no se puede cancelar por el horario");
		}
		mapa.put("mensaje", "turno Cancelado");
		return new ModelAndView("redirect:/usuarioHome", mapa);
	}

	@RequestMapping(path = "/usuarioSolicitarTurno")
	public ModelAndView irSoliciarTurno(HttpServletRequest request) {
		ModelMap mapa = new ModelMap();
		if (request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/login");
		}
		mapa.put("datosSolicitarTurno", new DatosSolicitarTurno());

		List<Mascota> mascotas= servicioLogin.listarMascotas((long)request.getSession().getAttribute("userId"));
		mapa.put("listaMascotas",mascotas);

		List<Turno> turnos= servicioTurnos.buscarTurnoPorFechaDeHoy();
		if(turnos.isEmpty()){
			mapa.put("mensaje","Sin Turnos disponibles para la fecha. Turnos mas Proximos");
			//aca va un metodo para traer proximos turnos;
			turnos= servicioTurnos.buscarProximosTurnos();
			mapa.put("listaTurnosDisponibles", turnos);
			return new ModelAndView("usuarioSolicitarTurno", mapa);
		}
		mapa.put("listaTurnosDisponibles", turnos );
		return new ModelAndView("usuarioSolicitarTurno", mapa);
	}

	@RequestMapping(path = "/tomarTurno/{idTurno}/{idMascota}", method = RequestMethod.POST)
	public ModelAndView tomarTurno(HttpServletRequest request, @PathVariable("idTurno") long idTurno,@PathVariable("idMascota") long idMascota) {
		ModelMap mapa = new ModelMap();
		if (request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/login");
		}
		Long idUsuario = (Long) request.getSession().getAttribute("userId");
		servicioTurnos.tomarTurno(idMascota, idUsuario,idTurno);
		return new ModelAndView("redirect:/usuarioHome", mapa);
	}

	@RequestMapping(path = "/tomarTurnoUsuario/{idTurno}", method = RequestMethod.POST)
	public ModelAndView tomarTurnoUsuario(HttpServletRequest request, @PathVariable("idTurno") long idTurno) {
		ModelMap mapa = new ModelMap();
		if (request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/login");
		}
		Long idUsuario = (Long) request.getSession().getAttribute("userId");
		servicioTurnos.tomarTurnoUsuario(idUsuario,idTurno);
		return new ModelAndView("redirect:/usuarioHome", mapa);
	}


	@RequestMapping(path="/irSoliciarTurnoPorFecha", method = RequestMethod.POST)
		public ModelAndView irSoliciarTurnoFecha( HttpServletRequest request, @ModelAttribute("datosSolicitarTurno") DatosSolicitarTurno datosSolicitarTurno) {

		if (request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/login");
		}
		ModelMap mapa = new ModelMap();

		List <Turno>turnos= servicioTurnos.buscarTurnoPorFecha(datosSolicitarTurno.getFecha2());
		if(turnos.isEmpty()){
			mapa.put("mensaje","Sin Turnos. Seleccione Otra Fecha");
			return new ModelAndView("usuarioSolicitarTurno", mapa);
		}
		mapa.put("listaTurnosDisponibles", turnos );

		return new ModelAndView("usuarioSolicitarTurno", mapa);
	}

}



