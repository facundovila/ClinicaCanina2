package clinicacanina.controladores;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.servicios.AmbulanciaService;

public class AmbulanciaController {
	
	private AmbulanciaService ambulanciaService;
	
	public AmbulanciaController(AmbulanciaService ambulanciaService) {
		this.ambulanciaService = ambulanciaService;
	}

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
