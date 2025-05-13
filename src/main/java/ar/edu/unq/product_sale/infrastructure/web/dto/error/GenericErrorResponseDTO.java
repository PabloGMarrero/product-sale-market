package ar.edu.unq.product_sale.infrastructure.web.dto.error;

public class GenericErrorResponseDTO extends ErrorResponseDTO<String> {
    public GenericErrorResponseDTO(String message) {
        super(message);
    }
}
