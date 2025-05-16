package conversordemoedas.servicos;

import com.google.gson.Gson;
import conversordemoedas.ConversorDeMoedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiServicos {

    String endereco = "https://v6.exchangerate-api.com/v6/8b7b036d1c08e8236223dd0f/latest/USD";
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endereco))
            .build();

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    String json = response.body();

    Gson gson = new Gson();
    ConversorDeMoedas.ExchangeRateResponse exchangeResponse = gson.fromJson(json, ConversorDeMoedas.ExchangeRateResponse.class);

    public ApiServicos() throws IOException, InterruptedException {
    }
}
