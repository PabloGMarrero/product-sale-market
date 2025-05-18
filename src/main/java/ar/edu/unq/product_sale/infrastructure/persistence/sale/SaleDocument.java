package ar.edu.unq.product_sale.infrastructure.persistence.sale;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@Document("sales")
public class SaleDocument {

    private String id;

    @Field("product_id")
    private String productId;

    @Field("user_id")
    private String userId;

    @Field("sale_value")
    private Double saleValue;
}
