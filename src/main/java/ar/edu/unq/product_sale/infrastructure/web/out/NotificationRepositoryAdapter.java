package ar.edu.unq.product_sale.infrastructure.web.out;

import ar.edu.unq.product_sale.domain.model.Sale;
import ar.edu.unq.product_sale.domain.port.out.NotificationRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {
    @Override
    public void notifySale(Sale sale) {

    }
}
