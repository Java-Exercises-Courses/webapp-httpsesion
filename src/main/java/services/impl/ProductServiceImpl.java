package services.impl;

import config.Service;
import jakarta.ejb.Stateful;
import jakarta.inject.Inject;
import models.entities.Categoria;
import models.entities.ProductDTO;
import repositories.CategoriaRepository;
import repositories.ProductRepository;
import repositories.RepositoryJpa;
import services.ProductService;
import services.ServiceException;

import java.util.List;
import java.util.Optional;

@Service
@Stateful
public class ProductServiceImpl implements ProductService {

    @Inject
    @RepositoryJpa
    private ProductRepository<ProductDTO> productRepository;
    @Inject
    @RepositoryJpa
    private CategoriaRepository categoriaRepository;

    public ProductServiceImpl() {}

    @Override
    public List<ProductDTO> getProducts() {
        try {
            return productRepository.listar();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        try {
            return Optional.ofNullable(productRepository.getById(id));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(ProductDTO productDTO) {
        try {
            productRepository.save(productDTO);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            productRepository.eliminar(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return categoriaRepository.listar();
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> getCategoriaById(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.getById(id));
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
