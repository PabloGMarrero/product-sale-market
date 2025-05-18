package ar.edu.unq.product_sale.infrastructure.web.in.dto.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProductCreateDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name length must be between 2 and 50 characters.")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 150, message = "Description length must be between 2 and 150 characters.")
    private String description;

    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 50, message = "Category length must be between 2 and 50 characters.")
    private String category;

    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be grater than 0.")
    private Double price;

    @NotNull(message = "Stock is required.")
    @Positive(message = "Stock must be greater than 0.")
    private Integer stock;

    @NotBlank(message = "Seller ID is required")
    @JsonProperty("seller_id")
    private String sellerId;
}
