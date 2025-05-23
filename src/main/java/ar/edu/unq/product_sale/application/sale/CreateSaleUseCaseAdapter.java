package ar.edu.unq.product_sale.application.sale;

import ar.edu.unq.product_sale.application.exceptions.ElementNotFoundException;
import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.model.Sale;
import ar.edu.unq.product_sale.domain.port.in.sale.CreateSaleUseCasePort;
import ar.edu.unq.product_sale.domain.port.out.NotificationRepositoryPort;
import ar.edu.unq.product_sale.domain.port.out.ProductRepositoryPort;
import ar.edu.unq.product_sale.domain.port.out.SaleRepositoryPort;
import ar.edu.unq.product_sale.domain.port.out.UserRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.sale.SaleCreateDTO;
import ar.edu.unq.product_sale.infrastructure.web.out.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateSaleUseCaseAdapter implements CreateSaleUseCasePort {

    private final ProductRepositoryPort productRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;
    private final SaleRepositoryPort saleRepositoryPort;
    private final NotificationRepositoryPort notificationRepositoryPort;

    public CreateSaleUseCaseAdapter(
            ProductRepositoryPort productRepositoryPort,
            UserRepositoryPort userRepositoryPort,
            SaleRepositoryPort saleRepositoryPort,
            NotificationRepositoryPort notificationRepositoryPort
    ) {
        this.productRepositoryPort = productRepositoryPort;
        this.userRepositoryPort = userRepositoryPort;
        this.saleRepositoryPort = saleRepositoryPort;
        this.notificationRepositoryPort = notificationRepositoryPort;
    }

    @Override
    public Sale createSale(SaleCreateDTO saleCreateDTO) {

        Optional<UserDTO> userWithId = userRepositoryPort.findById(saleCreateDTO.getUserId());
        if(userWithId.isEmpty()){
            throw new ElementNotFoundException("User", saleCreateDTO.getUserId());
        }

        Product productWithId = productRepositoryPort.findById(saleCreateDTO.getProductId())
                .orElseThrow(() -> new ElementNotFoundException("Product", saleCreateDTO.getProductId()));

        productWithId.removeFromStock(saleCreateDTO.getAmount());
        productRepositoryPort.save(productWithId);

        String idForNewSale = UUID.randomUUID().toString();
        Double saleValue = productWithId.getPrice() * saleCreateDTO.getAmount();

        Sale sale = new Sale(idForNewSale, saleCreateDTO.getProductId(), saleCreateDTO.getUserId(), saleValue);
        notificationRepositoryPort.notifySale(sale);
        return saleRepositoryPort.save(sale);
    }
}
