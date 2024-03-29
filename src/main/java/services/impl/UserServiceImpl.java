package services.impl;

import config.Service;
import interceptors.TransactionalJpa;
import jakarta.inject.Inject;
import models.entities.Usuario;
import repositories.RepositoryJpa;
import repositories.UsuarioRepository;
import services.ServiceException;
import services.UserService;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@TransactionalJpa
public class UserServiceImpl implements UserService {

    private UsuarioRepository usuarioRepository;

    @Inject
    public UserServiceImpl(@RepositoryJpa UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> getByUsername(String username, String password) {
        try {
            return Optional.ofNullable(usuarioRepository.byUsername(username))
                    .filter(usuario -> Objects.equals(usuario.getPassword(), password));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Usuario> getUsers() {
        try {
            return usuarioRepository.listar();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Usuario> getById(Long id) {
        try {
            return Optional.ofNullable(usuarioRepository.byId(id));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(Usuario usuario) {
        try {
            usuarioRepository.save(usuario);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            usuarioRepository.delete(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
