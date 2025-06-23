package rw.productant.v1.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.productant.v1.common.exceptions.ConflictException;
import rw.productant.v1.common.exceptions.NotFoundException;
import rw.productant.v1.utils.ClassMapper;

import java.util.List;
import java.util.UUID;

@Service
class ProductServiceImplementation implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("The product with the provided ID is not found"));
    }

    public Product createProduct(ProductDto productDto) {
        boolean productExists = productRepository.existsByName(productDto.getName());
        if(productExists) throw new ConflictException("The product with the same already exists");
        Product productEntity = ClassMapper.getProductFromDTO(productDto);
        return productRepository.save(productEntity);
    }

    public Product updateProductById(UUID id, ProductDto productDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());

        return productRepository.save(product);
    }

    public void deleteProductById(UUID id) {
        Product productToDelete = this.getProductById(id);
        productRepository.deleteById(productToDelete.getId());
    }
}
