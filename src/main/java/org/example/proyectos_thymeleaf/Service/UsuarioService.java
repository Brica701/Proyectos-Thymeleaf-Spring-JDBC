package org.example.proyectos_thymeleaf.Service;

import org.example.proyectos_thymeleaf.DAO.UsuarioDAO;
import org.example.proyectos_thymeleaf.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDAO usuarioDAO;

    public List<Usuario> listarUsuarios() {
        return usuarioDAO.listarUsuarios();
    }

    public boolean insertarUsuario(Usuario usuario) {
        return usuarioDAO.insertarUsuario(usuario);
    }
}