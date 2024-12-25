import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        String apiUrl = "https://v6.exchangerate-api.com/v6/d895dd0f749ee1a71be9debf/latest/USD";
        try {
            // Obter dados da API
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            Dados response = new Gson().fromJson(reader, Dados.class);

            // Menu
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n=== Conversor de Moedas ===");
                System.out.println("1. USD para BRL");
                System.out.println("2. USD para EUR");
                System.out.println("3. USD para JPY");
                System.out.println("4. BRL para USD");
                System.out.println("5. EUR para USD");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int opcao = scanner.nextInt();

                if (opcao == 6) {
                    System.out.println("Saindo do programa...");
                    break;
                }

                System.out.print("Digite o valor a ser convertido: ");
                double valor = scanner.nextDouble();

                double resultado = 0;
                switch (opcao) {
                    case 1: // USD para BRL
                        resultado = valor * response.getConversion_rates().get("BRL");
                        System.out.printf("Resultado: %.2f BRL\n", resultado);
                        break;
                    case 2: // USD para EUR
                        resultado = valor * response.getConversion_rates().get("EUR");
                        System.out.printf("Resultado: %.2f EUR\n", resultado);
                        break;
                    case 3: // USD para JPY
                        resultado = valor * response.getConversion_rates().get("JPY");
                        System.out.printf("Resultado: %.2f JPY\n", resultado);
                        break;
                    case 4: // BRL para USD
                        resultado = valor / response.getConversion_rates().get("BRL");
                        System.out.printf("Resultado: %.2f USD\n", resultado);
                        break;
                    case 5: // EUR para USD
                        resultado = valor / response.getConversion_rates().get("EUR");
                        System.out.printf("Resultado: %.2f USD\n", resultado);
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }

            scanner.close();
        } catch (Exception e) {
            System.out.println("Erro ao realizar a conversão: " + e.getMessage());
        }
    }
}
