package org.example.proyectos_thymeleaf.DAO;

import org.example.proyectos_thymeleaf.Model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO {

    private JdbcTemplate jdbcTemplate;

    public UsuarioDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Usuario validarUsuario(String usuario, String contrasena) {
        String hash = sha256(contrasena);

        String sql = "SELECT * FROM usuario WHERE usuario = ? AND contrasena = ?";
        List<Usuario> usuarios = jdbcTemplate.query(sql, new Object[]{usuario, hash},
                (ResultSet rs, int rowNum) -> {
                    var u = new Usuario();
                    u.setUsuario(rs.getString("usuario"));
                    u.setContrasena(rs.getString("contrasena"));
                    return u;
                });
        return usuarios.isEmpty() ? null : usuarios.get(0);
    }

    @Override
    public List<Usuario> listarUsuarios() {
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Usuario u = new Usuario();
            u.setUsuario(rs.getString("usuario"));
            u.setContrasena(rs.getString("contrasena"));
            return u;
        });
    }

    @Override
    public boolean existeUsuario(String usuario) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE usuario = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, usuario);
        return count != null && count > 0;
    }

    @Override
    public boolean insertarUsuario(Usuario usuario) {
        if (existeUsuario(usuario.getUsuario())) return false;
        String hash = sha256(usuario.getContrasena());
        String sql = "INSERT INTO usuario (usuario, contrasena) VALUES (?, ?)";
        return jdbcTemplate.update(sql, usuario.getUsuario(), hash) > 0;
    }

    private String sha256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(texto.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
