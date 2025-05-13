package ar.edu.unq.product_sale.infrastructure.web;

import ar.edu.unq.product_sale.domain.model.Sale;
import ar.edu.unq.product_sale.domain.port.in.sale.CreateSaleUseCasePort;
import ar.edu.unq.product_sale.infrastructure.web.dto.sale.SaleCreateDTO;
import ar.edu.unq.product_sale.infrastructure.web.dto.sale.SaleResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sale")
public class SaleController {

    private final CreateSaleUseCasePort createSaleUseCasePort;

    public SaleController(CreateSaleUseCasePort createSaleUseCasePort) {
        this.createSaleUseCasePort = createSaleUseCasePort;
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> generateSale(@Valid @RequestBody SaleCreateDTO saleCreateDTO) {
        Sale createdSale = createSaleUseCasePort.createSale(saleCreateDTO);

        SaleResponseDTO saleResponseDTO = new SaleResponseDTO(
                createdSale.getId(),
                createdSale.getProductId(),
                createdSale.getUserId(),
                createdSale.getSaleValue()
        );

        return ResponseEntity.ok(saleResponseDTO);
    }
}
