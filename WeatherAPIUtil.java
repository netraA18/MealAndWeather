import java.io.BufferedReader;
// import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;


public class WeatherAPIUtil {
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String WEATHER_API_KEY = "feac66e300f95f0f9b2eab48b68c36c1";

    
    public String getWeatherInfo(String cityName, String countryName) {
        
        try {

            String encodedCityName = URLEncoder.encode(cityName, StandardCharsets.UTF_8.toString());
            String encodedCountryName = URLEncoder.encode(countryName, StandardCharsets.UTF_8.toString());
            String apiUrl = String.format("%s?q=%s,%s&appid=%s", WEATHER_API_URL, encodedCityName, encodedCountryName, WEATHER_API_KEY);

            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                return reader.lines().collect(Collectors.joining("\n"));
            } catch (java.net.UnknownHostException e) {
                System.out.println(e);
                return "error";
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
