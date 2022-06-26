package clinicacanina.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.servicios.ServicioAmbulancia;
import clinicacanina.servicios.ServicioValidacionDatos;
import clinicacanina.servicios.ServicioValidacionDatosImpl;

@Controller
public class ControladorAmbulancia {
	
	private ServicioAmbulancia servicioAmbulancia;
	private ServicioValidacionDatosImpl servicioValidacionDatos = new ServicioValidacionDatosImpl();
	
	@Autowired
	public ControladorAmbulancia(ServicioAmbulancia servicioAmbulancia) {
		this.servicioAmbulancia = servicioAmbulancia;
		//this.servicioValidacionDatos = servicioValidacionDatos;
	}

	@RequestMapping(path="/pedir-ambulancia")
	public ModelAndView listarAmbulanciasDisponibles() {

		ModelMap model = new ModelMap();
		model.put("datosReservaAmbulancia", new DatosReservaAmbulancia());

		String viewName = "reservaAmbulancia";

		List <Ambulancia> ambulanciasDisponibles = servicioAmbulancia.buscarAmbulanciasDisponibles();

		if(ambulanciasDisponibles.isEmpty()) {
			model.put("SinAmbulancias", "vuelva a intentarlo mas tarde");

			

		}else {
			model.put("AmbulanciaDisponible", ambulanciasDisponibles);
		
		}


		return new ModelAndView(viewName, model);
	}

	@RequestMapping(path = "/reservar-ambulancia", method = RequestMethod.POST)
	public ModelAndView reservarAmbulancia(@ModelAttribute("datosReservaAmbulancia") DatosReservaAmbulancia datosReservaAmbulancia) {
		ModelMap model = new ModelMap();
		//String viewName = "reservaAmbulancia";
		Ambulancia ambulancia = servicioAmbulancia.buscarAmbulanciaPorPatente(datosReservaAmbulancia.getPatente());
		//doble validacion, pero la ambulancia que nos trae deberia estar disponible.
		String direccion = datosReservaAmbulancia.getDireccionCalle() + " " + datosReservaAmbulancia.getDireccionNumero();
		if(servicioValidacionDatos.validarDireccion(direccion) == false) {
			model.put("ErrorDatos", "La direccion no cumple con el formato esperado.");
			return new ModelAndView("reservaAmbulancia", model);
		}
		if(!servicioValidacionDatos.validarTelefono(datosReservaAmbulancia.getTelefono())) {
			model.put("ErrorDatos", "El telefono ingresado es invalido.");
			return new ModelAndView("reservaAmbulancia", model);
		}
		if(!servicioValidacionDatos.validarMotivo(datosReservaAmbulancia.getMotivo())) {
			model.put("ErrorDatos", "El motivo indicado no es valido.");
			return new ModelAndView("reservaAmbulancia", model);
		}
		try {
			
			servicioAmbulancia.reservarAmbulancia(direccion, datosReservaAmbulancia.getTelefono(), datosReservaAmbulancia.getMotivo(), ambulancia);
			ReservaDeAmbulancia reserva = servicioAmbulancia.buscarReserva(ambulancia);
			model.put("ReservaRealizada", reserva);
		}catch(Exception e) {
			model.put("Error", "Ocurrio un error inesperado al intentar reservar.");
			//viewName = "errorAlReservar";
		}
		
		
		
		return new ModelAndView("reservaAmbulancia", model);
	}

}
