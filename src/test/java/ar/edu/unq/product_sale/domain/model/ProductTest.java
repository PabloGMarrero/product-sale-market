package ar.edu.unq.product_sale.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    @Test
    public void markAsDeletedTest() {
        Product product = new Product(
                "123",
                "testProduct",
                "description",
                "category",
                100.0,
                10,
                "seller123"
        );

        product.markAsDeleted();

        assertTrue(product.getDeleted());
    }

    @Test
    public void removeFromStockSuccessfullyTest() {
        Product product = new Product(
                "123",
                "testProduct",
                "description",
                "category",
                100.0,
                10,
                "seller123"
        );

        product.removeFromStock(3);

        assertEquals(7, product.getStock());
    }

    @Test
    public void removeFromStockExactAmountTest() {
        Product product = new Product(
                "123",
                "testProduct",
                "description",
                "category",
                100.0,
                10,
                "seller123"
        );

        product.removeFromStock(10);

        assertEquals(0, product.getStock());
    }

    @Test
    public void removeFromStockMoreThanAvailableTest() {
        Product product = new Product(
                "123",
                "testProduct",
                "description",
                "category",
                100.0,
                10,
                "seller123"
        );

        NotStockEnoughException exception = assertThrows(NotStockEnoughException.class, () -> {
            product.removeFromStock(11);
        });

        assertEquals("Not stock enough for product with id 123.", exception.getMessage());
        assertEquals(10, product.getStock());
    }

    @Test
    public void removeFromStockNegativeAmountTest() {
        Product product = new Product(
                "123",
                "testProduct",
                "description",
                "category",
                100.0,
                10,
                "seller123"
        );

        NotStockEnoughException exception = assertThrows(NotStockEnoughException.class, () -> {
            product.removeFromStock(15);
        });

        assertEquals("Not stock enough for product with id 123.", exception.getMessage());
        assertEquals(10, product.getStock());
    }
}