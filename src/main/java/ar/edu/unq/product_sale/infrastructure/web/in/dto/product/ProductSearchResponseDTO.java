package ar.edu.unq.product_sale.infrastructure.web.in.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProductSearchResponseDTO {

    @JsonProperty("search_result")
    private List<ProductResponseDTO> searchResult;
}
