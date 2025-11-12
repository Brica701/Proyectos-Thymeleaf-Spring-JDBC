package org.example.proyectos_thymeleaf.DAO;

import org.example.proyectos_thymeleaf.Model.Usuario;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioDAO {
    Usuario validarUsuario(String usuario, String contrasena);
    List<Usuario> listarUsuarios();
    boolean insertarUsuario(Usuario usuario);
    boolean existeUsuario(String usuario);
}
