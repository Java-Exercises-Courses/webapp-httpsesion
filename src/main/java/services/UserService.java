package services;

import models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<Usuario> getByUsername(String username, String password);
    List<Usuario> getUsers();
}
