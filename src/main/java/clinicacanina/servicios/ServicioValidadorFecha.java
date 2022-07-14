package clinicacanina.servicios;

import java.util.Calendar;

public interface ServicioValidadorFecha {

    boolean validarFecha(String fecha);
    Calendar StringACalendar(String fecha);
}
