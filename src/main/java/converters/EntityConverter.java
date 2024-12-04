package converters;

import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EntityConverter implements EntityConverterInterface {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
    public JSONObject toJSONObject(Liabilities liabilities) {
        if (liabilities == null) {
            return null;
        }
        final JSONObject object = new JSONObject();
        object.put(LOAN, liabilities.getLoan());
        object.put(CREDIT_CARD, liabilities.getCreditCard());

        return object;
    }

    private JSONObject toJSONObject(Stock stock) {
        if (stock == null) {
            throw new RuntimeException("stock is null");
        }
        final JSONObject object = new JSONObject();
        object.put(STOCK_CODE, stock.getStockCode());
        object.put(QUANTITY, stock.getQuantity());
        object.put(BUY_PRICE, stock.getBuyPrice());
        object.put(MULTIPLIER, stock.getMultiplier());

        return object;
    }

    private JSONObject toJSONObject(Decision decision) {
        if (decision == null) {
            throw new RuntimeException("decision is null");
        }
        final JSONObject object = new JSONObject();
        object.put(AGE, decision.getAge());
        object.put(DECISION_TEXT, decision.getDecisionText());
        object.put(DECISION_RESPONSE, decision.getResponse());
        object.put(NET_WORTH_CHANGE, decision.getNetWorthChange());
        object.put(HAPPINESS_CHANGE, decision.getHappinessChange());
        object.put(SALARY_CHANGE, decision.getSalaryChange());

        return object;
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
    public Avatar toAvatar(JSONObject object) {
        if (object == null) {
            return null;
        }
        final String id = object.getString(AVATAR_ID);
        final String imagePath = object.getString(IMAGE_PATH);

        return new Avatar(id, imagePath);
    }

    @Override
    public Avatar toAvatar(String jsonString) {
        if (jsonString == null) {
            return new Avatar();
        }
        JSONObject object = new JSONObject(jsonString);
        return toAvatar(object);
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
    public Assets toAssets(JSONObject object) {
        if (object == null) {
            return null;
        }
        final double home = object.getDouble(HOME);
        final ArrayList<Stock> stocks = jsonArrayToListOfStock(object.getJSONArray(STOCKS));
        final double cash = object.getDouble(CASH);
        final double car = object.getDouble(CAR);

        return new Assets(home, stocks, cash, car);
    }

    private ArrayList<Stock> jsonArrayToListOfStock(JSONArray array) {
        final ArrayList<Stock> stocks = new ArrayList<>();
        if (array == null) {
            return stocks;
        }
        for (int i = 0; i < array.length(); i++) {
            final JSONObject jsonStockObject = array.getJSONObject(i);
            final Stock stock = jsonObjectToStock(jsonStockObject);
            stocks.add(stock);
        }
        return stocks;
    }

    private Stock jsonObjectToStock(JSONObject object) {
        if (object == null) {
            throw new RuntimeException("JSONObject could not be converted to Stock, because JSONObject is null.");
        }
        final String code = object.getString(STOCK_CODE);
        if (code == null) {
            throw new RuntimeException("JSONObject for stock has null code.");
        }
        final int quantity = object.getInt(QUANTITY);
        final double price = object.getDouble(BUY_PRICE);
        final int multiplier = object.getInt(MULTIPLIER);

        return new Stock(code, quantity, price, multiplier);
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

    @Override
    public Liabilities toLiabilities(String jsonString) {
        if (jsonString == null) {
            return null;
        }
        JSONObject object = new JSONObject(jsonString);
        return toLiabilities(object);
    }

    @Override
    public Liabilities toLiabilities(JSONObject object) {
        if (object == null) {
            return null;
        }
        final double loan = object.getDouble(LOAN);
        final double creditCard = object.getDouble(CREDIT_CARD);

        return new Liabilities(loan, creditCard);
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
    public ArrayList<Decision> toArrayListOfDecision(JSONArray array) {
        final ArrayList<Decision> decisions = new ArrayList<>();
        if (array == null) {
            return decisions;
        }
        for (int i = 0; i < array.length(); i++) {
            final JSONObject jsonDecisionObject = array.getJSONObject(i);
            final Decision decision = jsonObjectToDecision(jsonDecisionObject);
            if (decision != null) {
                decisions.add(decision);
            }
            else {
                throw new RuntimeException(
                        "JSONObject could not be converted to Decision: " + jsonDecisionObject.toString());
            }
        }
        return decisions;
    }

    private Decision jsonObjectToDecision(JSONObject object) {
        if (object == null) {
            return null;
        }
        final int age = object.optInt(AGE, 22);
        final String decisionText = object.getString(DECISION_TEXT);
        final String response = object.optString(DECISION_RESPONSE, "");
        final int netWorthChange = object.getInt(NET_WORTH_CHANGE);
        final int happinessChange = object.getInt(HAPPINESS_CHANGE);
        final int salaryChange = object.getInt(SALARY_CHANGE);

        return new Decision(age, decisionText, response, netWorthChange, happinessChange, salaryChange);
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
