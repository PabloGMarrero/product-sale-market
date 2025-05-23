package ar.edu.unq.product_sale.domain.port.out;

import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.sale.SaleCreateDTO;

public interface NotificationRepositoryPort {

    void notifySale(SaleCreateDTO saleCreateDTO, Product productWithId, Double saleValue);
}
