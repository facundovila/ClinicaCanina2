package clinicacanina.servicios;

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
	public boolean validarMotivo(String motivo) {
		String motivoUpperCasse = motivo.toUpperCase();
		regex = "([A-Z]{1,12}[ ]?){1,5}";
		return motivoUpperCasse.matches(regex);
	}
	
	

}
