package clinicacanina.controladores;

import java.util.List;

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
	public ControladorAmbulancia(ServicioAmbulancia servicioAmbulancia) {
		this.servicioAmbulancia = servicioAmbulancia;
	}

	@RequestMapping(path="/pedir-ambulancia")
	public ModelAndView listarAmbulanciasDisponibles() {

		ModelMap model = new ModelMap();

		String viewName = "listaAmbulancias";

		List <Ambulancia> ambulanciasDisponibles = servicioAmbulancia.buscarAmbulanciasDisponibles();

		if(ambulanciasDisponibles.isEmpty()) {
			model.put("SinAmbulancias", "vuelva a intentarlo mas tarde");

			

		}else {
			model.put("AmbulanciaDisponible", ambulanciasDisponibles);
		
		}


		return new ModelAndView(viewName, model);
	}

}
