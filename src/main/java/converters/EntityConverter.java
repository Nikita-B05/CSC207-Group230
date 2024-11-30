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
    public Avatar toAvatar(String jsonString) {
        if (jsonString == null) {
            return new Avatar();
        }
        JSONObject object = new JSONObject(jsonString);
        return toAvatar(object);
    }

    @Override
    public String serialize(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        final JSONObject object = new JSONObject();
        object.put(AVATAR_ID, avatar.getId());
        object.put(IMAGE_PATH, avatar.getImagePath());

        return object.toString();
    }

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
    public JSONObject toJSONObject(Avatar avatar) {
        if (avatar == null) {
            return null;
        }
        final JSONObject object = new JSONObject();
        object.put(AVATAR_ID, avatar.getId());
        object.put(IMAGE_PATH, avatar.getImagePath());

        return object;
    }

    @Override
    public Assets toAssets(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        JSONObject object = new JSONObject(jsonString);
        return toAssets(object);
    }

    @Override
    public String serialize(Assets assets) {
        if (assets == null) {
            return null;
        }
        final JSONObject object = new JSONObject();
        object.put(HOME, assets.getHome());
        object.put(STOCKS, listOfStockToJSONArray(assets.getStocks()));
        object.put(CASH, assets.getCash());
        object.put(CAR, assets.getCar());

        return object.toString();
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
            stocks.add(stock);
        }
        return stocks;
    }

    private Stock JSONObjectToStock(JSONObject object) {
        if (object == null) {
            throw new RuntimeException("JSONObject could not be converted to Stock, because JSONObject is null.");
        }
        final String code = object.getString(STOCK_CODE);
        if (code == null) {
            throw new RuntimeException("JSONObject for stock has null code.");
        }
        final int quantity = object.getInt(QUANTITY);
        final int price = object.getInt(BUY_PRICE);
        final int sellPrice = object.getInt(SELL_PRICE);
        final int multiplier = object.getInt(MULTIPLIER);

        return new Stock(code, quantity, price, sellPrice, multiplier);
    }

    private JSONArray listOfStockToJSONArray(ArrayList<Stock> stocks) {
        if (stocks == null) {
            throw new RuntimeException("stocks array is null");
        }
        final JSONArray array = new JSONArray();
        for (Stock stock : stocks) {
            array.put(toJSONObject(stock));
        }
        return array;
    }

    private JSONObject toJSONObject(Stock stock) {
        if (stock == null) {
            throw new RuntimeException("stock is null");
        }
        final JSONObject object = new JSONObject();
        object.put(STOCK_CODE, stock.getStockCode());
        object.put(QUANTITY, stock.getQuantity());
        object.put(BUY_PRICE, stock.getBuyPrice());
        object.put(SELL_PRICE, stock.getSellPrice());
        object.put(MULTIPLIER, stock.getMultiplier());

        return object;
    }

    @Override
    public JSONObject toJSONObject(Assets assets) {
        if (assets == null) {
            return null;
        }
        final JSONObject object = new JSONObject();
        object.put(HOME, assets.getHome());
        object.put(STOCKS, listOfStockToJSONArray(assets.getStocks()));
        object.put(CASH, assets.getCash());
        object.put(CAR, assets.getCar());

        return object;
    }

    @Override
    public Liabilities toLiabilities(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        JSONObject object = new JSONObject(jsonString);
        return toLiabilities(object);
    }

    @Override
    public String serialize(Liabilities liabilities) {
        if (liabilities == null) {
            return null;
        }
        final JSONObject object = new JSONObject();
        object.put(LOAN, liabilities.getLoan());
        object.put(CREDIT_CARD, liabilities.getCreditCard());

        return object.toString();
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
    public JSONObject toJSONObject(Liabilities liabilities) {
        if (liabilities == null) {
            return null;
        }
        final JSONObject object = new JSONObject();
        object.put(LOAN, liabilities.getLoan());
        object.put(CREDIT_CARD, liabilities.getCreditCard());

        return object;
    }

    @Override
    public ArrayList<Decision> toArrayListOfDecision(String jsonString) {
        if (jsonString == null) {
            return new ArrayList<>();
        }
        JSONArray array = new JSONArray(jsonString);
        return toArrayListOfDecision(array);
    }

    @Override
    public String serialize(ArrayList<Decision> decisions) {
        if (decisions == null) {
            return null;
        }
        final JSONArray array = new JSONArray();
        for (Decision decision : decisions) {
            array.put(toJSONObject(decision));
        }
        return array.toString();
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
        final int netWorthChange = object.getInt(NET_WORTH_CHANGE);
        final int happinessChange = object.getInt(HAPPINESS_CHANGE);

        return new Decision(timestamp, decisionText, netWorthChange, happinessChange);
    }

    private JSONObject toJSONObject(Decision decision) {
        if (decision == null) {
            throw new RuntimeException("decision is null");
        }
        final JSONObject object = new JSONObject();
        object.put(TIMESTAMP, decision.getTimestamp().format(formatter));
        object.put(DECISION_TEXT, decision.getDecisionText());
        object.put(NET_WORTH_CHANGE, decision.getCashChange());
        object.put(HAPPINESS_CHANGE, decision.getHappinessChange());

        return object;
    }

    @Override
    public JSONArray toJSONArray(ArrayList<Decision> decisions) {
        if (decisions == null) {
            return null;
        }
        final JSONArray array = new JSONArray();
        for (Decision decision : decisions) {
            array.put(toJSONObject(decision));
        }
        return array;
    }
}
