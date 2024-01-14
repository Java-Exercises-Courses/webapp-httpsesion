package repositories;

import models.Usuario;

import java.sql.SQLException;
import java.util.List;

public interface UsuarioRepository {
    Usuario byUsername(String username) throws SQLException;
    Usuario byId(Long id) throws SQLException;
    List<Usuario> listar() throws SQLException;

    void save(Usuario usuario) throws SQLException;
    void delete(Long id) throws SQLException;
}
