package ar.edu.unq.product_sale.infrastructure.persistence.product;

import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryAdapter implements ProductRepositoryPort {

    private final ProductMongoRepository productMongoRepository;

    public ProductRepositoryAdapter(ProductMongoRepository productMongoRepository) {
        this.productMongoRepository = productMongoRepository;
    }

    @Override
    public Product save(Product product) {
        ProductDocument productDocument = new ProductDocument(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getSellerId(),
                product.getDeleted()
        );

        productMongoRepository.save(productDocument);
        return product;
    }

    @Override
    public Optional<Product> findById(String productId) {
        return productMongoRepository.findById(productId).map(this::generateProductFrom);
    }

    @Override
    public Optional<Product> findByIdAndDeletedFalse(String productId) {
        return productMongoRepository.findByIdAndDeletedFalse(productId).map(this::generateProductFrom);
    }

    @Override
    public boolean existsByNameAndSellerId(String productName, String sellerId) {
        return productMongoRepository.existsByNameAndSellerId(productName, sellerId);
    }

    @Override
    public List<Product> searchProductByName(String productName) {
        return productMongoRepository.findByNameContaining(productName).stream().map(this::generateProductFrom).toList();
    }

    @Override
    public List<Product> searchProductByCategory(String productCategory) {
        return productMongoRepository.findByCategory(productCategory).stream().map(this::generateProductFrom).toList();
    }

    @Override
    public List<Product> searchProductsWithBetweenParam(Double minPrice, Double maxPrice) {
        return productMongoRepository.findByPriceBetween(minPrice, maxPrice).stream().map(this::generateProductFrom).toList();
    }

    @Override
    public List<Product> searchProductsWithLessThanParam(Double price) {
        return productMongoRepository.findByPriceLessThan(price).stream().map(this::generateProductFrom).toList();
    }

    @Override
    public List<Product> searchProductsWithGreaterThanParam(Double price) {
        return productMongoRepository.findByPriceGreaterThan(price).stream().map(this::generateProductFrom).toList();
    }

    private Product generateProductFrom(ProductDocument productDocument){
        return new Product(
                productDocument.getId(),
                productDocument.getName(),
                productDocument.getDescription(),
                productDocument.getCategory(),
                productDocument.getPrice(),
                productDocument.getStock(),
                productDocument.getSellerId(),
                productDocument.getDeleted()
        );
    }
}
