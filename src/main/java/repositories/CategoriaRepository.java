package repositories;

import models.Categoria;

import java.sql.SQLException;
import java.util.List;

public interface CategoriaRepository {
    List<Categoria> listar() throws SQLException;
    Categoria getById(Long id) throws SQLException;
}
