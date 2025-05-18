package ar.edu.unq.product_sale.application.product;


import ar.edu.unq.product_sale.application.exceptions.FilterNotSupportedException;
import ar.edu.unq.product_sale.application.filters.FilterStrategy;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.ProductSearchFilterDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SearchProductUseCaseAdapterTest {

    private SearchProductUseCaseAdapter searchProductUseCaseAdapter;

    @Mock
    private ProductRepositoryPort productRepositoryPort;

    @Mock
    private FilterStrategy filterStrategy;

    @BeforeEach
    public void setUp() {
        searchProductUseCaseAdapter = new SearchProductUseCaseAdapter(productRepositoryPort, List.of(filterStrategy));
    }

    @Test
    public void searchProductByNameTest() {
        List<Product> returnedProducts = searchProductUseCaseAdapter.searchProductByName("mockProductName");

        assertEquals(0, returnedProducts.size());
        verify(productRepositoryPort, times(1)).searchProductByName("mockProductName");
    }

    @Test
    public void searchProductByCategoryTest() {
        List<Product> returnedProducts = searchProductUseCaseAdapter.searchProductByName("mockProductCategory");

        assertEquals(0, returnedProducts.size());
        verify(productRepositoryPort, times(1)).searchProductByName("mockProductCategory");
    }

    @Test
    public void searchProductByFilterTest() {
        when(filterStrategy.getFilterName()).thenReturn("mockFilterName");
        when(filterStrategy.filter(any(ProductSearchFilterDTO.class))).thenReturn(new ArrayList<>());

        ProductSearchFilterDTO mockProductSearchFilterDTO = mock(ProductSearchFilterDTO.class);
        when(mockProductSearchFilterDTO.getFilterName()).thenReturn("mockFilterName");

        List<Product> returnedProducts = searchProductUseCaseAdapter.searchProductByFilter(mockProductSearchFilterDTO);

        assertEquals(0, returnedProducts.size());
        verify(filterStrategy, times(1)).getFilterName();
        verify(filterStrategy, times(1)).filter(eq(mockProductSearchFilterDTO));
    }

    @Test
    public void searchProductByNonExistingFilterTest() {
        when(filterStrategy.getFilterName()).thenReturn("mockFilterName");

        ProductSearchFilterDTO mockProductSearchFilterDTO = mock(ProductSearchFilterDTO.class);
        when(mockProductSearchFilterDTO.getFilterName()).thenReturn("mockNonExistingFilterName");

        FilterNotSupportedException exception = assertThrows(FilterNotSupportedException.class, () -> {
            searchProductUseCaseAdapter.searchProductByFilter(mockProductSearchFilterDTO);
        });

        assertEquals("Filter with name mockNonExistingFilterName is not supported", exception.getMessage());
        verify(filterStrategy, times(1)).getFilterName();
        verify(filterStrategy, never()).filter(eq(mockProductSearchFilterDTO));
    }
}