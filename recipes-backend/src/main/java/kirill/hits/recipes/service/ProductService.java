package kirill.hits.recipes.service;

import kirill.hits.recipes.model.Product;
import kirill.hits.recipes.repository.DishProductRepository;
import kirill.hits.recipes.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final DishProductRepository dishProductRepository;

    public ProductService(ProductRepository repository,
                          DishProductRepository dishProductRepository) {
        this.repository = repository;
        this.dishProductRepository = dishProductRepository;
    }

    public Product create(Product product) {
        validate(product);
        return repository.save(product);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public void delete(Long id) {
        if (dishProductRepository.existsByProductId(id)) {
            throw new RuntimeException("Продукт используется в блюдах");
        }
        repository.deleteById(id);
    }

    private void validate(Product p) {
        if (p.getProtein() + p.getFat() + p.getCarbs() > 100) {
            throw new RuntimeException("Сумма БЖУ > 100");
        }
    }
}