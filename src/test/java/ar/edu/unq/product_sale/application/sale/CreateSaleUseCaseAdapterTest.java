package ar.edu.unq.product_sale.application.sale;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.model.Sale;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.domain.port.out.SaleRepositoryPort;
import ar.edu.unq.product_sale.domain.port.out.UserRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.sale.SaleCreateDTO;
import ar.edu.unq.product_sale.infrastructure.web.out.dto.UserDTO;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateSaleUseCaseAdapterTest {

    @InjectMocks
    private CreateSaleUseCaseAdapter createSaleUseCaseAdapter;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private SaleRepositoryPort saleRepositoryPort;

    @Test
    public void createSaleTest(){
        UserDTO mockUserDTO = mock(UserDTO.class);
        when(userRepositoryPort.findById("mockUserId")).thenReturn(Optional.of(mockUserDTO));

        Product mockProduct = mock(Product.class);
        when(productRepositoryPort.findById("mockProductId")).thenReturn(Optional.of(mockProduct));

        when(productRepositoryPort.save(any(Product.class))).thenReturn(mockProduct);

        Sale mockSale = mock(Sale.class);
        when(saleRepositoryPort.save(any(Sale.class))).thenReturn(mockSale);

        SaleCreateDTO mockSaleCreateDTO = mock(SaleCreateDTO.class);
        when(mockSaleCreateDTO.getAmount()).thenReturn(10);
        when(mockSaleCreateDTO.getProductId()).thenReturn("mockProductId");
        when(mockSaleCreateDTO.getUserId()).thenReturn("mockUserId");

        Sale returnedSale = createSaleUseCaseAdapter.createSale(mockSaleCreateDTO);
        assertEquals(mockSale, returnedSale);
        verify(userRepositoryPort, times(1)).findById("mockUserId");
        verify(productRepositoryPort, times(1)).findById("mockProductId");
        verify(productRepositoryPort, times(1)).save(eq(mockProduct));
        verify(saleRepositoryPort, times(1)).save(any(Sale.class));
    }

    @Test
    public void createSaleWithNonExistingUserTest(){
        when(userRepositoryPort.findById("mockUserId")).thenReturn(Optional.empty());

        SaleCreateDTO mockSaleCreateDTO = mock(SaleCreateDTO.class);
        when(mockSaleCreateDTO.getUserId()).thenReturn("mockUserId");

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            createSaleUseCaseAdapter.createSale(mockSaleCreateDTO);
                });

        assertEquals("User with Id: mockUserId not found", exception.getMessage());
        verify(userRepositoryPort, times(1)).findById("mockUserId");
        verify(productRepositoryPort, never()).findById("mockProductId");
        verify(productRepositoryPort, never()).save(any(Product.class));
        verify(saleRepositoryPort, never()).save(any(Sale.class));
    }

    @Test
    public void createSaleWithNonExistingProductTest(){
        UserDTO mockUserDTO = mock(UserDTO.class);
        when(userRepositoryPort.findById("mockUserId")).thenReturn(Optional.of(mockUserDTO));

        when(productRepositoryPort.findById("mockProductId")).thenReturn(Optional.empty());

        SaleCreateDTO mockSaleCreateDTO = mock(SaleCreateDTO.class);
        when(mockSaleCreateDTO.getProductId()).thenReturn("mockProductId");
        when(mockSaleCreateDTO.getUserId()).thenReturn("mockUserId");

        ElementNotFoundException exception = assertThrows(ElementNotFoundException.class, () -> {
            createSaleUseCaseAdapter.createSale(mockSaleCreateDTO);
        });

        assertEquals("Product with Id: mockProductId not found", exception.getMessage());
        verify(userRepositoryPort, times(1)).findById("mockUserId");
        verify(productRepositoryPort, times(1)).findById("mockProductId");
        verify(productRepositoryPort, never()).save(any(Product.class));
        verify(saleRepositoryPort, never()).save(any(Sale.class));
    }
}