package ar.edu.unq.product_sale.infrastructure.persistence.product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductMongoRepository extends MongoRepository<ProductDocument, String> {

    Optional<ProductDocument> findByIdAndDeletedFalse(String productId);

    boolean existsByNameAndSellerId(String name, String sellerId);

    List<ProductDocument> findByNameContaining(String name);

    List<ProductDocument> findByCategory(String productCategory);

    List<ProductDocument> findByPriceBetween(Double lower, Double higher);

    List<ProductDocument> findByPriceLessThan(Double price);

    List<ProductDocument> findByPriceGreaterThan(Double price);
}
