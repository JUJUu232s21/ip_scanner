import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetUserIpModern {
    public static void main(String[] args) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://ipify.org")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Ваш текущий IP-адрес: " + response.body());
        } catch (Exception e) {
            System.err.println("Ошибка при получении IP: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
