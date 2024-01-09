package services;

import models.Categoria;
import models.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getProducts();
    Optional<ProductDTO> getById(Long id);
    void save(ProductDTO productDTO);
    void eliminar(Long id);
    List<Categoria> listarCategoria();
    Optional<Categoria> getCategoriaById(Long id);
}
