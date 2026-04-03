package kirill.hits.recipes.controller;

import kirill.hits.recipes.model.Dish;
import kirill.hits.recipes.request.DishCreateRequest;
import kirill.hits.recipes.service.DishService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishService service;

    public DishController(DishService service) {
        this.service = service;
    }

    @PostMapping
    public Dish create(@RequestBody DishCreateRequest request) {
        return service.create(request);
    }
}