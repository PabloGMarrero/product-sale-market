package ar.edu.unq.product_sale.infrastructure.web.in.dto.error;

import lombok.Getter;

@Getter
public abstract class ErrorResponseDTO<T> {

    private T message;

    public ErrorResponseDTO(T message) {
        this.message = message;
    }
}
