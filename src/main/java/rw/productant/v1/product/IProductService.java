package rw.productant.v1.product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<ProductEntity> getAllProducts();

    Optional<ProductEntity> getProductById(Long id);

    ProductEntity createProduct(ProductEntity product);

    ProductEntity updateProduct(Long id, ProductEntity productDetails);

    void deleteProduct(Long id);
}
