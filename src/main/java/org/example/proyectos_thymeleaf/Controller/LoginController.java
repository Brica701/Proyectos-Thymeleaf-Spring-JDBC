package org.example.proyectos_thymeleaf.Controller;

import org.example.proyectos_thymeleaf.DAO.UsuarioDAO;
import org.example.proyectos_thymeleaf.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UsuarioDAO usuarioDAO;

    @GetMapping("/")
    public String mostrarLogin(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "login";
    }

    @PostMapping("/login")
    public String procesarLogin(@ModelAttribute Usuario usuario, Model model) {
        var usuarioValido = usuarioDAO.validarUsuario(usuario.getUsuario(), usuario.getContrasena());

        if (usuarioValido != null) {
            model.addAttribute("nombre", usuarioValido.getUsuario());
            return "bienvenida";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }
}
