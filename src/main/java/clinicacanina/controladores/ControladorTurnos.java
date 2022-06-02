package clinicacanina.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

	@RequestMapping (path="/buscar-turno")
	public ModelAndView buscarTurno(String fecha) {

		ModelMap model = new ModelMap();

		String viewName =  "turnodisponible";

		List<Turno> turnos = servicioTurnos.buscarTurno(fecha);
		
		if (!turnos.isEmpty()) {
			model.put("msg", turnos);
			viewName= "turnoreservado"; //existen turnon disponibles
		} 
		return new ModelAndView(viewName, model); //todos los turnos estan reservados

	}

}
