package repositories.impl;

import config.Repository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import models.entities.ProductDTO;
import repositories.ProductRepository;
import repositories.RepositoryJpa;

import java.util.List;

@RepositoryJpa
@Repository
public class ProductoRepositoryJpaImpl implements ProductRepository<ProductDTO> {

    @Inject
    private EntityManager entityManager;

    @Override
    public List<ProductDTO> listar() throws Exception {
        return entityManager.createQuery("select p from ProductDTO p left outer join p.categoria", ProductDTO.class).getResultList();
    }

    @Override
    public ProductDTO getById(Long id) throws Exception {
        return entityManager.find(ProductDTO.class, id);
    }

    @Override
    public void save(ProductDTO productDTO) throws Exception {
        if (productDTO.getId() != null && productDTO.getId() > 0 ) {
            entityManager.merge(productDTO);
        } else {
            entityManager.persist(productDTO);
        }
    }

    @Override
    public void eliminar(Long id) throws Exception {
        entityManager.remove(getById(id));
    }
}
