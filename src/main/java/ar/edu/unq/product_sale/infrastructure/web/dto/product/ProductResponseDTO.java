package ar.edu.unq.product_sale.infrastructure.web.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {

    private String id;

    private String name;

    private String description;

    private String category;

    private Double price;

    private Integer stock;

    @JsonProperty("seller_id")
    private String sellerId;
}
