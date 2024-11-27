package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String username, String password) {
        return new CommonUser(username, password);
    }

    @Override
    public User create(String username, String password, boolean isDarkMode) {
        CommonUser user = new CommonUser(username, password);
        user.setDarkMode(isDarkMode);
        return user;
    }

    @Override
    public User create(String username,
                       String password,
                       boolean isDarkMode,
                       String characterName,
                       Avatar avatar,
                       int happiness,
                       int salary,
                       Assets assets,
                       Liabilities liabilities,
                       ArrayList<Decision> decisions
    ) {
        return new CommonUser(
                username,
                password,
                isDarkMode,
                characterName,
                avatar,
                happiness,
                salary,
                assets,
                liabilities,
                decisions
        );
    }

    @Override
    public User create(String name, String password, boolean darkMode) {
        return new CommonUser(name, password, darkMode);
    }
}
