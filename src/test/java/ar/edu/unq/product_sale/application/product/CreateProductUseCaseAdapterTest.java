package ar.edu.unq.product_sale.application.product;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.application.exceptions.ProductAlreadyExistsException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.domain.port.out.SellerRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.ProductCreateDTO;
import ar.edu.unq.product_sale.infrastructure.web.out.dto.SellerDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateProductUseCaseAdapterTest {

    @InjectMocks
    private CreateProductUseCaseAdapter createProductUseCaseAdapter;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private SellerRepositoryPort sellerRepositoryPort;

    @Mock
    private ProductCreateDTO productCreateDTO;

    @Test
    public void createProductTest() {
        SellerDTO mockSellerDTO = mock(SellerDTO.class);
        when(sellerRepositoryPort.findById(anyString())).thenReturn(Optional.of(mockSellerDTO));

        when(productRepositoryPort.existsByNameAndSellerId(anyString(), anyString())).thenReturn(false);

        Product mockProduct = mock(Product.class);
        when(productRepositoryPort.save(any(Product.class))).thenReturn(mockProduct);

        when(productCreateDTO.getName()).thenReturn("mockProductName");
        when(productCreateDTO.getSellerId()).thenReturn("mockSellerId");
        Product returnedProduct = createProductUseCaseAdapter.createProduct(productCreateDTO);

        assertEquals(mockProduct, returnedProduct);
        verify(sellerRepositoryPort, times(1)).findById(anyString());
        verify(productRepositoryPort, times(1)).existsByNameAndSellerId(anyString(), anyString());
        verify(productRepositoryPort, times(1)).save(any(Product.class));
    }

    @Test
    public void createProductForInexistentSellerTest() {
        when(sellerRepositoryPort.findById(anyString())).thenReturn(Optional.empty());

        when(productCreateDTO.getSellerId()).thenReturn("mockSellerId");
        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            createProductUseCaseAdapter.createProduct(productCreateDTO);
        });

        assertEquals("Seller with Id: mockSellerId not found", exception.getMessage());
        verify(sellerRepositoryPort, times(1)).findById(anyString());
        verify(productRepositoryPort, never()).existsByNameAndSellerId(anyString(), anyString());
        verify(productRepositoryPort, never()).save(any(Product.class));
    }

    @Test
    public void createProductForSellerWithExistingProductWithSameNameTest() {
        SellerDTO mockSellerDTO = mock(SellerDTO.class);
        when(sellerRepositoryPort.findById(anyString())).thenReturn(Optional.of(mockSellerDTO));

        when(productRepositoryPort.existsByNameAndSellerId(anyString(), anyString())).thenReturn(true);

        when(productCreateDTO.getName()).thenReturn("mockProductName");
        when(productCreateDTO.getSellerId()).thenReturn("mockSellerId");
        ProductAlreadyExistsException exception = assertThrows(ProductAlreadyExistsException.class, () -> {
            createProductUseCaseAdapter.createProduct(productCreateDTO);
        });

        assertEquals("Seller with id mockSellerId already has a registered product with name mockProductName.", exception.getMessage());
        verify(sellerRepositoryPort, times(1)).findById(anyString());
        verify(productRepositoryPort, times(1)).existsByNameAndSellerId(anyString(), anyString());
        verify(productRepositoryPort, never()).save(any(Product.class));
    }
}