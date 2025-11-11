package org.example.proyectos_thymeleaf.DAO;

import org.example.proyectos_thymeleaf.Model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDAO {
    Usuario validarUsuario(String usuario, String contrasena);
}
