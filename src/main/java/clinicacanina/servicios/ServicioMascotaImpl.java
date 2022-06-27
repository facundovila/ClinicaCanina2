package clinicacanina.servicios;


import clinicacanina.controladores.HistoriaClinica;
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

    @Override
    public Mascota modificarMascota(Mascota mascota) {

        Mascota mascotaAModificar = buscarMascotaPorId(mascota.getId());


        if(mascotaAModificar != null){
            mascotaAModificar.setDetalleTratamientos("pipeta anti pulgas");

            repositorioMascota.modificarMascota(mascotaAModificar);

        }else{

           throw new MascotaInexistenteException();


        }




        return mascotaAModificar;

    }


}
