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
}
