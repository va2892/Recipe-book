package kirill.hits.recipes.request;

import java.util.List;

public class DishCreateRequest {
    public String name;
    public double portionSize;
    public String category;

    public List<Item> items;

    public static class Item {
        public Long productId;
        public double amount;
    }
}