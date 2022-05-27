package clinicacanina.servicios;

import clinicacanina.modelo.Mascota;

import java.util.List;

public interface ServicioMascota {



     void mostrarDatosMascota();

     Mascota crearMascota(String nombreMascota,Integer peso);

     List<Mascota>buscarMascota(String nombreMascota, Integer peso);


     Mascota buscarMascotaPorId(Long id);

}
