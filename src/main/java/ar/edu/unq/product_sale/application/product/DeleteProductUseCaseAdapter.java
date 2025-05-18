package ar.edu.unq.product_sale.application.product;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.in.product.DeleteProductUseCasePort;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class DeleteProductUseCaseAdapter implements DeleteProductUseCasePort {

    private final ProductRepositoryPort productRepositoryPort;

    public DeleteProductUseCaseAdapter(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public void deleteProduct(String productId) {
        Product productWithId = productRepositoryPort.findById(productId).orElseThrow(() -> new ElementNotFoundException("Product", productId));

        if(!productWithId.getDeleted()){
            productWithId.markAsDeleted();
            productRepositoryPort.save(productWithId);
        }
    }
}
