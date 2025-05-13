package ar.edu.unq.product_sale.domain.model;

public class NotStockEnoughException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Not stock enough for product with id %s.";

    public NotStockEnoughException(String id) {
        super(String.format(ERROR_MESSAGE, id));
    }
}
