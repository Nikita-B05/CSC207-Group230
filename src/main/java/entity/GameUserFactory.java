package entity;

public class GameUserFactory implements UserFactory {

    @Override
    public User create(String name, String password) {
        return new GameUser(name, password);
    }

    public User create(String name, String password, boolean isDarkMode) {
        return new GameUser(name, password, isDarkMode);
    }
}
