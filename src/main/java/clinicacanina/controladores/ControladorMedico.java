package clinicacanina.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import clinicacanina.modelo.Medico;
import clinicacanina.servicios.ServicioMedico;

@Controller
public class ControladorMedico {
	
	private ServicioMedico servicioMedico;
	
	@Autowired
	public ControladorMedico(ServicioMedico servicioMedico) {
		this.servicioMedico = servicioMedico;
	}
	

	@RequestMapping(path="/lista-medicos")
	public ModelAndView mostrarTodosLosMedicos() {
		ModelMap model = new ModelMap();

		List <Medico> medico = servicioMedico.listarMedico();

		if(medico.isEmpty()){
			model.put("nohaymedicos", "todos los medicos estan ocupados");
		}

		model.put("medico", medico);
		
		return new ModelAndView("medicos",model); //vista , modelo
		
	}

	



}
