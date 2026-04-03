package kirill.hits.recipes.repository;

import kirill.hits.recipes.model.DishProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishProductRepository extends JpaRepository<DishProduct, Long> {
    boolean existsByProductId(Long productId);
}