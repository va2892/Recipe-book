package kirill.hits.recipes.model;

import jakarta.persistence.*;

@Entity
public class DishProduct {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Dish dish;

    @ManyToOne
    private Product product;

    private double amount;

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public double getAmount() {
        return amount;
    }
}