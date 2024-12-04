package stock_api;

import okhttp3.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import use_case.choose_asset.ChooseAssetStockDataAccessInterface;
import use_case.manage_stock.ManageStockStockAccessInterface;
import use_case.homepage.HomepageStockAccessInterface;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PolygonStockDataAccessObject implements
        ChooseAssetStockDataAccessInterface,
        HomepageStockAccessInterface,
        ManageStockStockAccessInterface {
    private static final String[] STOCK_CODES = {"AAPL", "NVDA"};
    private static final String[] COMPANY_NAMES = {"Apple", "Nvidia"};

    // Static HashMaps
    private static final Map<String, String> CODE_TO_COMPANY_MAP = new HashMap<>();
    private static final Map<String, String> COMPANY_TO_CODE_MAP = new HashMap<>();

    private final Map<String, Double> codeToPrice = new HashMap<>();
    private String date = "2024-11-01";

    static {
        for (int i = 0; i < STOCK_CODES.length; i++) {
            CODE_TO_COMPANY_MAP.put(STOCK_CODES[i], COMPANY_NAMES[i]);
            COMPANY_TO_CODE_MAP.put(COMPANY_NAMES[i], STOCK_CODES[i]);
        }
    }

    private final String baseUrl = "https://api.polygon.io";
    private String apiKey;
    private final OkHttpClient client;

    public PolygonStockDataAccessObject() {
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
        }
        catch (IOException exp) {
            throw new RuntimeException("Failed to load API key from properties file", exp);
        }
    }

    private void loadCodeToPrice() {
        for (String code : STOCK_CODES) {
            try {
                codeToPrice.put(code, getPrice(code));
            }
            catch (Exception exp) {
                throw new RuntimeException("Could not generate code to price map: ", exp);
                // Fail silently
            }
        }
    }

    public String[] getStockCodes() {
        return STOCK_CODES;
    }

    public String[] getCompanyNames() {
        return COMPANY_NAMES;
    }

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

    @Override
    public Map<String, Double> getStockPrices() {
        return codeToPrice;
    }

    public String getDate() {
        return date;
    }

    private String generateDate(int age) {
        // Format the date to YYYY-MM-DD
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Get the current date
        LocalDate currentDate = LocalDate.parse("2024-10-01", formatter);

        // Add a certain number of days
        int daysToAdd = age - 20;
        LocalDate newDate = currentDate.plusDays(daysToAdd);

        String formattedDate = newDate.format(formatter);

        return formattedDate;
    }

    @Override
    public void setDate(int age) {
        String prevDate = this.date;
        this.date = generateDate(age);
        if (!prevDate.equals(date)) {
            loadCodeToPrice();
        }
    }

    private double fetchPrice(String ticker, String queryDate) throws IOException {
        String url = baseUrl + "/v1/open-close/" + ticker + "/" + queryDate + "?adjusted=true&apiKey=" + apiKey;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();

                return jsonResponse.get("close").getAsDouble();
            }
            else {
                throw new RuntimeException("Could not retrieve stock price: "
                        + response.code() + " - " + response.message());
            }
        }
    }

    @Override
    public double getPrice(String ticker) throws IOException {
        return fetchPrice(ticker, date);
    }

    public static void main(String[] args) {
        PolygonStockDataAccessObject apiClient = new PolygonStockDataAccessObject();
        try {
            apiClient.setDate(10);
            double price = apiClient.getPrice("AAPL");
            System.out.println(price);
        }
        catch (IOException exp) {
            exp.printStackTrace();
        }
    }
}
