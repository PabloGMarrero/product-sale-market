package ar.edu.unq.product_sale.application.product;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.application.exceptions.ProductAlreadyExistsException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.in.product.CreateProductUseCasePort;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.domain.port.out.SellerRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.out.dto.SellerDTO;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.ProductCreateDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateProductUseCaseAdapter implements CreateProductUseCasePort {

    private final ProductRepositoryPort productRepositoryPort;
    private final SellerRepositoryPort sellerRepositoryPort;

    public CreateProductUseCaseAdapter(ProductRepositoryPort productRepositoryPort, SellerRepositoryPort sellerRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
        this.sellerRepositoryPort = sellerRepositoryPort;
    }

    @Override
    public Product createProduct(ProductCreateDTO productCreateDTO) {

        Optional<SellerDTO> sellerWithId = sellerRepositoryPort.findById(productCreateDTO.getSellerId());
        if(sellerWithId.isEmpty()) {
            throw new ElementNotFoundException("Seller", productCreateDTO.getSellerId());
        }

        if(productRepositoryPort.existsByNameAndSellerId(productCreateDTO.getName(), productCreateDTO.getSellerId())){
            throw new ProductAlreadyExistsException(productCreateDTO.getSellerId(), productCreateDTO.getName());
        }

        String idForNewProduct = UUID.randomUUID().toString();
        Product product = new Product(
                idForNewProduct,
                productCreateDTO.getName(),
                productCreateDTO.getDescription(),
                productCreateDTO.getCategory(),
                productCreateDTO.getPrice(),
                productCreateDTO.getStock(),
                productCreateDTO.getSellerId()
        );

        return productRepositoryPort.save(product);
    }
}
