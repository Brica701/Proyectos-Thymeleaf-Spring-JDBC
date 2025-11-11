package org.example.proyectos_thymeleaf.Controller;

import org.example.proyectos_thymeleaf.DAO.UserDAO;
import org.example.proyectos_thymeleaf.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", userDAO.listarUsuarios());
        return "usuarios";
    }

    @PostMapping("/usuarios")
    public String agregarUsuario(User user) {
        userDAO.agregarUsuario(user);
        return "redirect:/usuarios";
    }
}
