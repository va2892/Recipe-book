package kirill.hits.recipes.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double calories;
    private double protein;
    private double fat;
    private double carbs;

    private double portionSize;
    private String category;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<DishProduct> products;

    public void setName(String name) {
        this.name = name;
    }

    public void setPortionSize(double portionSize) {
        this.portionSize = portionSize;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }
}