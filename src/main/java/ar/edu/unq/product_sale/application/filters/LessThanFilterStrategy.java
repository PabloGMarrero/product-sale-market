package ar.edu.unq.product_sale.application.filters;

import ar.edu.unq.product_sale.application.exceptions.InvalidFIlterParameterException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.dto.product.ProductSearchFilterDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessThanFilterStrategy extends FilterStrategy {

    private static final String FILTER_NAME = "LT";

    public LessThanFilterStrategy(ProductRepositoryPort productRepositoryPort) {
        super(productRepositoryPort, FILTER_NAME);
    }

    @Override
    public List<Product> filter(ProductSearchFilterDTO productSearchFilterDTO) {

        if(productSearchFilterDTO.getPrice() == null) {
            throw new InvalidFIlterParameterException("LT filter must have price parameter");
        }

        return productRepositoryPort.searchProductsWithLessThanParam(productSearchFilterDTO.getPrice());
    }
}
