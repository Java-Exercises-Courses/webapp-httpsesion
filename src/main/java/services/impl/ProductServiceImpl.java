package services.impl;

import models.ProductDTO;
import repositories.impl.ProductoRepositoryImpl;
import services.ProductService;
import services.ServiceException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductoRepositoryImpl repository;

    public ProductServiceImpl(Connection connection) {
        this.repository = new ProductoRepositoryImpl(connection);
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
}
