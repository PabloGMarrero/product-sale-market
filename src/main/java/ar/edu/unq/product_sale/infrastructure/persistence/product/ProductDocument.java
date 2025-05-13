package ar.edu.unq.product_sale.infrastructure.persistence.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Document("products")
public class ProductDocument {

    @Id
    private String id;

    private String name;

    private String description;

    private String category;

    private Double price;

    private Integer stock;

    @Field("seller_id")
    private String sellerId;

    private Boolean deleted;
}
