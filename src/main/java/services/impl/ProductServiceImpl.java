package services.impl;

import models.Categoria;
import models.ProductDTO;
import repositories.CategoriaRepository;
import repositories.Repository;
import repositories.impl.CategoriaRepositoryImpl;
import repositories.impl.ProductoRepositoryImpl;
import services.ProductService;
import services.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final Repository<ProductDTO> repository;
    private final CategoriaRepository categoriaRepository;

    public ProductServiceImpl(Connection connection) {
        this.repository = new ProductoRepositoryImpl(connection);
        this.categoriaRepository = new CategoriaRepositoryImpl(connection);
    }

    @Override
    public List<ProductDTO> getProducts() {
        try {
            return repository.listar();
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        try {
            return Optional.ofNullable(repository.getById(id));
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void save(ProductDTO productDTO) {
        try {
            repository.save(productDTO);
        } catch (SQLException e) {
            throw new ServiceException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            repository.eliminar(id);
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
