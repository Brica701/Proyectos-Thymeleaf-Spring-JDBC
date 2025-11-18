package org.example.proyectos_thymeleaf.Service;

import org.example.proyectos_thymeleaf.DAO.ClienteDAO;
import org.example.proyectos_thymeleaf.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.obtenerClientePorId(id);
    }

    public List<Cliente> buscarPorNombre(String nombre) {
        return clienteDAO.buscarPorNombre(nombre);
    }

    public boolean insertarCliente(Cliente cliente) {
        return clienteDAO.insertarCliente(cliente);
    }

    public boolean actualizarCliente(Cliente cliente) {
        return clienteDAO.actualizarCliente(cliente);
    }

    public boolean eliminarCliente(int id) {
        return clienteDAO.eliminarCliente(id);
    }
}