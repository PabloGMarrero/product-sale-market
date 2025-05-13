package ar.edu.unq.product_sale.infrastructure.web.dto.sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleResponseDTO {

    private String id;

    @JsonProperty("product_id")
    private String productId;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("sale_value")
    private Double saleValue;
}
