package ar.edu.unq.product_sale.infrastructure.web.out;

import ar.edu.unq.product_sale.domain.port.out.SellerRepositoryPort;
import ar.edu.unq.product_sale.seller.SellerGrpcRequest;
import ar.edu.unq.product_sale.seller.SellerGrpcResponse;
import ar.edu.unq.product_sale.seller.SellerServiceGrpc;
import ar.edu.unq.product_sale.infrastructure.web.out.dto.SellerDTO;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SellerRepositoryAdapter implements SellerRepositoryPort {

    @GrpcClient("seller-service")
    private SellerServiceGrpc.SellerServiceBlockingStub sellerServiceBlockingStub;

    @Override
    public Optional<SellerDTO> findById(String sellerId) {
        SellerGrpcRequest request = SellerGrpcRequest.newBuilder().setId(sellerId).build();

        Optional<SellerDTO> sellerDtoOptional;
        try{
            SellerGrpcResponse response = sellerServiceBlockingStub.getSellerById(request);
            sellerDtoOptional = Optional.of(generateSellerDTOFrom(response));
        }catch(StatusRuntimeException e){
            sellerDtoOptional = Optional.empty();
        }

        return sellerDtoOptional;
    }

    private SellerDTO generateSellerDTOFrom(SellerGrpcResponse sellerGrpcResponse) {
        return new SellerDTO(
                sellerGrpcResponse.getId(),
                sellerGrpcResponse.getCompanyName(),
                sellerGrpcResponse.getCompanyEmail()
        );
    }
}
