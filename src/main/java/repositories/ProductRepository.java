package repositories;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository<T> {
    List<T> listar() throws SQLException;
    T getById(Long id) throws SQLException;
    void save(T t) throws SQLException;
    void eliminar(Long id) throws SQLException;
}
