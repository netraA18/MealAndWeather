import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class MealMain {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        boolean userStatus = true;

        while (userStatus) {

            RecipeManager recipeManager = new RecipeManager();
            try {
                System.out.print("Enter a dish: ");

                String dish = scanner.nextLine();
                String mealResponse = MealAPIUtil.getMealByName(dish);

                if (mealResponse.startsWith("error")) {
                    System.out.println(mealResponse);
                } else {
                    JSONObject meal = new JSONObject();
                    processMealResponse(mealResponse, dish, recipeManager, meal);
                }

                System.out.print("Do you want to search for a new recipe? ");
                String newRecipeDecision = scanner.next();
                scanner.nextLine();
                if (newRecipeDecision.equalsIgnoreCase("no")) {
                    userStatus = false;

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void processMealResponse(String mealResponse, String dish, RecipeManager recipeManager,
            JSONObject meal) {
        JSONParser parser = new JSONParser();
        JSONObject mealObject;
        JSONArray mealsArray;
        try {
            mealObject = (JSONObject) parser.parse(mealResponse);
            mealsArray = (JSONArray) mealObject.get("meals");
        } catch (ParseException e) {
            System.out.println("Error parsing JSON response.");
            return;
        }

        if (mealsArray != null && !mealsArray.isEmpty()) {
            for (int i = 0; i < mealsArray.size(); i++) {
                JSONObject currentMeal = (JSONObject) mealsArray.get(i);
                printMealDetails(currentMeal);

                System.out.print("Do you want to save this recipe? ");
                String saveDecision = scanner.next();

                if (saveDecision.equalsIgnoreCase("yes")) {
                    String mealName = (String) currentMeal.get("strMeal");
                    recipeManager.saveRecipe(mealName);
                }
            }
            details(recipeManager);

        } else {
            System.out.println("No meal data found for " + dish);
        }
    }

    public static void printMealDetails(JSONObject meal) {
        String mealName = (String) meal.get("strMeal");
        String mealCategory = (String) meal.get("strCategory");
        String mealInstructions = (String) meal.get("strInstructions");
        String youTube = (String) meal.get("strYoutube");

        String[] sentences = mealInstructions.split("\\.");

        System.out.println("\nMeal Name:\t\t " + mealName + "\n");
        System.out.println("Category:\t\t " + mealCategory + "\n");
        System.out.println("Youtube Video:\t\t " + youTube + "\n");
        System.out.println("Instructions:" + "\n");

        for (int j = 1; j < sentences.length; j++) {
            System.out.println(j + ") " + sentences[j].trim());
        }

        System.out.println("\nMeasurements and Ingredients:\n ");
        for (int m = 1; m <= 20; m++) {
            String mealMeasurements = (String) meal.get("strMeasure" + m);
            String mealIngredients = (String) meal.get("strIngredient" + m);
            if (mealMeasurements != null && !mealMeasurements.trim().isEmpty() && mealIngredients != null
                    && !mealIngredients.trim().isEmpty()) {
                System.out.println(" ~ " + mealMeasurements + " - " + mealIngredients);
            }
        }
        System.out.println();

    }

    public static void details(RecipeManager recipeManager) {
        System.out.print("Do you want to see your saved recipes? ");
        String seeSaved = scanner.next();
        if (seeSaved.equalsIgnoreCase("yes")) {
            System.out.println();
            recipeManager.getSavedRecipe();
            System.out.println();
        }

    }

}


