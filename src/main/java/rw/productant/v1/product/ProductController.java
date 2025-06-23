package rw.productant.v1.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.productant.v1.common.payloads.ApiResponsePayload;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductServiceImplementation productService;
    @PostMapping
    public ProductEntity create(@RequestBody ProductEntity product) {
        return productService.createProduct(product);
    }
    @GetMapping
    public ResponseEntity<ApiResponsePayload> getAll() {
        List<ProductEntity> productsResponse = this.productService.getAllProducts();
        return ResponseEntity.ok().body(new ApiResponsePayload(true, "Products fetched successfully", productsResponse))
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponsePayload> getById(@PathVariable UUID id) {
        ProductEntity productResponse = this.productService.getProductById(id);
        return ResponseEntity.ok().body(
                new ApiResponsePayload(true,"Successfully fetched a product", productResponse)
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponsePayload> update(@PathVariable UUID id, @RequestBody ProductEntity product) {
        ProductEntity updatedProduct = this.productService.updateProduct(id,product);
        return ResponseEntity.ok().body(new ApiResponsePayload(true,"The product was updated successfully", updatedProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponsePayload> delete(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body(new ApiResponsePayload(true, "The product was successfully deleted"));
    }
}
