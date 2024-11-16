package entity;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the ui mode of the user
     * @return the boolean that represents if the user is on darkmode or not
     */
    Boolean getDarkMode();

    /**
     * Sets whether the user is using dark mode or not
     */
    void setDarkMode(Boolean darkMode);
}
