package ar.edu.unq.product_sale.infrastructure.web.in;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.application.exceptions.FilterNotSupportedException;
import ar.edu.unq.product_sale.application.exceptions.ProductAlreadyExistsException;
import ar.edu.unq.product_sale.domain.model.NotStockEnoughException;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.error.GenericErrorResponseDTO;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.error.ValidationErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleUserNotFoundException(ElementNotFoundException elementNotFoundException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDTO(elementNotFoundException.getMessage()));
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleProductAlreadyExistsException(ProductAlreadyExistsException productAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDTO(productAlreadyExistsException.getMessage()));
    }

    @ExceptionHandler(FilterNotSupportedException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleFilterNotSupportedException(FilterNotSupportedException filterNotSupportedException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDTO(filterNotSupportedException.getMessage()));
    }

    @ExceptionHandler(NotStockEnoughException.class)
    public ResponseEntity<GenericErrorResponseDTO> handleNotStockEnoughException(NotStockEnoughException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDTO(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> errors = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorResponseDTO(errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericErrorResponseDTO> handleGenericException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GenericErrorResponseDTO(exception.getMessage()));
    }
}
