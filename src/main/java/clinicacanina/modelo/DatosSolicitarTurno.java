package clinicacanina.modelo;

import java.util.Calendar;
import java.util.Date;

public class DatosSolicitarTurno {
    private Calendar fecha;

    private Date fecha2;
    public Date getFecha2() {
        return fecha2;
    }

    public void setFecha2(Date fecha2) {
        this.fecha2 = fecha2;
    }

    public Calendar getCalendario() {
        return fecha;
    }

    public void setCalendario(Calendar calendario) {
        this.fecha = calendario;
    }
}
