package ar.edu.unq.product_sale.application.product;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteProductUseCaseAdapterTest {

    @InjectMocks
    private DeleteProductUseCaseAdapter deleteProductUseCaseAdapter;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Test
    public void deleteProductTest() {
        Product mockProduct = mock(Product.class);
        when(productRepositoryPort.findById(anyString())).thenReturn(Optional.of(mockProduct));
        when(mockProduct.getDeleted()).thenReturn(false);
        when(productRepositoryPort.save(mockProduct)).thenReturn(mockProduct);

        deleteProductUseCaseAdapter.deleteProduct("mockProductId");

        verify(productRepositoryPort, times(1)).findById(anyString());
        verify(mockProduct, times(1)).markAsDeleted();
        verify(productRepositoryPort, times(1)).save(mockProduct);
    }

    @Test
    public void deleteNonExistentProductTest() {
        Product mockProduct = mock(Product.class);
        when(productRepositoryPort.findById(anyString())).thenReturn(Optional.empty());

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            deleteProductUseCaseAdapter.deleteProduct("mockProductId");
        });

        assertEquals("Product with Id: mockProductId not found", exception.getMessage());
        verify(productRepositoryPort, times(1)).findById(anyString());
        verify(mockProduct, never()).markAsDeleted();
        verify(productRepositoryPort, never()).save(mockProduct);
    }

    @Test
    public void deleteAlreadyDeletedProductTest() {
        Product mockProduct = mock(Product.class);
        when(productRepositoryPort.findById(anyString())).thenReturn(Optional.of(mockProduct));
        when(mockProduct.getDeleted()).thenReturn(true);

        deleteProductUseCaseAdapter.deleteProduct("mockProductId");

        verify(productRepositoryPort, times(1)).findById(anyString());
        verify(mockProduct, never()).markAsDeleted();
        verify(productRepositoryPort, never()).save(mockProduct);
    }
}