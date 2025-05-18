package ar.edu.unq.product_sale.infrastructure.web.in.dto.error;

import java.util.Map;

public class ValidationErrorResponseDTO extends ErrorResponseDTO<Map<String, String>> {
    public ValidationErrorResponseDTO(Map<String, String> errors) {
        super(errors);
    }
}
