package clinicacanina.servicios;


import clinicacanina.modelo.HistoriaClinica;
import clinicacanina.modelo.Mascota;
import clinicacanina.repositorios.RepositorioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
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



}
