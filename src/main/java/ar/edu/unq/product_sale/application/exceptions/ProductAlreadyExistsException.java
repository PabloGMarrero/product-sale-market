package ar.edu.unq.product_sale.application.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Seller with id %s already has a registered product with name %s.";

    public ProductAlreadyExistsException(String productName, String sellerId) {
        super(String.format(ERROR_MESSAGE, productName, sellerId));
    }
}
