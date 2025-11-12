package org.example.proyectos_thymeleaf.Controller;

import org.example.proyectos_thymeleaf.DAO.ClienteDAO;
import org.example.proyectos_thymeleaf.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteDAO clienteDAO;

    // --- Listado general ---
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteDAO.listarClientes());
        return "clientes";
    }

    // --- Buscar por nombre ---
    @PostMapping("/buscar")
    public String buscar(@RequestParam String nombre, Model model) {
        List<Cliente> resultados = clienteDAO.buscarPorNombre(nombre);
        if (resultados.size() == 1) {
            model.addAttribute("cliente", resultados.get(0));
            return "cliente_detalle";
        } else {
            model.addAttribute("clientes", resultados);
            model.addAttribute("busqueda", nombre);
            return "clientes";
        }
    }

    // --- Formulario de alta ---
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente_form";
    }

    // --- Insertar cliente ---
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Cliente cliente) {
        clienteDAO.insertarCliente(cliente);
        return "redirect:/clientes";
    }

    // --- Editar cliente ---
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        model.addAttribute("cliente", clienteDAO.obtenerClientePorId(id));
        return "cliente_form";
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Cliente cliente) {
        clienteDAO.actualizarCliente(cliente);
        return "redirect:/clientes";
    }

    // --- Eliminar cliente ---
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable int id) {
        clienteDAO.eliminarCliente(id);
        return "redirect:/clientes";
    }
}
