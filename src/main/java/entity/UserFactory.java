package entity;

import java.util.ArrayList;

/**
 * Factory for creating users.
 */
public interface UserFactory {
    /**
     * Creates a new User.
     * @param username the name of the new user
     * @param password the password of the new user
     * @return the new user
     */
    User create(String username, String password);

    /**
     * Creates a new User.
     * @param username the name of the new user
     * @param password the password of the new user
     * @param age the age of the new use
     * @param isDarkMode the dark mode flag for the new user
     * @param characterName the character name of the new user
     * @param avatar the avatar of the new user
     * @param happiness the happiness of the new user
     * @param salary the salary of the new user
     * @param assets the assets of the new user
     * @param liabilities the assets of the new user
     * @param decisions the assets of the new user
     * @return the new user
     */
    User create(String username,
                String password,
                int age,
                boolean isDarkMode,
                String characterName,
                Avatar avatar,
                int happiness,
                double salary,
                Assets assets,
                Liabilities liabilities,
                ArrayList<Decision> decisions
    );
}
