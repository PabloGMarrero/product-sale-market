package ar.edu.unq.product_sale.application.exceptions;

public class ElementNotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "%s with Id: %s not found";

    public ElementNotFoundException(String elementNotFoundName, String elementNotFoundId) {
        super(String.format(ERROR_MESSAGE, elementNotFoundName, elementNotFoundId));
    }
}
