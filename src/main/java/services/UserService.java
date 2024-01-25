package services;

import jakarta.ejb.Local;
import models.entities.Usuario;

import java.util.List;
import java.util.Optional;

@Local
public interface UserService {
    Optional<Usuario> getByUsername(String username, String password);
    List<Usuario> getUsers();

    Optional<Usuario> getById(Long id);
    void save(Usuario usuario);
    void eliminar(Long id);
}
