package services;

import models.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getProducts();
    Optional<ProductDTO> getById(Long id);
}
