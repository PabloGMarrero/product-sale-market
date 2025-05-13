package ar.edu.unq.product_sale.domain.port.in.sale;

import ar.edu.unq.product_sale.domain.model.Sale;
import ar.edu.unq.product_sale.infrastructure.web.dto.sale.SaleCreateDTO;

public interface CreateSaleUseCasePort {
    Sale createSale(SaleCreateDTO saleCreateDTO);
}
