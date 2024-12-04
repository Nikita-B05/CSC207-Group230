package name_api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NameApiClient {

    private final String apiKey;

    public NameApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public JsonObject validateName(String name) throws IOException {
        // Prepare the JSON payload
        String payload = "{ \"context\": {}, \"inputPerson\": { \"type\": \"NaturalInputPerson\", " +
                "\"personName\": { \"nameFields\": [ { \"string\": \"" + name +
                "\", \"fieldType\": \"GIVENNAME\" } ] } } }";

        // Set up the connection
        URL url = new URL("https://api.nameapi.org/rest/v5.3/riskdetector/person?apiKey=" + apiKey);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Send the request
        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = payload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read the response
        try (InputStream is = conn.getInputStream()) {
            String jsonResponse = new String(is.readAllBytes(), "utf-8");
            return JsonParser.parseString(jsonResponse).getAsJsonObject();
        }
    }
}
