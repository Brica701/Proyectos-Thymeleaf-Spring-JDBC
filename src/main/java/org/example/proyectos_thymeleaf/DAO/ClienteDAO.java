package org.example.proyectos_thymeleaf.DAO;

import org.example.proyectos_thymeleaf.Model.Cliente;
import java.util.List;

public interface ClienteDAO {
    List<Cliente> listarClientes();
    Cliente obtenerClientePorId(int id);
    List<Cliente> buscarPorNombre(String nombre);
    boolean insertarCliente(Cliente cliente);
    boolean actualizarCliente(Cliente cliente);
    boolean eliminarCliente(int id);
}
