package ar.edu.unq.product_sale.domain.port.out;

import ar.edu.unq.product_sale.domain.model.Sale;

public interface SaleRepositoryPort {
    Sale save(Sale sale);
}
