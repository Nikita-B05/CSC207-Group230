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
        ManageStockStockAccessInterface {
    private static final String[] STOCK_CODES = {"AAPL", "NVDA", "MSFT"};
    private static final String[] COMPANY_NAMES = {"Apple", "Nvidia", "Microsoft"};

    // Static HashMaps
    private static final Map<String, String> CODE_TO_COMPANY_MAP = new HashMap<>();
    private static final Map<String, String> COMPANY_TO_CODE_MAP = new HashMap<>();

    private final Map<String, Double> codeToPrice = new HashMap<>();
    private String date = "2000-01-31";

    static {
        for (int i = 0; i < STOCK_CODES.length; i++) {
            CODE_TO_COMPANY_MAP.put(STOCK_CODES[i], COMPANY_NAMES[i]);
            COMPANY_TO_CODE_MAP.put(COMPANY_NAMES[i], STOCK_CODES[i]);
        }
    }

    private String baseUrl = "https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&outputsize=full";

    private final String apiKey;
    private final OkHttpClient client;

    public VantageStockDataAccessObject(String baseUrl) {
        this.baseUrl = baseUrl;
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
            return properties.getProperty("vantageApi.key");
        }
        catch (IOException exp) {
            throw new RuntimeException("Failed to load API key from properties file", exp);
        }
    }

    @Override
    public Map<String, String> getCodeToName() {
        return CODE_TO_COMPANY_MAP;
    }

    @Override
    public String[] getStockNames() {
        return COMPANY_NAMES;
    }

    @Override
    public Map<String, String> getNameToCode() {
        return COMPANY_TO_CODE_MAP;
    }

    @Override
    public Map<String, Double> getCodeToPrice() {
        return codeToPrice;
    }

    private void loadCodeToPrice() {
        for (String code : STOCK_CODES) {
            try {
                codeToPrice.put(code, getPrice(code));
            }
            catch (Exception exp) {
                throw new RuntimeException("Could not generate code to price map: ", exp);
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

    private String generateDate(int age) {
        int year = 2000 + (age - 18);
        return year + "-01-31";
    }

    @Override
    public void setDate(int age) {
        String prevDate = this.date;
        this.date = generateDate(age);
        if (!prevDate.equals(date)) {
            loadCodeToPrice();
        }
    }

    private double fetchData(String ticker) throws IOException {
        String url = baseUrl + "&symbol=" + ticker + "&apikey=" + apiKey;

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

            }
            else {
                throw new RuntimeException("Could not retrieve stock price: "
                        + response.code() + " - " + response.message());
            }
        }
    }
}
