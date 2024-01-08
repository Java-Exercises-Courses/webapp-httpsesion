package models;

import java.util.Objects;

public class ItemCarro {
    private int cantidad;
    private ProductDTO productDTO;

    public ItemCarro(int cantidad, ProductDTO productDTO) {
        this.cantidad = cantidad;
        this.productDTO = productDTO;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public int getTotal() {
        return cantidad * productDTO.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemCarro itemCarro = (ItemCarro) o;
        return Objects.equals(productDTO.getId(), itemCarro.productDTO.getId())
                && Objects.equals(productDTO.getName(), itemCarro.productDTO.getName());
    }

}
