package org.example.proyectos_thymeleaf.Controller;

import org.example.proyectos_thymeleaf.DAO.UsuarioDAO;
import org.example.proyectos_thymeleaf.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            if ("admin".equalsIgnoreCase(usuarioValido.getUsuario())) {
                // Ir al CRUD de usuarios
                return "redirect:/usuarios";
            } else {
                model.addAttribute("nombre", usuarioValido.getUsuario());
                return "bienvenida";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @PostMapping("/piramide")
    public String generarPiramide(@RequestParam int altura,
                                  @RequestParam(required = false) String nombre,
                                  Model model) {

        String gatoImg = "<img class='cat' src='https://cdn-icons-png.flaticon.com/512/616/616408.png' alt='gato'>";
        StringBuilder piramide = new StringBuilder();

        for (int i = 1; i <= altura; i++) {
            piramide.append("<div>");
            for (int j = 1; j <= i; j++) {
                piramide.append(gatoImg);
            }
            piramide.append("</div>");
        }

        model.addAttribute("nombre", nombre);
        model.addAttribute("piramide", piramide.toString());

        return "bienvenida";
    }
}
