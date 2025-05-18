package ar.edu.unq.product_sale.domain.port.in.product;

import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.ProductCreateDTO;

public interface CreateProductUseCasePort {
    Product createProduct(ProductCreateDTO productCreateDTO);
}
