package clinicacanina.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.servicios.ServicioAmbulancia;

@Controller
public class ControladorAmbulancia {
	
	private ServicioAmbulancia servicioAmbulancia;
	
	@Autowired
	public ControladorAmbulancia(ServicioAmbulancia ambulanciaService) {
		this.servicioAmbulancia = ambulanciaService;
	}

	@RequestMapping(path="/pedir-ambulancia")
	public ModelAndView pedirAmbulancia() {

		ModelMap model = new ModelMap();

		String viewName = "ver-ambulancias";

		Ambulancia ambulanciaDisponible = servicioAmbulancia.buscarAmbulanciaDisponible();

		if(ambulanciaDisponible != null) {

			model.put("AmbulanciaDisponible", ambulanciaDisponible);

		}else {

		model.put("SinAmbulancias", "vuelva a intentarlo mas tarde");
		}


		return new ModelAndView(viewName, model);
	}

}
