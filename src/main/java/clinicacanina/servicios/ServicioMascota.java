package clinicacanina.servicios;

import clinicacanina.controladores.HistoriaClinica;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;

import java.util.List;

public interface ServicioMascota {



     void mostrarDatosMascota();

     Mascota crearMascota(HistoriaClinica historiaClinica);



     Mascota buscarMascotaPorId(Long id);

     List<Mascota> listarMascotas();

     Mascota modificarMascota(Long id, Integer edad, Float peso);

     Long guardarVisitaMedicaDeMascota(Long idMascota, VisitaClinica visitaClinica);

     List<VisitaClinica> obtenerVisitasClinicasDeLaMascota(Mascota mascota);


}
