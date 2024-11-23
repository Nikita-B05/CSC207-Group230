package converters;

import entity.Assets;
import entity.Avatar;
import entity.Decision;
import entity.Liabilities;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public interface EntityConverterInterface {

    static final String AVATAR_ID = "avatarId";
    static final String IMAGE_PATH = "imagePath";
    static final String HOME = "home";
    static final String STOCKS = "stocks";
    static final String STOCK_CODE = "stockCode";
    static final String QUANTITY = "quantity";
    static final String BUY_PRICE = "buyPrice";
    static final String SELL_PRICE = "sellPrice";
    static final String MULTIPLIER = "multiplier";
    static final String CASH = "cash";
    static final String CAR = "car";
    static final String LOAN = "loan";
    static final String CREDIT_CARD = "creditCard";
    static final String TIMESTAMP = "timestamp";
    static final String DECISION_TEXT = "decisionText";
    static final String DECISION_RESPONSE = "decisionResponse";
    static final String NET_WORTH_CHANGE = "netWorthChange";
    static final String HAPPINESS_CHANGE = "happinessChange";

    public Avatar toAvatar(JSONObject object);
    public Assets toAssets(JSONObject object);
    public Liabilities toLiabilities(JSONObject object);
    public ArrayList<Decision> toArrayListOfDecision(JSONArray array);
}
