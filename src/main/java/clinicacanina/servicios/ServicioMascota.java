package clinicacanina.servicios;

import clinicacanina.controladores.HistoriaClinica;
import clinicacanina.modelo.Mascota;

import java.util.List;

public interface ServicioMascota {



     void mostrarDatosMascota();

     Mascota crearMascota(HistoriaClinica historiaClinica);



     Mascota buscarMascotaPorId(Long id);

     List<Mascota> listarMascotas();

     Mascota modificarMascota(Long id, String detalleTratamientos, String detalleTratamientoCambiado, Integer edad, Integer peso, String nombre);
}
