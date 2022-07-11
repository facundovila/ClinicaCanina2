package clinicacanina.servicios;


import clinicacanina.controladores.HistoriaClinica;
import clinicacanina.modelo.Mascota;
import clinicacanina.modelo.VisitaClinica;
import clinicacanina.repositorios.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioMascotaImpl implements ServicioMascota {

    private RepositorioMascota repositorioMascota;



    @Autowired
    public ServicioMascotaImpl(RepositorioMascota repositorioMascota){

        this.repositorioMascota = repositorioMascota;

    }


    @Override
    public void mostrarDatosMascota(){


    }


    @Override
    public Mascota crearMascota(HistoriaClinica historiaClinica){


        Mascota mascotaGuardada = new Mascota(historiaClinica);

       repositorioMascota.guardar(mascotaGuardada);

        return mascotaGuardada;

    }




    @Override
    public Mascota buscarMascotaPorId(Long id){

        Mascota mascotaBuscada = repositorioMascota.buscarPorId(id);

        return mascotaBuscada;

    }


    @Override
    public List<Mascota> listarMascotas(){

        return repositorioMascota.buscarTodasLasMascotas();

    }

    @Override
    public Mascota modificarMascota(Long id, String detalleTratamientos, String sintomas, Integer edad, Integer peso, String nombre) {

        Mascota mascotaAModificar = buscarMascotaPorId(id);



        if(mascotaAModificar.getId() != null){

           Mascota mascotaModificada = repositorioMascota.modificarMascota( id, detalleTratamientos, sintomas, peso, edad, nombre);

            return mascotaModificada;

        }else{

           throw new MascotaInexistenteException();

        }
    }

    @Override
    public Long guardarVisitaMedicaDeMascota(Long idMascota, VisitaClinica visitaClinica){

       return repositorioMascota.guardarVisitaMedica(idMascota,visitaClinica);

    }

    @Override
    public List<VisitaClinica> obtenerVisitasClinicasDeLaMascota(Mascota mascota){

        return repositorioMascota.obtenerVisitaMedicaDeLaMascota(mascota);

    }


}
