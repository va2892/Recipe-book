package kirill.hits.recipes.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double calories;
    private double protein;
    private double fat;
    private double carbs;

    @Column(columnDefinition = "TEXT")
    private String composition;

    private String category;
    private String cookingType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getCalories() {
        return calories;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getCookingType() {
        return cookingType;
    }
}