package ar.edu.unq.product_sale.infrastructure.web.out;

import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.NotificationRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.sale.SaleCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {

    @Override
    public void notifySale(SaleCreateDTO saleCreateDTO, Product productWithId, Double saleValue) {

    }
}
