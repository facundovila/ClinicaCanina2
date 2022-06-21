package clinicacanina.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Turno;
import clinicacanina.servicios.ServicioTurnos;

@Controller
public class ControladorTurnos {

	private ServicioTurnos servicioTurnos;

	@Autowired
	public ControladorTurnos(ServicioTurnos servicioTurnos) {
		this.servicioTurnos = servicioTurnos;
	}


	@RequestMapping (path="/buscar-turno/{fecha}")
	public ModelAndView buscarTurno(@PathVariable("fecha") String fecha) { //este metodo deberia llamarse buscarTurnoRegistrado

		ModelMap model = new ModelMap();

		List<Turno> turnos = servicioTurnos.buscarTurno(fecha);
		
		if (!turnos.isEmpty()) {
			model.put("msg", turnos);

		} 
			model.put("vacia", "hay turnos disponibles");

			return new ModelAndView("turnodisponible", model); //todos los turnos estan reservados

	}
	
	@RequestMapping(path="/cancelarTurno/{idTurno}")
	public ModelAndView cancelarTurno(@PathVariable("idTurno") Long idTurno, HttpServletRequest request) {
		ModelMap mapa = new ModelMap();
		/*
		if (request.getSession().getAttribute("userId") == null) {
			return new ModelAndView("redirect:/login");
		}
		mapa.put("userID", request.getSession().getAttribute("userId"));
		*/
		Boolean estado = servicioTurnos.cancelarTurnoPorId(idTurno);

			return new ModelAndView("redirect:/usuarioHome",mapa);


	}}
