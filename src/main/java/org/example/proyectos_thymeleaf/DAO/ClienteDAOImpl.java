package org.example.proyectos_thymeleaf.DAO;

import org.example.proyectos_thymeleaf.Model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Cliente mapRow(ResultSet rs, int rowNum) throws java.sql.SQLException {
        Cliente c = new Cliente();
        c.setCliente_id(rs.getInt("cliente_id"));
        c.setNombre_completo(rs.getString("nombre_completo"));
        c.setDireccion(rs.getString("direccion"));
        c.setTelefono(rs.getString("telefono"));
        c.setFecha_nacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
        return c;
    }

    @Override
    public List<Cliente> listarClientes() {
        return jdbcTemplate.query("SELECT * FROM cliente", this::mapRow);
    }

    @Override
    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT * FROM cliente WHERE cliente_id = ?";
        List<Cliente> lista = jdbcTemplate.query(sql, this::mapRow, id);
        return lista.isEmpty() ? null : lista.get(0);
    }

    @Override
    public List<Cliente> buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM cliente WHERE nombre_completo LIKE ?";
        return jdbcTemplate.query(sql, this::mapRow, "%" + nombre + "%");
    }

    @Override
    public boolean insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO cliente (cliente_id, nombre_completo, direccion, telefono, fecha_nacimiento) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                cliente.getCliente_id(),
                cliente.getNombre_completo(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                java.sql.Date.valueOf(cliente.getFecha_nacimiento())) > 0;
    }

    @Override
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nombre_completo=?, direccion=?, telefono=?, fecha_nacimiento=? WHERE cliente_id=?";
        return jdbcTemplate.update(sql,
                cliente.getNombre_completo(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                java.sql.Date.valueOf(cliente.getFecha_nacimiento()),
                cliente.getCliente_id()) > 0;
    }

    @Override
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM cliente WHERE cliente_id=?";
        return jdbcTemplate.update(sql, id) > 0;
    }
}
