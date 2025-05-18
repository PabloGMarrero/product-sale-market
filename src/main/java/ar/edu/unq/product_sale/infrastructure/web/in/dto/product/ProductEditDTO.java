package ar.edu.unq.product_sale.infrastructure.web.in.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductEditDTO {

    @NotBlank(message = "Description is required")
    @Size(min = 2, max = 150, message = "Description length must be between 2 and 150 characters.")
    private String description;

    @NotBlank(message = "Category is required")
    @Size(min = 2, max = 150, message = "Category length must be between 2 and 150 characters.")
    private String category;

    @NotNull(message = "Price is required.")
    @Positive(message = "Price must be greater than 0.")
    private Double price;

    @NotNull(message = "Stock is required.")
    @Positive(message = "Stock must be greater than 0.")
    private Integer stock;
}
