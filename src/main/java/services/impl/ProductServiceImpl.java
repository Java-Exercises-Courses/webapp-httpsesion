package services.impl;

import config.Service;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import models.Categoria;
import models.ProductDTO;
import repositories.CategoriaRepository;
import repositories.ProductRepository;
import services.ProductService;
import services.ServiceException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Inject
    private ProductRepository<ProductDTO> productRepository;
    @Inject
    private CategoriaRepository categoriaRepository;

    public ProductServiceImpl() {}

    @Override
    public List<ProductDTO> getProducts() {
        try {
            return productRepository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        try {
            return Optional.ofNullable(productRepository.getById(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(ProductDTO productDTO) {
        try {
            productRepository.save(productDTO);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            productRepository.eliminar(id);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public List<Categoria> listarCategoria() {
        try {
            return categoriaRepository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Categoria> getCategoriaById(Long id) {
        try {
            return Optional.ofNullable(categoriaRepository.getById(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }
}
