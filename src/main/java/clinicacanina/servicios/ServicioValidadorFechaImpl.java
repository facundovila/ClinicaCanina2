package clinicacanina.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service @Transactional
public class ServicioValidadorFechaImpl implements ServicioValidadorFecha {


    @Override
    public boolean validarFecha(String fecha) {
        if(fecha.matches( ("\\d{4}-[01]\\d-[0-3]\\d"))){return true;
        }
        if(fecha.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public Calendar StringACalendar(String fecha) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        return calender;
    }
}
