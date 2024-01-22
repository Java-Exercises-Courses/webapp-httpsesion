package repositories;

import models.entities.Categoria;

import java.util.List;

public interface CategoriaRepository {
    List<Categoria> listar() throws Exception;
    Categoria getById(Long id) throws Exception;
}
