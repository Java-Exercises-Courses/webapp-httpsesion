package models;

public class ProductDTO {
    private Long id;
    private String name;
    private String categoria;
    private int price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, String categoria, int price) {
        this.id = id;
        this.name = name;
        this.categoria = categoria;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
