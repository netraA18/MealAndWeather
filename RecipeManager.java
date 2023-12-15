import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class RecipeManager {

    private List<String> savedRecipes;

    public RecipeManager() {
        this.savedRecipes = new ArrayList<>();
    }

    public void saveRecipe(String recipe) {
        savedRecipes.add(recipe);

    }

    public List<String> getSavedRecipe() {
        savedRecipes.forEach(System.out::println);
        return savedRecipes;

    }

}
