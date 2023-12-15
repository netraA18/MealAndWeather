import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

import org.json.simple.JSONObject;


public class Main {

    static Scanner scanner = new Scanner(System.in);
    static MealMain mealMainInstance = new MealMain();
    static WeatherMain weatherMainInstance = new WeatherMain();
     
    public static void main(String[] args) throws ParseException, org.json.simple.parser.ParseException, IOException {
        System.out.println("Enter 1 or 2: \n1) Search a Recipe\n2) Generate Recipes based on Weather");
        String initialAnswer = scanner.next();
        if (initialAnswer.equals("1")) {
            mealMainInstance.main(new String[]{});
            
        }

        if (initialAnswer.equals("2")) {
           Main.weatherWithMeal();
        }

    }


    public static void weatherWithMeal() throws org.json.simple.parser.ParseException, IOException {
        WeatherMain.main(new String[]{});
        System.out.println("Based on the weather, here are a few meals you can make: ");
        Main.determineFood(weatherMainInstance);

        


    }

    public static void determineFood(WeatherMain weatherMainInstance) throws IOException {
        if (WeatherMain.convertedTemp <= 55) {
            String mealResponseSoup = MealAPIUtil.getMealByName("soup");
            String mealResponseHotChocolate = MealAPIUtil.getMealByName("Hot Chocolate");
            String mealResponseCasserole = MealAPIUtil.getMealByName("Casserole");
            String mealResponseDal = MealAPIUtil.getMealByName("Dal fry");

        
            MealMain.processMealResponse(mealResponseSoup);
            MealMain.processMealResponse(mealResponseHotChocolate); 
            MealMain.processMealResponse(mealResponseCasserole);
            MealMain.processMealResponse(mealResponseDal);

   
        } else if (WeatherMain.convertedTemp > 55 && WeatherMain.convertedTemp <= 70) {
            String mealResponseGrilled = MealAPIUtil.getMealByName("grilled");
            String mealResponseSalad = MealAPIUtil.getMealByName("salad");
            String mealResponseSushi = MealAPIUtil.getMealByName("sushi");
            String mealResponseChicken = MealAPIUtil.getMealByName("chicken");

        
            MealMain.processMealResponse(mealResponseGrilled);
            MealMain.processMealResponse(mealResponseSalad); 
            MealMain.processMealResponse(mealResponseSushi);
            MealMain.processMealResponse(mealResponseChicken);


        } else if (WeatherMain.convertedTemp > 70 && WeatherMain.convertedTemp <= 90) {
            String mealResponseTaco = MealAPIUtil.getMealByName("taco");
            String mealResponseSandwich = MealAPIUtil.getMealByName("sandwich");
            String mealResponseCheese= MealAPIUtil.getMealByName("cheese");
            String mealResponseFruit = MealAPIUtil.getMealByName("fruit");

        
            MealMain.processMealResponse(mealResponseTaco);
            MealMain.processMealResponse(mealResponseSandwich); 
            MealMain.processMealResponse(mealResponseCheese);
            MealMain.processMealResponse(mealResponseFruit);

        } else if (WeatherMain.convertedTemp > 90) {
            String mealResponseYogurt = MealAPIUtil.getMealByName("yogurt");
            String mealResponseSandwich = MealAPIUtil.getMealByName("sandwich");
            String mealResponseSledz= MealAPIUtil.getMealByName("sledz");
            String mealResponseStrawBerries = MealAPIUtil.getMealByName("strawberries");

        
            MealMain.processMealResponse(mealResponseYogurt);
            MealMain.processMealResponse(mealResponseSandwich); 
            MealMain.processMealResponse(mealResponseSledz);
            MealMain.processMealResponse(mealResponseStrawBerries);



        }
        
            
        
    }

    
}
