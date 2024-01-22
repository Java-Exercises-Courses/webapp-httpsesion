package repositories;

import models.entities.Usuario;

import java.util.List;

public interface UsuarioRepository {
    Usuario byUsername(String username) throws Exception;
    Usuario byId(Long id) throws Exception;
    List<Usuario> listar() throws Exception;

    void save(Usuario usuario) throws Exception;
    void delete(Long id) throws Exception;
}
