package ar.edu.unq.product_sale.infrastructure.web.out;

import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.out.NotificationRepositoryPort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.sale.SaleCreateDTO;
import ar.edu.unq.product_sale.notification.NotificationServiceGrpc;
import ar.edu.unq.product_sale.notification.SaleNotificationGrpcRequest;
import ar.edu.unq.product_sale.notification.SaleNotificationGrpcResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NotificationRepositoryAdapter implements NotificationRepositoryPort {

    @GrpcClient("notification-service")
    private NotificationServiceGrpc.NotificationServiceStub notificationServiceStub;

    @Override
    public void notifySale(SaleCreateDTO saleCreateDTO, Product productWithId, Double saleValue) {

        SaleNotificationGrpcRequest request = SaleNotificationGrpcRequest.newBuilder()
                .setSellerId(productWithId.getSellerId())
                .setBuyerId(saleCreateDTO.getUserId())
                .setProductId(saleCreateDTO.getProductId())
                .setProductAmount(saleCreateDTO.getAmount())
                .setSaleValue(saleValue)
                .build();

        notificationServiceStub.notifySale(request, new StreamObserver<>() {
            @Override
            public void onNext(SaleNotificationGrpcResponse saleNotificationGrpcResponse) {
                log.info("Sale notification sent with returned status: {}", saleNotificationGrpcResponse.getNotificationStatus());
            }

            @Override
            public void onError(Throwable throwable) {
                log.error("Error sending sale notification: {}", throwable.getMessage(), throwable);
            }

            @Override
            public void onCompleted() {
                log.info("Sale notification sent successfully");
            }
        });
    }
}
