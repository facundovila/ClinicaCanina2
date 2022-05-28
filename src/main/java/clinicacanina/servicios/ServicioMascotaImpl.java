package clinicacanina.servicios;


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
    public Mascota crearMascota(String nombreMascota, Integer peso){

        return new Mascota(nombreMascota,peso);

    }

    @Override
    public List<Mascota> buscarMascota(String nombreMascota, Integer peso){
        List<Mascota> lista= new LinkedList<>();

        for(int i=0; i<10; i++){
            lista.add(new Mascota(nombreMascota, peso));
        }

        return lista;

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
