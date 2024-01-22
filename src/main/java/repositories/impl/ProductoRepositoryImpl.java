package repositories.impl;

import config.MysqlConn;
import config.Repository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import models.entities.Categoria;
import models.entities.ProductDTO;
import repositories.ProductRepository;
import repositories.RepositoryJdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RepositoryJdbc
public class ProductoRepositoryImpl implements ProductRepository<ProductDTO> {

    @Inject
    @MysqlConn
    private Connection conn;

    public ProductoRepositoryImpl() {}

    @PostConstruct
    public void init() {
        System.out.println("Inicializando el bean " + this.getClass().getName());
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Destruyendo el bean " + this.getClass().getName());
    }

    @Override
    public List<ProductDTO> listar() throws SQLException {
        List<ProductDTO> productDTOS = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.name as categoria FROM productos as p " +
                    " INNER JOIN categorias as c ON p.category_id = c.id ORDER BY p.id ASC")) {

            while (rs.next()) {
                ProductDTO p = getProductDTO(rs);
                productDTOS.add(p);
            }
        }
        return productDTOS;
    }

    @Override
    public ProductDTO getById(Long id) throws SQLException {
        ProductDTO productDTO = null;
        try (PreparedStatement stmt = conn.prepareStatement("SELECT p.*, c.name as categoria FROM productos as p " +
                "INNER JOIN categorias as c on p.category_id = c.id WHERE p.id = ?")){
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    productDTO = getProductDTO(rs);
                }
            }
        }
        return productDTO;
    }

    @Override
    public void save(ProductDTO productDTO) throws SQLException {
        String sql;
        if (productDTO.getId() != null && productDTO.getId() > 0) {
            sql = "UPDATE productos set name=?, price=?, sku=?, category_id=? where id=?";
        } else {
            sql = "INSERT INTO productos (name, price, sku, category_id, registry_date) VALUES (?,?,?,?,?)";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, productDTO.getName());
            stmt.setInt(2, productDTO.getPrice());
            stmt.setString(3, productDTO.getSku());
            stmt.setLong(4, productDTO.getCategoria().getId());

            if (productDTO.getId() != null && productDTO.getId() > 0) {
                stmt.setLong(5, productDTO.getId());
            } else {
                stmt.setDate(5, Date.valueOf(productDTO.getFechaRegistro()));
            }

            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminar(Long id) throws SQLException {
        String sql = "DELETE FROM productos WHERE id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

    private ProductDTO getProductDTO(ResultSet rs) throws SQLException {
        ProductDTO p = new ProductDTO();
        Categoria c = new Categoria();
        c.setId(rs.getLong("category_id"));
        c.setNombre(rs.getString("categoria"));

        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getInt("price"));
        p.setSku(rs.getString("sku"));
        p.setFechaRegistro(rs.getDate("registry_date").toLocalDate());
        p.setCategoria(c);
        return p;
    }

}
