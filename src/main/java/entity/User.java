package entity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getUsername();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns whether the user is on dark mode.
     * @return the dark mode flag of the user.
     */
    boolean isDarkMode();

    /**
     * Returns the characterName of the user.
     * @return the characterName of the user.
     */
    String getCharacterName();

    /**
     * Returns the avatar of the user.
     * @return the avatar of the user.
     */
    Avatar getAvatar();

    /**
     * Returns the happiness of the user.
     * @return the happiness of the user.
     */
    int getHappiness();

    /**
     * Returns the salary of the user.
     * @return the salary of the user.
     */
    public int getSalary();

    /**
     * Returns the assets of the user.
     * @return the assets of the user.
     */
    Assets getAssets();

    /**
     * Returns the liabilities of the user.
     * @return the liabilities of the user.
     */
    Liabilities getLiabilities();

    /**
     * Returns the decisions of the user.
     * @return the decisions of the user.
     */
    ArrayList<Decision> getDecisions();

    /**
     * Returns the net worth (assets - liabilities) of the user.
     * @param stockPrices is the current market prices of stocks.
     * @return the net worth of the user.
     */
    double getNetWorth(HashMap<String, Double> stockPrices);

    /**
     * Sets the user's username
     * @param username new username
     */
    void setUsername(String username);

    /**
     * Sets the user's password
     * @param password new password
     */
    void setPassword(String password);

    /**
     * Sets the user's darkMode
     * @param isDarkMode new darkMode
     */
    void setDarkMode(boolean isDarkMode);

    /**
     * Sets the user's characterName
     * @param characterName new characterName
     */
    void setCharacterName(String characterName);

    /**
     * Sets the user's avatar
     * @param avatar new avatar
     */
    void setAvatar(Avatar avatar);

    /**
     * Sets the user's happiness
     * @param happiness new happiness
     */
    void setHappiness(int happiness);

    /**
     * Sets the user's salary
     * @param salary new salary
     */
    void setSalary(int salary);

    /**
     * Sets the user's assets
     * @param assets new assets
     */
    void setAssets(Assets assets);

    /**
     * Sets the user's liabilities
     * @param liabilities new liabilities
     */
    void setLiabilities(Liabilities liabilities);

    /**
     * Sets the user's decisions
     * @param decisions new decisions
     */
    void setDecisions(ArrayList<Decision> decisions);

    /**
     * Buys a stock for the user.
     * Precondition: User has sufficient cash.
     * @param stockCode code of the stock to be bought.
     * @param quantity number of stock to be bought.
     * @param buyPrice price of stock to be bought.
     */
    public void buyStock(String stockCode, int quantity, double buyPrice);

    /**
     * Returns if the user has the funds to buy stock.
     * @param stockCode code of the stock to be bought.
     * @param quantity number of stock to be bought.
     * @param stockPrices is the current market prices of stocks.
     * @return if the user has the funds to buy stock.
     */
    public boolean canBuyStock(String stockCode, int quantity, HashMap<String, Double> stockPrices);

    /**
     * Returns the money made on the stock sold.
     * Precondition: assumes the user owns enough stock to sell.
     * @param stockCode code of the stock to be sold.
     * @param quantity number of stock to be sold.
     * @param sellPrice price of stock to be sold.
     * @return the money made on the stock sold.
     */
    public double sellStock(String stockCode, int quantity, double sellPrice);

    /**
     * Returns if the user can sell the stock.
     * @param stockCode code of the stock to be sold.
     * @param quantity number of stock to be sold.
     * @return if the user can sell the stock.
     */
    public boolean isValidSell(String stockCode, int quantity);
}
