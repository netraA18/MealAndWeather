import java.util.Scanner;

public class WeatherMain {

    
    WeatherAPIUtil weatherAPI = new WeatherAPIUtil();
    String apiURL;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the city name: ");
      
        
        String cityName = scanner.next();
        System.out.print("Enter the country abbreviation: ");
        String countryName = scanner.next();
        WeatherAPIUtil weatherAPI = new WeatherAPIUtil();
        // WeatherMain mainInstance = new WeatherMain();
        // mainInstance.apiURL = mainInstance.weatherAPI.getWeatherInfo(mainInstance.userLatitude, mainInstance.userLongitude);
        // System.out.println(mainInstance.apiURL);
        String weatherInfo = weatherAPI.getWeatherInfo(cityName, countryName);
        System.out.println(weatherInfo);

        scanner.close();
    }
}