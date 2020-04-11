package sia.tacocloud.web.controller.tacos;

import lombok.Data;
import sia.tacocloud.Ingredient;
import java.util.List;

@Data
public class Taco {
    private String name;
    private List<String> ingredients;
}
