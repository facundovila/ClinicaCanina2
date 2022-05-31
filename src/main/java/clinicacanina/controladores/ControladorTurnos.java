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
		this.servicioTurnos=servicioTurnos;
	}
	
	
	public ModelAndView buscarTurno (String fecha, Boolean estado) {

		ModelMap model= new ModelMap();
		
		String viewName= "turnoreservado";

//		Turno nuevoTurno= servicioTurnos.buscarTurno(turno);
		
		Turno nuevoTurno= new Turno(fecha, estado);
		
		if(nuevoTurno.getEstado()==true) {
			model.put("turno", nuevoTurno);
			
	    	return new ModelAndView(viewName, model);

		}else {
			viewName = "turnodisponible";
			model.put("msg", "TurnoDisponible");
		}
    	return new ModelAndView(viewName, model);
	
	  
	  
	}

}
