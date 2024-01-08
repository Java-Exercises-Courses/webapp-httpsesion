package repositories.impl;

import models.ProductDTO;
import repositories.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositoryImpl implements Repository<ProductDTO> {
    private Connection conn;

    public ProductoRepositoryImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<ProductDTO> listar() throws SQLException {
        List<ProductDTO> productDTOS = new ArrayList<>();
        try(Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT p.*, c.name as categoria FROM productos as p " +
                    " INNER JOIN categorias as c ON p.category_id = c.id")) {

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

    }

    @Override
    public void eliminar(Long id) throws SQLException {

    }

    private ProductDTO getProductDTO(ResultSet rs) throws SQLException {
        ProductDTO p = new ProductDTO();
        p.setId(rs.getLong("id"));
        p.setName(rs.getString("name"));
        p.setPrice(rs.getInt("price"));
        p.setCategoria(rs.getString("categoria"));
        return p;
    }

}
