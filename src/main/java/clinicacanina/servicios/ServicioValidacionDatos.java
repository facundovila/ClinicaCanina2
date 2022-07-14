package clinicacanina.servicios;

public interface ServicioValidacionDatos {

	boolean validarTelefono(String telefono);
	boolean validarDireccion(String direccion);
	boolean validarLocalidad(String localidad);
	boolean validarMotivo(String motivo);
	String quitarEspaciosEnBlanco(String cadena);
	boolean validarPatente(String patente);

	String validarHorario(String time);
	



    boolean validadEdad(Integer edad);
}
