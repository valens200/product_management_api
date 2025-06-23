package rw.productant.v1.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface IProductService {
    List<Product> getAllProducts();

   Product getProductById(UUID id);

    Product createProduct(ProductDto productDto);

    Product updateProductById(UUID id, ProductDto productDto);

    void deleteProductById(UUID id);
}
