import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaApi {

    public Moeda buscaTaxasDeCambio(String codigoMoeda) {
        String apiKey = "47eb726c9cfbbdf849af2c86";
        URI endereco = URI.create("https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + codigoMoeda);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco)
                    .build();

            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            // Desserializa o JSON para um objeto Moeda usando Gson
            return new Gson().fromJson(response.body(), Moeda.class);

        } catch (Exception e) {
            throw new RuntimeException("Não foi possível obter a taxa de câmbio: " + e.getMessage());
        }
    }
}