package clinicacanina.servicios;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

@Service
@Transactional
public interface ServicioFechaYhora {

    Calendar fechaDelSistema();
}
