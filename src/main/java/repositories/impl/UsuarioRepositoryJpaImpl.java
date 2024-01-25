package repositories.impl;

import config.Repository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import models.entities.Usuario;
import repositories.RepositoryJpa;
import repositories.UsuarioRepository;

import java.util.List;

@RepositoryJpa
@Repository
public class UsuarioRepositoryJpaImpl implements UsuarioRepository {

    @Inject
    EntityManager entityManager;

    @Override
    public Usuario byUsername(String username) throws Exception {
        return entityManager.createQuery("select u from Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public Usuario byId(Long id) throws Exception {
        return entityManager.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> listar() throws Exception {
        return entityManager.createQuery("select u from Usuario u", Usuario.class).getResultList();
    }

    @Override
    public void save(Usuario usuario) throws Exception {
        if (usuario.getId() != null && usuario.getId() > 0) {
            entityManager.merge(usuario);
        } else {
            entityManager.persist(usuario);
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        entityManager.remove(byId(id));
    }
}
