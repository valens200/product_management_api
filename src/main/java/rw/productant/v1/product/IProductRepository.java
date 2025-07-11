package rw.productant.v1.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
interface IProductRepository extends JpaRepository<Product, UUID> {
    Product findByName(String name);

    boolean existsByName(String name);
}
