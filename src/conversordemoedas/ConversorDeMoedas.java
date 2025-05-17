package conversordemoedas;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class ConversorDeMoedas {
    public static void main(String[] args) throws IOException, InterruptedException {

        String endereco = "https://v6.exchangerate-api.com/v6/8b7b036d1c08e8236223dd0f/latest/USD";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String json = response.body();
        Gson gson = new Gson();
        ExchangeRateResponse exchangeResponse = gson.fromJson(json, ExchangeRateResponse.class);

        Scanner leitura = new Scanner(System.in);
        int opcoes = 0;
        String textoInicial = "Digite o valor que deseja converter:";

        while (opcoes <= 7) {
            System.out.println("***********************************************************");
            System.out.println("Seja Bem-vindo/a ao conversor de moeda =]");
            System.out.println("1)Dólar =>> Peso argentino");
            System.out.println("2) Peso argentinho =>> Dólar");
            System.out.println("3) Dólar =>> Real brasileiro");
            System.out.println("4) Real brasileiro =>> Dólar");
            System.out.println("5) Dólar =>> Peso colombiano");
            System.out.println("6)Peso colombiano =>> Dólar");
            System.out.println("7) Sair");

            System.out.println("Escolha uma opção válida");
            int digito = leitura.nextInt();

            System.out.println("***************************************************************");

            if (digito == 1) {
                System.out.println(textoInicial);
                double valor1 = leitura.nextDouble();
                double taxaARS = exchangeResponse.conversion_rates.get("ARS");
                double valorConvertido = valor1 * taxaARS;
                System.out.println("Valor de " + valor1 + " [USD]  corresponde ao valor final de  =>>> " + valorConvertido + " [ARG]");

            }if (digito == 2) {
                System.out.println("Digite o valor que deseja converter");
                double valorARS = leitura.nextDouble();
                double taxaUSD_ARS = exchangeResponse.conversion_rates.get("ARS");
                double valorConvertidoUSD = valorARS / taxaUSD_ARS;
                System.out.printf("Valor de %.2f [ARS] corresponde ao valor final de =>>> %.2f [USD]%n", valorARS, valorConvertidoUSD);

            }if (digito == 3) {

                System.out.println("Digite o valor que deseja converter:");
                double valorUSD = leitura.nextDouble();
                double taxaBRL = exchangeResponse.conversion_rates.get("BRL");
                double valorConvertido3 = valorUSD * taxaBRL;
                System.out.println("Valor de " + valorUSD + " [USD]  corresponde ao valor final de  =>>> " + valorConvertido3 + " [BRL]");

            }if (digito == 4) {
                System.out.println("Digite o valor que deseja converter:");
                double valorBRL2 = leitura.nextDouble();
                double taxaBRL_USD = exchangeResponse.conversion_rates.get("BRL");
                double valorConvertido4 = valorBRL2 / taxaBRL_USD;
                System.out.println("Valor de " + valorBRL2 + " [BRL]  corresponde ao valor final de  =>>> " + valorConvertido4 + " [USD]");

            }if (digito == 5) {
                System.out.println(textoInicial);
                double valorUSD5 = leitura.nextDouble();
                double valorColombia = exchangeResponse.conversion_rates.get("COP");
                double valorConvertido5 = valorUSD5 * valorColombia;
                System.out.println("Valor de " + valorUSD5 + " [USD]  corresponde ao valor final de  =>>> " + valorConvertido5 + " [COP]");

            }if (digito == 6) {
                System.out.println(textoInicial);
                double valorCOP = leitura.nextDouble();
                double valorUsd6 = exchangeResponse.conversion_rates.get("COP");
                double valorConvertido6 = valorCOP / valorUsd6;
                System.out.println("Valor de " + valorCOP + " [COP]  corresponde ao valor final de  =>>> " + valorConvertido6 + " [USD]");
            } else if (digito == 7) {
                System.out.println("Programa finalizado com sucesso!");
                break;
            }

            if (digito == 0 || digito > 7) {
                System.out.println("Opção inválido!");
            }
        }
    }


    public static class ExchangeRateResponse {
        Map<String, Double> conversion_rates;
    }
}
