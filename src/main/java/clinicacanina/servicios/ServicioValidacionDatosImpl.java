package clinicacanina.servicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Service @Transactional
public class ServicioValidacionDatosImpl implements ServicioValidacionDatos {
    private String regex = "";
	
    @Override
	public boolean validarTelefono(String telefono) {
		regex = "[0-9]{8,10}";
		return telefono.matches(regex);
	}

	@Override
	public boolean validarDireccion(String direccion) {
		String direccionUpperCase = direccion.toUpperCase();
		regex = "([A-Z]{2,10}[ ]?){1,3}[ ][0-9]{1,5}";
		return direccionUpperCase.matches(regex);
	}
	
	@Override
	public boolean validarLocalidad(String localidad) {
		String localidadUpperCase = localidad.toUpperCase();
		regex = "([A-Z]{2,10}[ ]?){1,3}";
		return localidadUpperCase.matches(regex);
	}

	@Override
	public boolean validarMotivo(String motivo) {
		String motivoUpperCasse = motivo.toUpperCase();
		regex = "([A-Z]{1,12}[ ]?){1,5}";
		return motivoUpperCasse.matches(regex);
	}

	@Override
	public String quitarEspaciosEnBlanco(String cadena) {
		String cadenaSinEspacio = cadena.replaceAll("\\s+","");
		return cadenaSinEspacio;
		
	}

	@Override
	public boolean validarPatente(String patente) {
		regex = "[A-Z]{3}[0-9]{3}";
		return patente.matches(regex);
	}

	@Override
	public String validarHorario(String time) {
		char[] arrayCaracteres = time.toCharArray();
		String timeConvertido = "";
		
		if(arrayCaracteres.length > 5 && arrayCaracteres.length < 15) {
			// fecha y hora
			timeConvertido = convertirHorarioSolicitud(arrayCaracteres);
		}
		if(arrayCaracteres.length == 15) {
			timeConvertido = time;
		}
		if(arrayCaracteres.length == 5) {
			// hora correcta
			timeConvertido = time;
		}
		if(arrayCaracteres.length < 5) {
			// hora incorrecta
			timeConvertido = convertirHorarioLlegada(arrayCaracteres);
		}
		
		return timeConvertido;
	}


	
	private String convertirHorarioSolicitud(char[]  time) {
		String horarioConvertido = "";
		for(int i = 0; i < time.length; i++) {
			if(i == 12) {
				horarioConvertido += time[i];
				horarioConvertido += "0";
				break;
			}else {
				horarioConvertido += time[i];
			}
		}
		
		return horarioConvertido += time[time.length -1];
	}

	
	private String convertirHorarioLlegada(char[]  time) {
		String horarioConvertido = "";
		for(int i = 0; i < time.length; i++) {
			if(i == 2) {
				horarioConvertido += time[i];
				horarioConvertido += "0";
				break;
			}else {
				horarioConvertido += time[i];
			}
		}
		
		return horarioConvertido += time[time.length -1];
	}
}
