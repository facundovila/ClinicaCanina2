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
import org.springframework.web.bind.annotation.RequestParam;
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




    @RequestMapping(path = "/listar-mascotas" , method = RequestMethod.GET)
    public ModelAndView listarMascotas() {

        ModelMap model= new ModelMap();

        List<Mascota> listaDeMascotas = servicioMascota.listarMascotas();

        if(listaDeMascotas.isEmpty()){
            model.put("sinMascotas", "no hay pacientes mascotas");
        }


        model.put("listarmascotas", listaDeMascotas);

        return new ModelAndView("listaMascotas", model);



    }


    @RequestMapping(path = "/historia-clinica", method = RequestMethod.GET)
    public ModelAndView irAHistoriaClinica(@RequestParam("idMascota") Long idMascota) {

        ModelMap model= new ModelMap();


        Mascota mascota = servicioMascota.buscarMascotaPorId(idMascota);


        model.put("historiaclinica", mascota);

        return new ModelAndView("historiaClinica", model);



    }
}
