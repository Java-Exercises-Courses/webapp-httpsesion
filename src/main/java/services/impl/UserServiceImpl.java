package services.impl;

import config.Service;
import jakarta.inject.Inject;
import models.Usuario;
import repositories.UsuarioRepository;
import services.ServiceException;
import services.UserService;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UsuarioRepository usuarioRepository;

    @Inject
    public UserServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> getByUsername(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.byUsername(username))
                    .filter(usuario -> Objects.equals(usuario.getPassword(), password));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Usuario> getUsers() {
        try {
            return usuarioRepository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> getById(Long id) {
        try {
            return Optional.ofNullable(usuarioRepository.byId(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            usuarioRepository.delete(id);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
