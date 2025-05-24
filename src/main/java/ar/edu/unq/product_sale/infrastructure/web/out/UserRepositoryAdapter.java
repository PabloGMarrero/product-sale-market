package ar.edu.unq.product_sale.infrastructure.web.out;

import ar.edu.unq.product_sale.domain.port.out.UserRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.out.dto.UserDTO;
import ar.edu.unq.seller_user.UserGrpcRequest;
import ar.edu.unq.seller_user.UserGrpcResponse;
import ar.edu.unq.seller_user.UserServiceGrpc;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryAdapter implements UserRepositoryPort {

    @GrpcClient("user-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceBlockingStub;

    @Override
    public Optional<UserDTO> findById(String userId) {
        var request = UserGrpcRequest.newBuilder().setId(userId).build();

        Optional<UserDTO> userDTOOptional;
        try{
            var response = userServiceBlockingStub.getUserById(request);
            userDTOOptional = Optional.of(generateUserDTOFrom(response));
        }catch(StatusRuntimeException e){
            userDTOOptional = Optional.empty();
        }

        return userDTOOptional;
    }

    private UserDTO generateUserDTOFrom(UserGrpcResponse userGrpcResponse) {
        return new UserDTO(
                userGrpcResponse.getId(),
                userGrpcResponse.getName(),
                userGrpcResponse.getDeleted()
        );
    }


}
