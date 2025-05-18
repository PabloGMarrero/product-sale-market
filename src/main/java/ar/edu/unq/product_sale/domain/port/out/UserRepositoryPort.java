package ar.edu.unq.product_sale.domain.port.out;


import ar.edu.unq.product_sale.domain.port.out.dto.UserDTO;

import java.util.Optional;

public interface UserRepositoryPort {

    Optional<UserDTO> findById(String userId);

    Optional<UserDTO> findByIdAndDeletedFalse(String sellerId);

    boolean existsByEmail(String email);
}
