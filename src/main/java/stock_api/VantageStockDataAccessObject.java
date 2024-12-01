package stock_api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.choose_asset.ChooseAssetStockDataAccessInterface;
import use_case.manage_stock.ManageStockStockAccessInterface;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * The DAO for stock data, now using vantage api.
 */
public class VantageStockDataAccessObject implements
        ChooseAssetStockDataAccessInterface,
        ManageStockStockAccessInterface
{
    private static final String[] stockCodes = {"AAPL", "NVDA", "MSFT"};
    private static final String[] companyNames = {"Apple", "Nvidia", "Microsoft"};

    // Static HashMaps
    private static final Map<String, String> codeToCompanyMap = new HashMap<>();
    private static final Map<String, String> companyToCodeMap = new HashMap<>();

    private final Map<String, Double> codeToPrice = new HashMap<>();
    private String date = "2000-01-31";

    static {
        for (int i = 0; i < stockCodes.length; i++) {
            codeToCompanyMap.put(stockCodes[i], companyNames[i]);
            companyToCodeMap.put(companyNames[i], stockCodes[i]);
        }
    }

    private static final String BASE_URL =
            "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&outputsize=full";
    private String apiKey;
    private final OkHttpClient client;

    public VantageStockDataAccessObject() {
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

    private void loadCodeToPrice() {
        for (String code : stockCodes) {
            try {
                codeToPrice.put(code, getPrice(code));
            } catch (Exception e) {
                throw new RuntimeException("Could not generate code to price map: ", e);
            }
        }
    }

    @Override
    public double getPrice(String ticker) throws IOException {
        return fetchData(ticker);
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

    private double fetchData(String ticker) throws IOException {
        String url = BASE_URL + "&symbol=" + ticker + "&apikey=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

                JsonObject timeSeries = jsonResponse.get("Monthly Time Series").getAsJsonObject();
                // Extracting the last trade price
                String priceString = timeSeries.get(date).getAsJsonObject().get("4. close").getAsString();
                return Double.parseDouble(priceString);

            } else {
                throw new RuntimeException("Could not retrieve stock price: "
                        + response.code() + " - " + response.message());
            }
        }
    }

    public static void main(String[] args) {
        VantageStockDataAccessObject vantageStockDataAccessObject = new VantageStockDataAccessObject();
        try {
            System.out.println(vantageStockDataAccessObject.getPrice("AAPL"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
