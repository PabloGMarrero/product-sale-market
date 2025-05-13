package ar.edu.unq.product_sale.application.product;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.in.product.EditProductUseCasePort;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.dto.product.ProductEditDTO;
import org.springframework.stereotype.Service;

@Service
public class EditProductUseCaseAdapter implements EditProductUseCasePort {

    private final ProductRepositoryPort productRepositoryPort;

    public EditProductUseCaseAdapter(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Product editProduct(String productId, ProductEditDTO productEditDTO) {
        Product productWithId = productRepositoryPort.findByIdAndDeletedFalse(productId).orElseThrow(() -> new ElementNotFoundException("Product", productId));

        productWithId.setDescription(productEditDTO.getDescription());
        productWithId.setCategory(productEditDTO.getCategory());
        productWithId.setPrice(productEditDTO.getPrice());
        productWithId.setStock(productEditDTO.getStock());
        productRepositoryPort.save(productWithId);

        return productWithId;
    }
}
