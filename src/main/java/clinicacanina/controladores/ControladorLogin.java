package clinicacanina.controladores;


import clinicacanina.modelo.Medico;
import clinicacanina.servicios.ServicioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorLogin {

    private ServicioMedico servicioMedico;


    @Autowired
    public ControladorLogin(ServicioMedico servicioMedico){
        this.servicioMedico = servicioMedico;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");}




    @RequestMapping("/login")
    public ModelAndView irALogin() {

        ModelMap modelo = new ModelMap();

        modelo.put("datosLogin", new DatosLogin());

        return new ModelAndView("login", modelo);
    }


    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin ) {
        ModelMap model = new ModelMap();

        Medico adminBuscado = servicioMedico.buscarMedicoLogin(datosLogin.getDni(), datosLogin.getContrasenia());
        if (adminBuscado != null) {

            return new ModelAndView("redirect:/listar-mascotas");
        } else {
            model.put("error", "Usuario o clave incorrecta");
        }
        return new ModelAndView("login", model);
    }

}
