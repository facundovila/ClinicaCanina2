package clinicacanina.controladores;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import clinicacanina.modelo.Medico;
import clinicacanina.servicios.ServicioMedico;
import org.springframework.web.bind.annotation.ModelAttribute;

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
		
		DatosMedicos datosM = new DatosMedicos();
		model.put("datosMedicos",datosM);
		
		List <Medico> medico = servicioMedico.listarMedico();

		
		if(medico.isEmpty()){
			model.put("nohaymedicos", "todos los medicos estan ocupados");
		}

		model.put("medico", medico);
			
		return new ModelAndView("medicos",model); //vista , modelo
		
	}
	
	@RequestMapping(path="/enviar-medico", method = RequestMethod.POST)
	public ModelAndView enviarMedicoADomicilio(@ModelAttribute("datosMedicos") DatosMedicos datosMedicos) {
		
		ModelMap model = new ModelMap();
		
		Medico medicoReservado = servicioMedico.buscarMedicosPorNombre(datosMedicos.getDni());
		
		servicioMedico.enviarMedico(medicoReservado);
		
		model.put("medicoReservado", medicoReservado);

		return new ModelAndView("reservaMedico",model); //muestro al medico reservado en una vista nueva
		
		
	}




}
