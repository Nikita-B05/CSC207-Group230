package data_access;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entity.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface
{
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String DARK_MODE = "darkMode";
    private static final String CHARACTER_NAME = "characterName";
    private static final String AVATAR = "avatar";
    private static final String AVATAR_ID = "avatarId";
    private static final String IMAGE_PATH = "imagePath";
    private static final String HAPPINESS = "happiness";
    private static final String SALARY = "salary";
    private static final String ASSETS = "assets";
    private static final String HOME = "home";
    private static final String STOCKS = "stocks";
    private static final String CASH = "cash";
    private static final String CAR = "car";
    private static final String STOCK_CODE = "stockCode";
    private static final String QUANTITY = "quantity";
    private static final String BUY_PRICE = "buyPrice";
    private static final String SELL_PRICE = "sellPrice";
    private static final String MULTIPLIER = "multiplier";
    private static final String LIABILITIES = "liabilities";
    private static final String LOAN = "loan";
    private static final String CREDIT_CARD = "creditCard";
    private static final String DECISIONS = "decisions";
    private static final String TIMESTAMP = "timestamp";
    private static final String DECISION_TEXT = "decisionText";
    private static final String DECISION_RESPONSE = "decisionResponse";
    private static final String NET_WORTH_CHANGE = "netWorthChange";
    private static final String HAPPINESS_CHANGE = "happinessChange";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String MESSAGE = "failed to get user from database";
    private final UserFactory userFactory;

    public DBUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User get(String username) {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final String dbUsername = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);
                final boolean isDarkMode = userJSONObject.getBoolean(DARK_MODE);
                final String characterName = userJSONObject.getString(CHARACTER_NAME);
                final Avatar avatar = JSONObjectToAvatar(userJSONObject.getJSONObject(AVATAR));
                final int happiness = userJSONObject.getInt(HAPPINESS);
                final int salary = userJSONObject.getInt(SALARY);
                final Assets assets = JSONObjectToAssets(userJSONObject.getJSONObject(ASSETS));
                final Liabilities liabilities = JSONObjectToLiabilities(userJSONObject.getJSONObject(LIABILITIES));
                final ArrayList<Decision> decisions = JSONArrayToListOfDecision(userJSONObject.getJSONArray(DECISIONS));

                return userFactory.create(
                        dbUsername,
                        password,
                        isDarkMode,
                        characterName,
                        avatar,
                        happiness,
                        salary,
                        assets,
                        liabilities,
                        decisions
                );
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setCurrentUsername(String name) {
        // this isn't implemented for the lab
    }

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void changePassword(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }

    private static Avatar JSONObjectToAvatar(JSONObject object) {
        if (object == null) {
            return null;
        }
        final String id = object.getString(AVATAR_ID);
        final String imagePath = object.getString(IMAGE_PATH);

        return new Avatar(id, imagePath);
    }

    private static Assets JSONObjectToAssets(JSONObject object) {
        if (object == null) {
            return null;
        }
        final int home = object.getInt(HOME);
        final ArrayList<Stock> stocks = JSONArrayToListOfStock(object.getJSONArray(STOCKS));
        final int cash = object.getInt(CASH);
        final int car = object.getInt(CAR);

        return new Assets(home, stocks, cash, car);
    }

    private static ArrayList<Stock> JSONArrayToListOfStock(JSONArray array) {
        final ArrayList<Stock> stocks = new ArrayList<Stock>();
        if (array == null) {
            return stocks;
        }
        for (int i = 0; i < array.length(); i++) {
            final JSONObject jsonStockObject = array.getJSONObject(i);
            final Stock stock = JSONObjectToStock(jsonStockObject);
            if (stock != null) {
                stocks.add(stock);
            } else {
                throw new RuntimeException("JSONObject could not be converted to Stock: " + jsonStockObject.toString());
            }
        }
        return stocks;
    }

    private static Stock JSONObjectToStock(JSONObject object) {
        if (object == null) {
            return null;
        }
        final String code = object.getString(STOCK_CODE);
        final int quantity = object.getInt(QUANTITY);
        final int price = object.getInt(BUY_PRICE);
        final int sellPrice = object.getInt(SELL_PRICE);
        final int multiplier = object.getInt(MULTIPLIER);

        return new Stock(code, quantity, price, sellPrice, multiplier);
    }

    private static Liabilities JSONObjectToLiabilities(JSONObject object) {
        if (object == null) {
            return null;
        }
        final int loan = object.getInt(LOAN);
        final int creditCard = object.getInt(CREDIT_CARD);

        return new Liabilities(loan, creditCard);
    }

    private static ArrayList<Decision> JSONArrayToListOfDecision(JSONArray array) {
        final ArrayList<Decision> decisions = new ArrayList<>();
        if (array == null) {
            return decisions;
        }
        for (int i = 0; i < array.length(); i++) {
            final JSONObject jsonDecisionObject = array.getJSONObject(i);
            final Decision decision = JSONObjectToDecision(jsonDecisionObject);
            if (decision != null) {
                decisions.add(decision);
            } else {
                throw new RuntimeException(
                        "JSONObject could not be converted to Decision: " + jsonDecisionObject.toString());
            }
        }
        return decisions;
    }

    private static Decision JSONObjectToDecision(JSONObject object) {
        if (object == null) {
            return null;
        }

        final String timeStampString = object.getString(TIMESTAMP);
        if (timeStampString == null) {
            throw new RuntimeException("JSONDecisionObject is not null and has null timestamp: " + object.toString());
        }
        final LocalDateTime timestamp = LocalDateTime.parse(timeStampString, formatter);
        final String decisionText = object.getString(DECISION_TEXT);
        final String decisionReponse = object.getString(DECISION_RESPONSE);
        final int netWorthChange = object.getInt(NET_WORTH_CHANGE);
        final int happinessChange = object.getInt(HAPPINESS_CHANGE);

        return new Decision(timestamp, decisionText, decisionReponse, netWorthChange, happinessChange);
    }
}
