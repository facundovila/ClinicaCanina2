package clinicacanina.servicios;

import clinicacanina.modelo.Mascota;

import java.util.List;

public interface ServicioMascota {



     void mostrarDatosMascota();

     Mascota crearMascota(String nombreMascota,Integer peso, Integer edad);

     List<Mascota>buscarMascota(String nombreMascota, Integer peso, Integer edad);

     Mascota buscarMascotaPorId(Long id);

     List<Mascota> listarMascotas();
}
