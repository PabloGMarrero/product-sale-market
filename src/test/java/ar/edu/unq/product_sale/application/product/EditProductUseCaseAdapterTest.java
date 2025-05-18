package ar.edu.unq.product_sale.application.product;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.ProductEditDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditProductUseCaseAdapterTest {

    @InjectMocks
    private EditProductUseCaseAdapter editProductUseCaseAdapter;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Test
    public void editProductTest() {

        Product mockProduct = mock(Product.class);
        when(productRepositoryPort.findByIdAndDeletedFalse("mockProductId")).thenReturn(Optional.of(mockProduct));
        when(productRepositoryPort.save(mockProduct)).thenReturn(mockProduct);

        ProductEditDTO mockProductEditDTO = mock(ProductEditDTO.class);
        when(mockProductEditDTO.getDescription()).thenReturn("mockDescription");
        when(mockProductEditDTO.getPrice()).thenReturn(1000.0);
        when(mockProductEditDTO.getStock()).thenReturn(100);

        Product returnedProduct = editProductUseCaseAdapter.editProduct("mockProductId", mockProductEditDTO);

        assertEquals(returnedProduct, mockProduct);
        verify(productRepositoryPort, times(1)).findByIdAndDeletedFalse("mockProductId");
        verify(productRepositoryPort, times(1)).save(mockProduct);
    }

    @Test
    public void editNonExistentProductTest() {
        when(productRepositoryPort.findByIdAndDeletedFalse("mockProductId")).thenReturn(Optional.empty());

        ProductEditDTO mockProductEditDTO = mock(ProductEditDTO.class);
        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () ->  {
            editProductUseCaseAdapter.editProduct("mockProductId", mockProductEditDTO);
        });

        assertEquals("Product with Id: mockProductId not found", exception.getMessage());
        verify(productRepositoryPort, times(1)).findByIdAndDeletedFalse("mockProductId");
        verify(productRepositoryPort, never()).save(any(Product.class));
    }
}