package ar.edu.unq.product_sale.application.product;

import ar.edu.unq.product_sale.application.exceptions.FilterNotSupportedException;
import ar.edu.unq.product_sale.application.filters.FilterStrategy;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.in.product.SearchProductUserCasePort;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.ProductSearchFilterDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SearchProductUseCaseAdapter implements SearchProductUserCasePort {

    private final ProductRepositoryPort productRepositoryPort;
    private final List<FilterStrategy> filterStrategies;

    public SearchProductUseCaseAdapter(ProductRepositoryPort productRepositoryPort, List<FilterStrategy> filterStrategies) {
        this.productRepositoryPort = productRepositoryPort;
        this.filterStrategies = filterStrategies;
    }

    @Override
    public List<Product> searchProductByName(String productName) {
        return productRepositoryPort.searchProductByName(productName);
    }

    @Override
    public List<Product> searchProductByCategory(String productCategory) {
        return productRepositoryPort.searchProductByCategory(productCategory);
    }

    @Override
    public List<Product> searchProductByFilter(ProductSearchFilterDTO productSearchFilterDTO) {
        Optional<FilterStrategy> filterStrategyOptional = getFIlterStrategyWithName(productSearchFilterDTO.getFilterName());

        if (filterStrategyOptional.isPresent()) {
            FilterStrategy filterStrategy = filterStrategyOptional.get();
            return filterStrategy.filter(productSearchFilterDTO);
        }else{
            throw new FilterNotSupportedException(productSearchFilterDTO.getFilterName());
        }
    }

    private Optional<FilterStrategy> getFIlterStrategyWithName(String filterStrategyName) {
        return filterStrategies.stream().filter(filterStrategy -> filterStrategyName.equals(filterStrategy.getFilterName())).findFirst();
    }
}
