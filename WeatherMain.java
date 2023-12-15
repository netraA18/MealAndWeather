import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class WeatherMain {
    static double convertedTemp;

    WeatherAPIUtil weatherAPI = new WeatherAPIUtil();
    String apiURL;

    public static void main(String[] args) throws ParseException, IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the city name: ");

        String cityName = scanner.next();
        System.out.print("Enter the country abbreviation: ");
        String countryName = scanner.next();
        WeatherAPIUtil weatherAPI = new WeatherAPIUtil();
        String weatherInfo = weatherAPI.getWeatherInfo(cityName, countryName);

        if (weatherInfo.startsWith("error")) {
            System.out.println(weatherInfo);
        } else {
            JSONObject city = new JSONObject();
            processWeatherDescription(weatherInfo, city, cityName, countryName);
        }

        
    

        scanner.close();
    }

    public static double convertTemp(double temperature) {
        double fahrenTemp = (temperature - 273.15) * 1.8 + 32;

        return fahrenTemp;
    }

    public static void processWeatherDescription(String weatherInfo, JSONObject city, String cityName, String countryName) {
        JSONParser jsonParser = new JSONParser();
        JSONObject allInfoObject;
        JSONArray weatherInfoArray;
        JSONObject mainInfoObject;

        try {
            allInfoObject = (JSONObject) jsonParser.parse(weatherInfo);
            mainInfoObject = (JSONObject) allInfoObject.get("main");
            weatherInfoArray = (JSONArray) allInfoObject.get("weather");
        } catch (ParseException e) {
            System.out.println("Error parsing JSON response.");
            return;
        }

        if (weatherInfoArray != null && !weatherInfoArray.isEmpty()) {
            for (int i = 0; i < weatherInfoArray.size(); i++) {
                JSONObject currentInfo = (JSONObject) weatherInfoArray.get(i);
                printWeatherDescription(currentInfo);
            }
        } else {
            System.out.println("No data found for " + cityName);
        }

        if (mainInfoObject != null) {
            printMainWeatherDescription(mainInfoObject);
        } else {
            System.out.println("No data found for " + cityName);
        }
    }

    public static void printWeatherDescription(JSONObject city) {
        String mainDescription = (String) city.get("main");
        String otherDescription = (String) city.get("description");
        System.out.println("\nMain Description: " + mainDescription + ", " + otherDescription);

    }

    public static void printMainWeatherDescription(JSONObject city) {
        double currentTemp = (double) city.get("temp");
        long humidity = (long) city.get("humidity");

        convertedTemp = convertTemp(currentTemp);
        System.out.printf("Temperature: %.2f°F\n", convertedTemp);
        System.out.println("Humidity: " + humidity + "%\n");
        
    }

}