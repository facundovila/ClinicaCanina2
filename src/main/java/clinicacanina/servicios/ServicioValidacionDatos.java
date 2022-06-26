package clinicacanina.servicios;

public interface ServicioValidacionDatos {

	boolean validarTelefono(String telefono);
	boolean validarDireccion(String direccion);
	boolean validarMotivo(String motivo);

}
