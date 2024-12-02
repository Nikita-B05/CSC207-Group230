package entity;

import java.util.ArrayList;
import java.util.Map;

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
     * Sets the user's username.
     * @param username new username
     */
    void setUsername(String username);

    /**
     * Sets the user's password.
     * @param password new password
     */
    void setPassword(String password);

    /**
     * Sets the user's darkMode.
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
     * Sets the user's happiness.
     * @param happiness new happiness
     */
    void setHappiness(int happiness);

    /**
     * Sets the user's salary.
     * @param salary new salary
     */
    void setSalary(int salary);

    /**
     * Sets the user's assets.
     * @param assets new assets
     */
    void setAssets(Assets assets);

    /**
     * Sets the user's liabilities.
     * @param liabilities new liabilities
     */
    void setLiabilities(Liabilities liabilities);

    /**
     * Sets the user's decisions.
     * @param decisions new decisions
     */
    void setDecisions(ArrayList<Decision> decisions);

    /**
     * Returns the age of the user.
     * @return the age of the user.
     */
    int getAge();

    /**
     * Sets the user's age.
     * @param age new age
     */
    void setAge(int age);

    /**
     * Changes the user's happiness by happiness.
     * @param happiness the amount to change user's happiness by.
     */
    void changeHappiness(double happiness);

    /**
     * Adds a decision to the user.
     * @param decision the decision to be added.
     */
    void addDecision(Decision decision);

    /**
     * Changes the user's salary by modification.
     * @param modification the amount to change user's salary by.
     */
    void modifySalary(double modification);

    /**
     * Returns the map of age to question.
     * @return the map of age to question.
     */
    Map<Integer, Question> getQuestion();
}
