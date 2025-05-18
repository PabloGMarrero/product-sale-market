package ar.edu.unq.product_sale.domain.port.out;

import ar.edu.unq.product_sale.domain.port.out.dto.SellerDTO;

import java.util.Optional;

public interface SellerRepositoryPort {

    Optional<SellerDTO> findById(String sellerId);

    Optional<SellerDTO> findByIdAndDeletedFalse(String sellerId);

    boolean existsByCompanyEmail(String companyEmail);
}
