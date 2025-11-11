package org.example.proyectos_thymeleaf.DAO;

import org.example.proyectos_thymeleaf.Model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAO {
    private List<User> usuarios = new ArrayList<>();

    public List<User> listarUsuarios() {
        return usuarios;
    }

    public void agregarUsuario(User user) {
        usuarios.add(user);
    }

}
