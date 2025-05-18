package ar.edu.unq.product_sale.infrastructure.persistence.sale;

import ar.edu.unq.product_sale.domain.model.Sale;
import ar.edu.unq.product_sale.domain.port.out.SaleRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class SaleRepositoryAdapter implements SaleRepositoryPort {

    private final SaleMongoRepository saleMongoRepository;

    public SaleRepositoryAdapter(SaleMongoRepository saleMongoRepository) {
        this.saleMongoRepository = saleMongoRepository;
    }

    @Override
    public Sale save(Sale sale) {
        SaleDocument saleDocument = new SaleDocument(
                sale.getId(),
                sale.getProductId(),
                sale.getUserId(),
                sale.getSaleValue()
        );

        saleMongoRepository.save(saleDocument);
        return sale;
    }
}
