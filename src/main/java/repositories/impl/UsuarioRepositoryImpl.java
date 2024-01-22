package repositories.impl;

import config.MysqlConn;
import config.Repository;
import jakarta.inject.Inject;
import models.entities.Usuario;
import repositories.RepositoryJdbc;
import repositories.UsuarioRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RepositoryJdbc
public class UsuarioRepositoryImpl implements UsuarioRepository {
    @Inject
    @MysqlConn
    private Connection conn;

    public UsuarioRepositoryImpl() {}

    @Override
    public Usuario byUsername(String username) throws SQLException {
        Usuario usuario = null;
        try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM shopping_car.usuarios WHERE username=?")) {
            stmt.setString(1, username);
            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }
        return usuario;
    }

    @Override
    public Usuario byId(Long id) throws SQLException {
        Usuario usuario = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT  * FROM shopping_car.usuarios WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = getUsuario(rs);
                }
            }
        }

        return usuario;
    }

    @Override
    public List<Usuario> listar() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();

        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from shopping_car.usuarios ORDER BY id")
        ){
            while (rs.next()) {
                Usuario usuario = getUsuario(rs);
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }

    @Override
    public void save(Usuario usuario) throws SQLException {
        String sql;
        if (usuario.getId() != null && usuario.getId() > 0) {
            sql = "UPDATE shopping_car.usuarios set username=?, password=?, email=? WHERE id=?";
        } else {
            sql = "INSERT INTO shopping_car.usuarios (username, password, email) values (?,?,?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());

            if (usuario.getId() != null && usuario.getId() > 0) {
                stmt.setLong(4, usuario.getId());
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Long id) throws SQLException {
        String sql = "DELETE FROM shopping_car.usuarios WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private Usuario getUsuario(ResultSet rs) throws SQLException {
        Usuario usuario;
        usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }
}
