package ar.edu.unq.product_sale.infrastructure.web.out;

import ar.edu.unq.product_sale.domain.port.out.UserRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.out.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {
    @Override
    public Optional<UserDTO> findById(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<UserDTO> findByIdAndDeletedFalse(String sellerId) {
        return Optional.empty();
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
