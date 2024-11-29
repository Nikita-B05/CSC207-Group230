package stock_api;

import com.google.gson.JsonArray;
import okhttp3.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import use_case.login.ChooseAssetStockDataAccessInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PolygonApiClient implements ChooseAssetStockDataAccessInterface {

    private static final String[] stockCodes = {"AAPL", "NVDA", "MSFT", "AMZN", "GOOGL", "META", "TSLA"};
    private static final String[] companyNames = {"Apple", "Nvidia", "Microsoft", "Amazon", "Google", "Meta", "Tesla"};
    private static final String BASE_URL = "https://api.polygon.io";
    private String apiKey;
    private final OkHttpClient client;

    public PolygonApiClient() {
        this.client = new OkHttpClient();
        this.apiKey = loadApiKey("config.properties");
    }

    public String[] getStockCodes() {
        return stockCodes;
    }

    public String[] getCompanyNames() {
        return companyNames;
    }

    private String loadApiKey(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + fileName);
            }
            properties.load(input);
            return properties.getProperty("api.key");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API key from properties file", e);
        }
    }

    public void fetchStockData(String ticker) throws IOException {
        String url = BASE_URL + "/v3/reference/tickers/" + ticker + "?apiKey=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                System.out.println(jsonResponse);
            } else {
                System.out.println("Request failed: " + response.code() + " - " + response.message());
            }
        }
    }

    public double getBuyPrice(String ticker) throws IOException {
        return fetchPrices(ticker)[0];
    }

    public double getSellPrice(String ticker) throws IOException {
        return fetchPrices(ticker)[1];
    }

    private double[] fetchPrices(String ticker) throws IOException {
        String url = BASE_URL + "/v2/aggs/ticker/" + ticker + "/prev?adjusted=true&apiKey=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

                // Extracting the last trade price
                JsonArray results = jsonResponse.get("results").getAsJsonArray();
                double buyPrice = results.get(0).getAsJsonObject().get("o").getAsDouble();
                double sellPrice = results.get(0).getAsJsonObject().get("c").getAsDouble();
                return new double[]{buyPrice, sellPrice};

            } else {
                throw new RuntimeException("Could not retrieve stock price: "
                        + response.code() + " - " + response.message());
            }
        }
    }

    public static void main(String[] args) {
        PolygonApiClient apiClient = new PolygonApiClient();
        try {
            double[] prices = apiClient.fetchPrices("TSLA");
            System.out.println(prices[0] + ", " + prices[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}