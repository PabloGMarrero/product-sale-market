package ar.edu.unq.product_sale.infrastructure.web.in;

import ar.edu.unq.product_sale.domain.model.Product;
import ar.edu.unq.product_sale.domain.port.in.product.CreateProductUseCasePort;
import ar.edu.unq.product_sale.domain.port.in.product.DeleteProductUseCasePort;
import ar.edu.unq.product_sale.domain.port.in.product.EditProductUseCasePort;
import ar.edu.unq.product_sale.domain.port.in.product.SearchProductUserCasePort;
import ar.edu.unq.product_sale.infrastructure.web.in.dto.product.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final CreateProductUseCasePort createProductUseCasePort;
    private final DeleteProductUseCasePort deleteProductUserCasePort;
    private final EditProductUseCasePort editProductUserCasePort;
    private final SearchProductUserCasePort searchProductUserCasePort;

    public ProductController(
            CreateProductUseCasePort createProductUseCasePort,
            DeleteProductUseCasePort deleteProductUserCasePort,
            EditProductUseCasePort editProductUserCasePort,
            SearchProductUserCasePort searchProductUserCasePort
    ) {
        this.createProductUseCasePort = createProductUseCasePort;
        this.deleteProductUserCasePort = deleteProductUserCasePort;
        this.editProductUserCasePort = editProductUserCasePort;
        this.searchProductUserCasePort = searchProductUserCasePort;
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> createProduct(@Valid @RequestBody ProductCreateDTO productCreateDTO) {
        Product createdProduct = createProductUseCasePort.createProduct(productCreateDTO);

        return ResponseEntity.ok(generateProductResponseDTOFrom(createdProduct));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {
        deleteProductUserCasePort.deleteProduct(productId);
        
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable String productId, @Valid @RequestBody ProductEditDTO productEditDTO) {
        Product editedProduct = editProductUserCasePort.editProduct(productId, productEditDTO);

        return ResponseEntity.ok(generateProductResponseDTOFrom(editedProduct));
    }

    @GetMapping("/byName/{productName}")
    public ResponseEntity<ProductSearchResponseDTO> findProductByName(@PathVariable String productName) {
        List<Product> productsWithName = searchProductUserCasePort.searchProductByName(productName);

        List<ProductResponseDTO> productResponseDTOS = productsWithName.stream().map(this::generateProductResponseDTOFrom).toList();

        return ResponseEntity.ok(new ProductSearchResponseDTO(productResponseDTOS));
    }

    @GetMapping("/byCategory/{productCategory}")
    public ResponseEntity<ProductSearchResponseDTO> findProductByCategory(@PathVariable String productCategory) {
        List<Product> productsWithCategory = searchProductUserCasePort.searchProductByCategory(productCategory);

        List<ProductResponseDTO> productResponseDTOS = productsWithCategory.stream().map(this::generateProductResponseDTOFrom).toList();

        return ResponseEntity.ok(new ProductSearchResponseDTO(productResponseDTOS));
    }

    @GetMapping("/byFilter")
    public ResponseEntity<ProductSearchResponseDTO> findProductByFilter(@Valid @RequestBody ProductSearchFilterDTO productSearchFilterDTO) {
        List<Product> productsMatchingFilter = searchProductUserCasePort.searchProductByFilter(productSearchFilterDTO);

        List<ProductResponseDTO> productResponseDTOS = productsMatchingFilter.stream().map(this::generateProductResponseDTOFrom).toList();

        return ResponseEntity.ok(new ProductSearchResponseDTO(productResponseDTOS));
    }

    private ProductResponseDTO generateProductResponseDTOFrom(Product product) {
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStock(),
                product.getSellerId()
        );
    }
}
