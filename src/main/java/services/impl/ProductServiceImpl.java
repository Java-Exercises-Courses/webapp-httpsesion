package services.impl;

import models.ProductDTO;
import services.ProductService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDTO> getProducts() {
        return Arrays.asList(
                new ProductDTO(1L, "Teclado", 200),
                new ProductDTO(2L, "Monitor", 500)
        );
    }

    @Override
    public Optional<ProductDTO> getById(Long id) {
        return getProducts().stream().filter(productDTO -> productDTO.getId().equals(id)).findAny();
    }
}
