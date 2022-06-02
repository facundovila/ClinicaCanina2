package clinicacanina.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.servicios.AmbulanciaService;

@Controller
public class AmbulanciaController {
	
	private AmbulanciaService ambulanciaService;
	
	@Autowired
	public AmbulanciaController(AmbulanciaService ambulanciaService) {
		this.ambulanciaService = ambulanciaService;
	}

	@RequestMapping(path="/pedir-ambulancia")
	public ModelAndView pedirAmbulancia() {
		ModelMap model = new ModelMap();
		String viewName = "ambulancia-en-camino";
		Ambulancia ambulanciaDisponible = ambulanciaService.buscarAmbulanciaDisponible();
		if(ambulanciaDisponible != null) {
			model.put("AmbulanciaDisponible", ambulanciaDisponible);
			return new ModelAndView(viewName, model);
		}
		model.put("SinAmbulancias", "No hay ambulancias disponibles");
		viewName = "no-hay-ambulancias";
		return new ModelAndView(viewName, model);
	}

}
