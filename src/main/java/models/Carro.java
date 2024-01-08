package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Carro {
    private List<ItemCarro> items;

    public Carro() {
        this.items = new ArrayList<>();
    }

    public List<ItemCarro> getItems() {
        return items;
    }

    public void addItem(ItemCarro itemCarro) {
        if (items.contains(itemCarro)) {
           Optional<ItemCarro> optionalItemCarro = items
                   .stream()
                   .filter(item -> item.equals(itemCarro))
                   .findAny();

           if (optionalItemCarro.isPresent()) {
               ItemCarro i = optionalItemCarro.get();
               i.setCantidad(i.getCantidad() + 1);
           }
        } else {
            items.add(itemCarro);
        }
    }

    public int getTotal() {
        return items.stream().mapToInt(ItemCarro::getTotal).sum();
    }

    public void removeProductos(List<String> productsIds) {
        if (productsIds != null) {
            productsIds.forEach(this::removeProducto);
        }
    }

    public void removeProducto(String productoId) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> items.remove(itemCarro));
    }

    public void updateCantidad(String productoId, int cantidad) {
        Optional<ItemCarro> producto = findProducto(productoId);
        producto.ifPresent(itemCarro -> itemCarro.setCantidad(cantidad));
    }

    private Optional<ItemCarro> findProducto(String productoId) {
        return items.stream()
                .filter(itemCarro -> productoId.equals(Long.toString(itemCarro.getProductDTO().getId())))
                .findAny();
    }
}
