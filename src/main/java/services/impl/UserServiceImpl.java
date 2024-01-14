package services.impl;

import models.Usuario;
import repositories.UsuarioRepository;
import repositories.impl.UsuarioRepositoryImpl;
import services.ServiceException;
import services.UserService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UsuarioRepository usuarioRepository;

    public UserServiceImpl(Connection connection) {
        this.usuarioRepository = new UsuarioRepositoryImpl(connection);
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
