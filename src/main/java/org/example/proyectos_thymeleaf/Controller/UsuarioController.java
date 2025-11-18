package org.example.proyectos_thymeleaf.Controller;

import org.example.proyectos_thymeleaf.Model.Usuario;
import org.example.proyectos_thymeleaf.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("nuevoUsuario", new Usuario());
        return "usuarios";
    }

    @PostMapping("/usuarios/agregar")
    public String agregarUsuario(@ModelAttribute("nuevoUsuario") Usuario nuevoUsuario, Model model) {
        boolean insertado = usuarioService.insertarUsuario(nuevoUsuario);

        if (!insertado) {
            model.addAttribute("error", "El usuario ya existe");
        }

        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("nuevoUsuario", new Usuario());
        return "usuarios";
    }
}
