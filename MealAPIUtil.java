import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class MealAPIUtil {
    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";

    public static String getMealByName(String dish) throws IOException {
        // Create a URL for the API request
        URL url = new URL(API_URL + dish);

        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Read the response
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (java.net.UnknownHostException e) {
            System.out.println(e);
            return "error";
        } finally {
            connection.disconnect();
        }
    }
}
