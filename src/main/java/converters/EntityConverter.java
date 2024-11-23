package converters;

import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EntityConverter implements EntityConverterInterface {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Avatar toAvatar(JSONObject object) {
        if (object == null) {
            return null;
        }
        final String id = object.getString(AVATAR_ID);
        final String imagePath = object.getString(IMAGE_PATH);

        return new Avatar(id, imagePath);
    }

    @Override
    public Assets toAssets(JSONObject object) {
        if (object == null) {
            return null;
        }
        final int home = object.getInt(HOME);
        final ArrayList<Stock> stocks = JSONArrayToListOfStock(object.getJSONArray(STOCKS));
        final int cash = object.getInt(CASH);
        final int car = object.getInt(CAR);

        return new Assets(home, stocks, cash, car);
    }

    private ArrayList<Stock> JSONArrayToListOfStock(JSONArray array) {
        final ArrayList<Stock> stocks = new ArrayList<>();
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

    private Stock JSONObjectToStock(JSONObject object) {
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

    @Override
    public Liabilities toLiabilities(JSONObject object) {
        if (object == null) {
            return null;
        }
        final int loan = object.getInt(LOAN);
        final int creditCard = object.getInt(CREDIT_CARD);

        return new Liabilities(loan, creditCard);
    }

    @Override
    public ArrayList<Decision> toArrayListOfDecision(JSONArray array) {
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

    private Decision JSONObjectToDecision(JSONObject object) {
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
