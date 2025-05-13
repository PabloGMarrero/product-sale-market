package ar.edu.unq.product_sale.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Product {

    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer stock;
    private String sellerId;
    private Boolean deleted;

    public Product(String id, String name, String description, String category, Double price, Integer stock, String sellerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.sellerId = sellerId;
        this.deleted = false;
    }

    public void markAsDeleted() {
        this.deleted = true;
    }

    public void removeFromStock(Integer amount) {
        Integer newStock = this.stock - amount;

        if(newStock < 0){
            throw new NotStockEnoughException(this.id);
        }

        setStock(newStock);
    }
}
