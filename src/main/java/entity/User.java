package entity;

import java.util.ArrayList;
import java.util.Map;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Sets the characterName of the user.
     * @param characterName the new character name of the user.
     */
    void setCharacterName(String characterName);

    /**
     * Sets the avatar of the user.
     * @param avatar the new avatar of the user.
     */
    void setAvatar(Avatar avatar);

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
    double getSalary();

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
    double getNetWorth(Map<String, Double> stockPrices);

    /**
     * Returns the dark mode UI config of the user.
     */
    void setDarkMode(boolean isDarkMode);

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
    public boolean canBuyStock(String stockCode, int quantity, Map<String, Double> stockPrices);

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

    int getAge();

    public void setAge(int Age);

    /**
     * Changes the user's happiness by happiness.
     * @param happiness the amount to change user's happiness by.
     */
    void changeHappiness(int happiness);

    public void addDecision(Decision decision);

    public void modifySalary(double modification);

    public Map<Integer, Question> getQuestion();

    void setAssets(Assets assets);

    void setDecisions(ArrayList<Decision> decisions);

    /**
     * Set the user's new salary.
     * @param salary the user's new salary.
     */
    public void setSalary(double salary);

    /**
     * Set the user's new happiness.
     * @param happiness the user's new happiness.
     */
    public void setHappiness(int happiness);

    void setHappiness(int happiness);

    void setSalary(int salary);

    void setAssets(Assets assets);
}
