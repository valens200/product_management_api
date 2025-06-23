package rw.productant.v1.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.productant.v1.common.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImplementation {
    @Autowired
    private IProductRepository productRepository;

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("The product with the provided ID is not found"));
    }

    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    public ProductEntity updateProduct(UUID id, ProductEntity productDetails) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }
}
