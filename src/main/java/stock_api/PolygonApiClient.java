package stock_api;

import com.google.gson.JsonArray;
import okhttp3.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import use_case.choose_asset.ChooseAssetStockDataAccessInterface;
import use_case.manage_stock.ManageStockStockAccessInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class PolygonApiClient implements
        ChooseAssetStockDataAccessInterface,
        ManageStockStockAccessInterface
{

    private static final String[] stockCodes = {"AAPL", "NVDA", "MSFT"};
    private static final String[] companyNames = {"Apple", "Nvidia", "Microsoft"};

    // Static HashMaps
    private static final Map<String, String> codeToCompanyMap = new HashMap<>();
    private static final Map<String, String> companyToCodeMap = new HashMap<>();

    private static final String STOCK_DATA_YEAR_MONTH = "2024-11";

    private final Map<String, Double> codeToPrice = new HashMap<>();
    private String date = "2024-11-01";

    static {
        for (int i = 0; i < stockCodes.length; i++) {
            codeToCompanyMap.put(stockCodes[i], companyNames[i]);
            companyToCodeMap.put(companyNames[i], stockCodes[i]);
        }
    }

    private static final String BASE_URL = "https://api.polygon.io";
    private String apiKey;
    private final OkHttpClient client;

    public PolygonApiClient() {
        this.client = new OkHttpClient();
        this.apiKey = loadApiKey("config.properties");
        loadCodeToPrice();
    }

    private String loadApiKey(String fileName) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + fileName);
            }
            properties.load(input);
            return properties.getProperty("polygonApi.key");
        } catch (IOException e) {
            throw new RuntimeException("Failed to load API key from properties file", e);
        }
    }

    private void loadCodeToPrice() {
        for (String code : stockCodes) {
            try {
                codeToPrice.put(code, getPrice(code));
            } catch (Exception e) {
                throw new RuntimeException("Could not generate code to price map: ", e);
            }
        }
    }

    public String[] getStockCodes() {
        return stockCodes;
    }

    public String[] getCompanyNames() {
        return companyNames;
    }

//    @Override
    public Map<String, String> getCodeToName() {
        return codeToCompanyMap;
    }

    @Override
    public String[] getStockNames() {
        return companyNames;
    }

    @Override
    public Map<String, String> getNameToCode() {
        return companyToCodeMap;
    }

    @Override
    public Map<String, Double> getCodeToPrice() {
        return codeToPrice;
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
            } else {
                System.out.println("Request failed: " + response.code() + " - " + response.message());
            }
        }
    }

    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        String prevDate = this.date;
        this.date = date;
        if (!prevDate.equals(date)) {
            loadCodeToPrice();
        }
    }

    private double fetchPrice(String ticker, String date) throws IOException {
        String url = BASE_URL + "/v1/open-close/" + ticker + "/" + date + "?adjusted=true&apiKey=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

                // Extracting the last trade price
                return jsonResponse.get("close").getAsDouble();

            } else {
                throw new RuntimeException("Could not retrieve stock price: "
                        + response.code() + " - " + response.message());
            }
        }
    }

    @Override
    public double getPrice(String ticker) throws IOException {
        return fetchPrice(ticker, date);
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
            double price = apiClient.getPrice("AAPL");
            System.out.println(price);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}