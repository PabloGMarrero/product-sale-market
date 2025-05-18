package ar.edu.unq.product_sale.infrastructure.web.out;

import ar.edu.unq.product_sale.domain.port.out.SellerRepositoryPort;
import ar.edu.unq.product_sale.domain.port.out.dto.SellerDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SellerRepositoryAdapter implements SellerRepositoryPort {

    @Override
    public Optional<SellerDTO> findById(String sellerId) {
        return Optional.empty();
    }

    @Override
    public Optional<SellerDTO> findByIdAndDeletedFalse(String sellerId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByCompanyEmail(String companyEmail) {
        return false;
    }
}
