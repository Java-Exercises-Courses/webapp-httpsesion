package repositories.impl;

import config.Repository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import models.entities.Categoria;
import repositories.CategoriaRepository;
import repositories.RepositoryJpa;

import java.util.List;

@RepositoryJpa
@Repository
public class CategoriaRepositoryJpaImpl implements CategoriaRepository {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<Categoria> listar() throws Exception {
        return entityManager.createQuery("select c from Categoria c", Categoria.class).getResultList();
    }

    @Override
    public Categoria getById(Long id) throws Exception {
        return entityManager.find(Categoria.class, id);
    }
}
