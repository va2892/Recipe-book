package kirill.hits.recipes.repository;

import kirill.hits.recipes.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}