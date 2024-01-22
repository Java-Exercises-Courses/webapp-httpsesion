package repositories;

import java.sql.SQLException;
import java.util.List;

public interface ProductRepository<T> {
    List<T> listar() throws Exception;
    T getById(Long id) throws Exception;
    void save(T t) throws Exception;
    void eliminar(Long id) throws Exception;
}
