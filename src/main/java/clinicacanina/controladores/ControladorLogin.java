package clinicacanina.controladores;


import clinicacanina.modelo.Medico;
import clinicacanina.modelo.Turno;
import clinicacanina.modelo.Usuario;
import clinicacanina.servicios.ServicioLogin;
import clinicacanina.servicios.ServicioMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class ControladorLogin {

    private ServicioLogin servicioLogin;

    @Autowired
    public ControladorLogin(ServicioLogin servicioLogin){
        this.servicioLogin = servicioLogin;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/login");}


    @RequestMapping("/login")
    public ModelAndView irALogin(HttpSession session) {
        ModelMap mapa = new ModelMap();
        mapa.put("datosLogin", new DatosLogin());
        return new ModelAndView("login",mapa);
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("datosLogin") DatosLogin datosLogin, HttpServletRequest request ) {
        ModelMap model = new ModelMap();
        // invoca el metodo consultarUsuario del servicio y hace un redirect a la URL /home, esto es, en lugar de enviar a una vista
        Usuario usuarioBuscado = servicioLogin.consultarUsuario(datosLogin.getEmail(), datosLogin.getPassword());
        if (usuarioBuscado != null) {
            request.getSession().setAttribute("userId", usuarioBuscado.getId());
            request.getSession().setAttribute("nombreUsuario", usuarioBuscado.getEmail());
            model.put("turno", new Turno());
            return new ModelAndView("redirect:/usuarioHome");
        } else {
            // si el usuario no existe agrega un mensaje de error en el modelo.
            model.put("error", "Usuario o clave incorrecta");
        }
        return new ModelAndView("login", model);
    }

    @RequestMapping(path = "/cerrarSesion")
    public ModelAndView cerrarSesion(HttpServletRequest request) {
        request.getSession().removeAttribute("userId");
        return new ModelAndView("redirect:/login");
    }

}
