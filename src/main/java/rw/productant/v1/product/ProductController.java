package rw.productant.v1.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.productant.v1.common.payloads.ApiResponsePayload;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@Slf4j
class ProductController {

    @Autowired
    private ProductServiceImplementation productService;
    @PostMapping
    public ResponseEntity<ApiResponsePayload> createProduct(@RequestBody @Valid  ProductDto productDto) {
        Product createdProduct = this.productService.createProduct(productDto);
        return ResponseEntity.ok().body(new ApiResponsePayload(true,"The product was created successfully",createdProduct));
    }
    @GetMapping
    public ResponseEntity<ApiResponsePayload> getAllProducts() {
        List<Product> productsResponse = this.productService.getAllProducts();
        return ResponseEntity.ok().body(new ApiResponsePayload(true, "Products fetched successfully", productsResponse));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponsePayload> getProductById(@PathVariable UUID id) {
        Product productResponse = this.productService.getProductById(id);
        return ResponseEntity.ok().body(
                new ApiResponsePayload(true,"Successfully fetched a product", productResponse)
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponsePayload> updateProduct(@PathVariable UUID id, @RequestBody @Valid ProductDto productDto) {
        Product updatedProduct = this.productService.updateProductById(id,productDto);
        return ResponseEntity.ok().body(new ApiResponsePayload(true,"The product was updated successfully", updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponsePayload> deleteProduct(@PathVariable UUID id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().body(new ApiResponsePayload(true, "The product was successfully deleted"));
    }
}
