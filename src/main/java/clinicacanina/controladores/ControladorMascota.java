package clinicacanina.controladores;

import clinicacanina.modelo.Mascota;
import clinicacanina.servicios.ListaVaciaExcepcion;
import clinicacanina.servicios.ServicioMascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ControladorMascota {


    private ServicioMascota servicioMascota;

    @Autowired
    public ControladorMascota(ServicioMascota servicioMascota) {
        this.servicioMascota = servicioMascota;
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/listar-mascotas");
    }


    @RequestMapping(path="/mostrar-mascota/{nombre}/{peso}/{edad}/")
    public ModelAndView mostrarMascotaCreada(@PathVariable("nombre") String nombre, @PathVariable("peso") Integer peso, @PathVariable("edad") Integer edad) {

        ModelMap model = new ModelMap();

        Mascota resultado=servicioMascota.crearMascota(nombre,peso, edad);

        model.put("mascota", resultado);

        return new ModelAndView("detalle-mascota", model);

    }

    @RequestMapping(path = "/listar-mascotas" , method = RequestMethod.GET)
    public ModelAndView listarMascotas() {

        ModelMap model= new ModelMap();

        List<Mascota> listaDeMascotas = servicioMascota.listarMascotas();

//        try{
//            listaDeMascotas = servicioMascota.listarMascotas();
//        } catch(ListaVaciaExcepcion e) {
//            model.put("msg-error", "No hay registrado ningun animal");
//        }

        model.put("listarmascotas", listaDeMascotas);

        return new ModelAndView("listaMascotas", model);



    }
}
