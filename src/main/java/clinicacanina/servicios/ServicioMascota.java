package clinicacanina.servicios;

import clinicacanina.modelo.HistoriaClinica;
import clinicacanina.modelo.Mascota;

import java.util.List;

public interface ServicioMascota {



     void mostrarDatosMascota();

     Mascota crearMascota(HistoriaClinica historiaClinica);



     Mascota buscarMascotaPorId(Long id);

     List<Mascota> listarMascotas();
}
