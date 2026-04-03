package kirill.hits.recipes.service;

import kirill.hits.recipes.model.Dish;
import kirill.hits.recipes.model.DishProduct;
import kirill.hits.recipes.model.Product;
import kirill.hits.recipes.repository.DishProductRepository;
import kirill.hits.recipes.repository.DishRepository;
import kirill.hits.recipes.repository.ProductRepository;
import kirill.hits.recipes.request.DishCreateRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final ProductRepository productRepository;
    private final DishProductRepository dishProductRepository;

    public DishService(DishRepository dishRepository,
                       ProductRepository productRepository,
                       DishProductRepository dishProductRepository) {
        this.dishRepository = dishRepository;
        this.productRepository = productRepository;
        this.dishProductRepository = dishProductRepository;
    }

    public Dish create(DishCreateRequest request) {

        Dish dish = new Dish();
        dish.setName(request.name);
        dish.setPortionSize(request.portionSize);
        dish.setCategory(request.category);

        dish = dishRepository.save(dish);

        List<DishProduct> items = new ArrayList<>();

        for (DishCreateRequest.Item i : request.items) {
            Product product = productRepository.findById(i.productId)
                    .orElseThrow();

            DishProduct dp = new DishProduct();
            dp.setDish(dish);
            dp.setProduct(product);
            dp.setAmount(i.amount);

            items.add(dp);
        }

        dishProductRepository.saveAll(items);

        calculateAndSetNutrition(dish, items);

        return dishRepository.save(dish);
    }

    private void calculateAndSetNutrition(Dish dish, List<DishProduct> items) {
        double calories = 0, protein = 0, fat = 0, carbs = 0;

        for (DishProduct dp : items) {
            Product p = dp.getProduct();
            double amount = dp.getAmount();

            calories += p.getCalories() * amount / 100;
            protein  += p.getProtein()  * amount / 100;
            fat      += p.getFat()      * amount / 100;
            carbs    += p.getCarbs()    * amount / 100;
        }

        dish.setCalories(calories);
        dish.setProtein(protein);
        dish.setFat(fat);
        dish.setCarbs(carbs);
    }
}