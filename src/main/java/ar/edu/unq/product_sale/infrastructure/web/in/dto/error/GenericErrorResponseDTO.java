package ar.edu.unq.product_sale.infrastructure.web.in.dto.error;

public class GenericErrorResponseDTO extends ErrorResponseDTO<String> {
    public GenericErrorResponseDTO(String message) {
        super(message);
    }
}
