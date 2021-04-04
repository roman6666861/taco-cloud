package sia.tacocloud.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import sia.tacocloud.Ingredient;
import sia.tacocloud.Ingredient.Type;
import sia.tacocloud.web.controller.tacos.Order;
import sia.tacocloud.web.controller.tacos.Taco;
import sia.tacocloud.web.controller.tacos.data.IngredientRepository;
import sia.tacocloud.web.controller.tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

    private final static Random random = new Random();

    private final IngredientRepository ingredientRepo;

    private TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepository) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = tacoRepository;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        System.out.println("Первый коммит");
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredients::add);
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }
    @PostMapping
    public String processDesign(@Valid Taco taco, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()){
            return "design";
        }
        Taco saved = designRepo.save(taco);
        order.addDesign(saved);
        log.info("Processing design: " + taco);
        return "redirect:/orders/current";
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
