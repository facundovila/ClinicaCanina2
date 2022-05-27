package clinicacanina.controladores;

import clinicacanina.modelo.Mascota;
import clinicacanina.servicios.ServicioMascota;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ControladorMascota {


    private ServicioMascota servicioMascota;


    public ControladorMascota(ServicioMascota servicioMascota) {
        this.servicioMascota = servicioMascota;
    }


    @RequestMapping(path="/mostrar-mascota/{nombre}/{peso}")
    public ModelAndView mostrarMascotaCreada(@PathVariable("nombre") String nombre, @PathVariable("peso") Integer peso) {

        ModelMap model = new ModelMap();

        Mascota resultado=servicioMascota.crearMascota(nombre,peso);

       model.put("mascota", resultado);

        return new ModelAndView("detalle-mascota", model);

    }

    @RequestMapping(path = "/listar-mascota/{nombreMascota}/{peso}")
    public ModelAndView listar(@PathVariable("nombreMascota") String nombreMascota, @PathVariable("peso") Integer peso) {

        ModelMap model= new ModelMap();

        List<Mascota> resultado = null;

        try{
            resultado = servicioMascota.buscarMascota(nombreMascota, peso);
        } catch(Exception e) {
            model.put("msg-error", "Lista de animal invalida");
        }

        model.put("listaDeMascotas", resultado);

        return new ModelAndView("listado-mascota", model);



    }
}
