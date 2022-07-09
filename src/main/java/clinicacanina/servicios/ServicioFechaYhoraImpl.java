package clinicacanina.servicios;

import java.util.Calendar;

public class ServicioFechaYhoraImpl implements ServicioFechaYhora{

    @Override
    public Calendar fechaDelSistema() {
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }
}
