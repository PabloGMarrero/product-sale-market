package ar.edu.unq.product_sale.application.filters;

import ar.edu.unq.product_sale.application.exceptions.InvalidFIlterParameterException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.ProductSearchFilterDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GreaterThanFilterStrategy extends FilterStrategy {

    private static final String FILTER_NAME = "GT";

    public GreaterThanFilterStrategy(ProductRepositoryPort productRepositoryPort) {
        super(productRepositoryPort, FILTER_NAME);
    }

    @Override
    public List<Product> filter(ProductSearchFilterDTO productSearchFilterDTO) {

        if(productSearchFilterDTO.getPrice() == null) {
            throw new InvalidFIlterParameterException("GT filter must have price parameter");
        }

        return productRepositoryPort.searchProductsWithGreaterThanParam(productSearchFilterDTO.getPrice());
    }
}
