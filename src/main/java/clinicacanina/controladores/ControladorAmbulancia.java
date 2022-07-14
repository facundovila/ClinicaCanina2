package clinicacanina.controladores;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import clinicacanina.modelo.Ambulancia;
import clinicacanina.modelo.Navegador;
import clinicacanina.modelo.ReservaDeAmbulancia;
import clinicacanina.repositorios.Trayecto;
import clinicacanina.servicios.ServicioAmbulancia;
import clinicacanina.servicios.ServicioGoogleDistanceMatrixAPI;
import clinicacanina.servicios.ServicioNavegacion;
import clinicacanina.servicios.ServicioValidacionDatos;
import clinicacanina.servicios.ServicioValidacionDatosImpl;

@Controller
public class ControladorAmbulancia {
	
	private ServicioAmbulancia servicioAmbulancia;
	private ServicioNavegacion servicioNavegacion;
	private ServicioValidacionDatosImpl servicioValidacionDatos = new ServicioValidacionDatosImpl();
	private ServicioGoogleDistanceMatrixAPI servicioGoogleDistanceMatrixAPI = new ServicioGoogleDistanceMatrixAPI();
	private String patente ="";
	
	@Autowired
	public ControladorAmbulancia(ServicioAmbulancia servicioAmbulancia, ServicioNavegacion servicioNavegacion) {
		this.servicioAmbulancia = servicioAmbulancia;
		this.servicioNavegacion = servicioNavegacion;
		
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

	// Se agregan validaciones de datos y se cambia la manera en que se muestran las ambulancias.
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
	
	//Se agrega metodo para consultar seguimiento.
	@RequestMapping(path="/ver-seguimiento")
	public ModelAndView verSeguimiento() {
        Ambulancia ambulancia = null;
        ReservaDeAmbulancia reserva = null;
        String direccion = "";
        Trayecto trayecto = null;
		ModelMap model = new ModelMap();
		
        if(patente == "") {
        	model.put("Error", "No se puede visualizar el seguimiento.");
        	return new ModelAndView("reservaAmbulancia", model);
        }
        ambulancia = servicioAmbulancia.buscarAmbulanciaPorPatente(patente);
    	reserva = servicioAmbulancia.buscarReserva(ambulancia);
        direccion = servicioValidacionDatos.quitarEspaciosEnBlanco(reserva.getDireccion());
        try {
        trayecto = servicioGoogleDistanceMatrixAPI.getDistance(direccion);
        
        model.put("Seguimiento", trayecto);
        model.put("Reserva", reserva);
        // Guardo datos para el seguimiento de la Navegacion.
        servicioNavegacion.guardarNavegacion(reserva, trayecto);
        }catch(Exception e) {
        	model.put("Error", "Ocurrio un error al intentar consultar el seguimiento.");
        }
		return new ModelAndView("reservaAmbulancia", model);
	}
	
	@RequestMapping(path="/detalle-seguimiento")
	public ModelAndView detalleSeguimiento() {
		ModelMap model = new ModelMap();
		List <String> datosNavegacion = new LinkedList<String>();
		Navegador navegador = servicioNavegacion.buscarNavegacion(patente);
		LocalDateTime horarioDeLlegada = servicioNavegacion.calcularHorarioDeLlegada(navegador.getPatente());
		String tiempoRestante = servicioNavegacion.calcularTiempoRestanteDeLlegada(navegador.getPatente());
		String horarioLlegadaString = horarioDeLlegada.getHour()+":"+horarioDeLlegada.getMinute();
		datosNavegacion.add(navegador.getHorarioDeSolicitud());
		datosNavegacion.add(navegador.getTiempoEstimado());
	    datosNavegacion.add(servicioValidacionDatos.validarHorario(horarioLlegadaString));
	    datosNavegacion.add(tiempoRestante);
	    if(Integer.parseInt(tiempoRestante) > 0) {
	    	model.put("DatosNavegacion", datosNavegacion);
	    }else {
	    	model.put("Llegada", "La Ambulancia con patente <strong>" + navegador.getPatente() + "</strong> se encuentra en la puerta de su domicilio.");
	    }
	   
	    return new ModelAndView("seguimiento", model);
	}
	
}
