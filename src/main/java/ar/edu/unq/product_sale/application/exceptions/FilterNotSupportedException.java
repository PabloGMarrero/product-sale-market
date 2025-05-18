package ar.edu.unq.product_sale.application.exceptions;

public class FilterNotSupportedException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Filter with name %s is not supported";

    public FilterNotSupportedException(String filterName) {
        super(String.format(ERROR_MESSAGE, filterName));
    }
}
