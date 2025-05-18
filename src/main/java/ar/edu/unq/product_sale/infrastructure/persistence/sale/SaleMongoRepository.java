package ar.edu.unq.product_sale.infrastructure.persistence.sale;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleMongoRepository extends MongoRepository<SaleDocument, String> {
}
