package ar.edu.unq.product_sale.infrastructure.web.dto.sale;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SaleCreateDTO {

    @NotBlank(message = "Product ID is required")
    @Size(min = 2, max = 50, message = "Product ID length must be between 2 and 50 characters.")
    @JsonProperty("product_id")
    private String productId;

    @NotBlank(message = "User ID is required")
    @Size(min = 2, max = 50, message = "User ID length must be between 2 and 50 characters.")
    @JsonProperty("user_id")
    private String userId;

    @NotNull(message = "Amount is required.")
    @Positive(message = "Amount must be greater than 0.")
    private Integer amount;
}
