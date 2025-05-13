package ar.edu.unq.product_sale.domain.port.out;

import ar.edu.unq.product_sale.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryPort {

    Product save(Product product);

    Optional<Product> findById(String productId);

    Optional<Product> findByIdAndDeletedFalse(String productId);

    boolean existsByNameAndSellerId(String productName, String sellerId);

    List<Product> searchProductByName(String productName);

    List<Product> searchProductByCategory(String productCategory);

    List<Product> searchProductsWithBetweenParam(Double minPrice, Double maxPrice);

    List<Product> searchProductsWithLessThanParam(Double price);

    List<Product> searchProductsWithGreaterThanParam(Double price);
}
