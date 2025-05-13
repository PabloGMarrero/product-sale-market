package ar.edu.unq.product_sale.infrastructure.web.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSearchFilterDTO {

    @NotBlank(message = "filter_name is required")
    @JsonProperty("filter_name")
    private String filterName;

    @JsonProperty("min_price")
    private Double minPrice;

    @JsonProperty("max_price")
    private Double maxPrice;

    private Double price;
}
