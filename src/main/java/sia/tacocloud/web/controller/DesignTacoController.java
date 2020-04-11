package sia.tacocloud.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Ingredient.Type;
import sia.tacocloud.web.controller.tacos.Taco;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Flour Tortilla", Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE)
        );
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }
    @PostMapping
    public String processDesign(Design design) {

    }


        private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        if (type.equals(Type.WRAP))
            return ingredients.stream().filter(b -> b.getType().equals(Type.WRAP)).collect(Collectors.toList());
        if (type.equals(Type.CHEESE))
            return ingredients.stream().filter(b -> b.getType().equals(Type.CHEESE)).collect(Collectors.toList());
        if (type.equals(Type.PROTEIN))
            return ingredients.stream().filter(b -> b.getType().equals(Type.PROTEIN)).collect(Collectors.toList());
        if (type.equals(Type.VEGGIES))
            return ingredients.stream().filter(b -> b.getType().equals(Type.VEGGIES)).collect(Collectors.toList());
        if (type.equals(Type.SAUCE))
            return ingredients.stream().filter(b -> b.getType().equals(Type.SAUCE)).collect(Collectors.toList());
        return null;
    }
}