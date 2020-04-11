package sia.tacocloud.web;

import sia.tacocloud.Ingredient;
import java.util.ArrayList;

public class Taco {
    private java.util.List<Ingredient> ingredients = new ArrayList<>();

    private String name;

    public Taco() {
    }

    public Taco(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public java.util.List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(java.util.List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
