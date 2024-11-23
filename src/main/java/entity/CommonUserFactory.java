package entity;

import java.util.ArrayList;

/**
 * Factory for creating CommonUser objects.
 */
public class CommonUserFactory implements UserFactory {

    @Override
    public User create(String username, String password) {
        return new CommonUser(username, password);
    }

    @Override
    public User create(String username,
                       String password,
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
                characterName,
                avatar,
                happiness,
                salary,
                assets,
                liabilities,
                decisions
        );
    }
}
