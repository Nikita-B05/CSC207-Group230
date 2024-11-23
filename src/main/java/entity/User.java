package entity;

import java.util.ArrayList;

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
     * @return the net worth of the user.
     */
    int getNetWork();
}
