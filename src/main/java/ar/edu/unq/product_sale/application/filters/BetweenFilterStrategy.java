package ar.edu.unq.product_sale.application.filters;

import ar.edu.unq.product_sale.application.exceptions.InvalidFIlterParameterException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.ProductSearchFilterDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BetweenFilterStrategy extends FilterStrategy {

    private static final String FILTER_NAME = "BT";

    public BetweenFilterStrategy(ProductRepositoryPort productRepositoryPort) {
        super(productRepositoryPort, FILTER_NAME);
    }

    @Override
    public List<Product> filter(ProductSearchFilterDTO productSearchFilterDTO) {

        if(productSearchFilterDTO.getMinPrice() == null || productSearchFilterDTO.getMaxPrice() == null) {
            throw new InvalidFIlterParameterException("BT filter must have min_price and max_price parameters");
        }

        return productRepositoryPort.searchProductsWithBetweenParam(productSearchFilterDTO.getMinPrice(), productSearchFilterDTO.getMaxPrice());
    }
}
