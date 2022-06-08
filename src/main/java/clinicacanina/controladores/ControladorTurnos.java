package clinicacanina.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
	public ModelAndView buscarTurnoDisponible(@PathVariable("fecha") String fecha) { 

		ModelMap model = new ModelMap();

		List<Turno> turnos = servicioTurnos.buscarTurno(fecha);
		
		if (!turnos.isEmpty()) {
			model.put("noHayTunosDisponibles", turnos);

		} 
			model.put("turnosDisponibles", "hay turnos disponibles");


			return new ModelAndView("turnodisponible", model);

	}

}
