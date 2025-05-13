package ar.edu.unq.product_sale.application.filters;

import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.dto.product.ProductSearchFilterDTO;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class FilterStrategy {

    protected final ProductRepositoryPort productRepositoryPort;

    private final String filterName;

    protected FilterStrategy(ProductRepositoryPort productRepositoryPort, String filterName) {
        this.productRepositoryPort = productRepositoryPort;
        this.filterName = filterName;
    }

    public abstract List<Product> filter(ProductSearchFilterDTO productSearchFilterDTO);
}
