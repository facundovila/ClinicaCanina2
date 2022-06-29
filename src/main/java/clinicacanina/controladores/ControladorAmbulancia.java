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
import clinicacanina.servicios.ServicioGoogleDistanceMatrixAPI;
import clinicacanina.servicios.ServicioValidacionDatos;
import clinicacanina.servicios.ServicioValidacionDatosImpl;

@Controller
public class ControladorAmbulancia {
	
	private ServicioAmbulancia servicioAmbulancia;
	private ServicioValidacionDatosImpl servicioValidacionDatos = new ServicioValidacionDatosImpl();
	private ServicioGoogleDistanceMatrixAPI servicioGoogleDistanceMatrixAPI = new ServicioGoogleDistanceMatrixAPI();
	private String patente ="";
	
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
			model.put("SinAmbulancias", "Actualmente no contamos con ambulancias disponibles, vuelva a intentarlo mas tarde. Muchas gracias!");

			

		}else {
			model.put("AmbulanciaDisponible", ambulanciasDisponibles);
			this.patente = ambulanciasDisponibles.get(0).getPatente();
		
		}


		return new ModelAndView(viewName, model);
	}

	@RequestMapping(path = "/reservar-ambulancia", method = RequestMethod.POST)
	public ModelAndView reservarAmbulancia(@ModelAttribute("datosReservaAmbulancia") DatosReservaAmbulancia datosReservaAmbulancia) {
		ModelMap model = new ModelMap();
		//String viewName = "reservaAmbulancia";
		Ambulancia ambulancia = servicioAmbulancia.buscarAmbulanciaPorPatente(datosReservaAmbulancia.getPatente());
		//doble validacion, pero la ambulancia que nos trae deberia estar disponible.
		String direccion="";
		//String telefono = datosReservaAmbulancia.getTelefono().toString();
		if(servicioValidacionDatos.validarDireccion(datosReservaAmbulancia.getDireccion()) == false) {
			model.put("ErrorDatos", "La direccion no cumple con el formato esperado.");
			return new ModelAndView("reservaAmbulancia", model);
		}
		if(servicioValidacionDatos.validarLocalidad(datosReservaAmbulancia.getLocalidad()) == false) {
			model.put("ErrorDatos", "La localidad no cumple con el formato esperado.");
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
		direccion = datosReservaAmbulancia.getDireccion() + " " +datosReservaAmbulancia.getLocalidad();
		//direccion = servicioValidacionDatos.quitarEspaciosEnBlanco(direccion);
		try {
			
			servicioAmbulancia.reservarAmbulancia(direccion.toLowerCase(), datosReservaAmbulancia.getTelefono(), datosReservaAmbulancia.getMotivo().toLowerCase(), ambulancia);
			ReservaDeAmbulancia reserva = servicioAmbulancia.buscarReserva(ambulancia);
			model.put("ReservaRealizada", reserva);
		}catch(Exception e) {
			model.put("Error", "Ocurrio un error inesperado al intentar reservar.");
			//viewName = "errorAlReservar";
		}
		
		
		
		return new ModelAndView("reservaAmbulancia", model);
	}
	
	@RequestMapping(path="/ver-seguimiento")
	public ModelAndView verSeguimiento() {
        Ambulancia ambulancia = null;
        ReservaDeAmbulancia reserva = null;
        String direccion = "";
        String[] seguimiento = null;
		ModelMap model = new ModelMap();
		//model.put("datosParaSeguimiento", new DatosParaSeguimiento());
        if(patente == "") {
        	model.put("Error", "No se puede visualizar el seguimiento.");
        	return new ModelAndView("reservaAmbulancia", model);
        }
        ambulancia = servicioAmbulancia.buscarAmbulanciaPorPatente(patente);
    	reserva = servicioAmbulancia.buscarReserva(ambulancia);
        direccion = servicioValidacionDatos.quitarEspaciosEnBlanco(reserva.getDireccion());
        try {
        seguimiento = servicioGoogleDistanceMatrixAPI.getData(direccion);
        model.put("Seguimiento", seguimiento);
        model.put("Reserva", reserva);
        }catch(Exception e) {
        	model.put("Error", "Ocurrio un error al intentar consultar el seguimiento.");
        }
		return new ModelAndView("reservaAmbulancia", model);
	}
	
	/*
	@RequestMapping(path="/ver-seguimiento")
	public ModelAndView verSeguimiento() {

		ModelMap model = new ModelMap();
		model.put("datosParaSeguimiento", new DatosParaSeguimiento());


		return new ModelAndView("reservaAmbulancia", model);
	}
	
	@RequestMapping(path="/ver-seguimiento-reserva")
	public ModelAndView verSeguimientoReserva(@ModelAttribute("datosParaSeguimiento") DatosParaSeguimiento datosParaSeguimiento) {
		ModelMap model = new ModelMap();
		String patente;//datosParaSeguimiento.getPatente();
		String telefono;//datosParaSeguimiento.getTelefono();
		String direccion = "";
		ReservaDeAmbulancia reserva = null;
		String[] seguimiento;
		//------------------------------------------------------------------
		if(datosParaSeguimiento.getPatente() !=null) {
		  if(!servicioValidacionDatos.validarPatente(datosParaSeguimiento.getPatente().toUpperCase())) {
			model.put("ErrorDatos", "La patente debe contener el siguiente formato : ABC123");
			return new ModelAndView("reservaAmbulancia", model);
		  }
		}
		if(datosParaSeguimiento.getTelefono() != null) {
		  if(!servicioValidacionDatos.validarTelefono(datosParaSeguimiento.getTelefono())) {
			model.put("ErrorDatos", "El telefono debe contener caracteres numericos entre 8 y 10 digitos.");
			return new ModelAndView("reservaAmbulancia", model);
		  }
		}	
		patente = datosParaSeguimiento.getPatente().toUpperCase();
		telefono = datosParaSeguimiento.getTelefono();
		
		if(patente != "" && telefono == null) {
			Ambulancia ambulancia = servicioAmbulancia.buscarAmbulanciaPorPatente(patente);
			reserva = servicioAmbulancia.buscarReserva(ambulancia);
			direccion = servicioValidacionDatos.quitarEspaciosEnBlanco(reserva.getDireccion());
		}if(patente =="" && telefono !=null) {
			reserva = servicioAmbulancia.buscarReservaPorTelefono(telefono);
			direccion = servicioValidacionDatos.quitarEspaciosEnBlanco(reserva.getDireccion());
		}if(patente != "" && telefono != null) {
			reserva = servicioAmbulancia.buscarReservaPorTelefono(telefono);
			direccion = servicioValidacionDatos.quitarEspaciosEnBlanco(reserva.getDireccion());
		}if(patente == "" && telefono == null) {
			model.put("ErrorDatos", "Debe llenar almenos uno de los campos.");
			return new ModelAndView("reservaAmbulancia", model);
		}if(reserva == null) {
			model.put("ErrorDatos", "LA RESERVA POR LA CUAL CONSULTA, NO EXISTE!");
			return new ModelAndView("reservaAmbulancia", model);
		}
        try {
        seguimiento = servicioGoogleDistanceMatrixAPI.getData(direccion);
        model.put("Seguimiento", seguimiento);
        model.put("Reserva", reserva);
        }catch(Exception e) {
        	model.put("Error", "No se pudo obtener los datos de seguimiento.");
        }
		return new ModelAndView("reservaAmbulancia", model);
	}
	
*/
}
