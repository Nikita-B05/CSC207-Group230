package data_access;

import entity.*;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.choose_avatar.ChooseAvatarUserDataAccessInterface;
import use_case.homepage.HomepageUserDataAccessInterface;
import use_case.input_name.InputNameUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.settings.SettingsUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the User Data Access Object.
 */
public class InMemoryUserDataAccessObject implements
        SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        HomepageUserDataAccessInterface,
        SettingsUserDataAccessInterface,
        ChooseAvatarUserDataAccessInterface,
        InputNameUserDataAccessInterface {

    private final Map<String, User> userStore = new HashMap<>();
    private String currentUsername = null;
    private final UserFactory userFactory;

    public InMemoryUserDataAccessObject(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public void save(User user) {
        userStore.put(user.getUsername(), user);
    }

    @Override
    public User get(String username) {
        if (userStore.containsKey(username)) {
            return userStore.get(username);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public boolean existsByName(String username) {
        return userStore.containsKey(username);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        if (currentUsername == null) {
            throw new IllegalStateException("No current user is set");
        }
        return currentUsername;
    }

    @Override
    public User getCurrentUser() {
        return get(getCurrentUsername());
    }

    @Override
    public void changePassword(User user) {
        save(user);
    }

    @Override
    public void updateUserDarkMode(boolean isDarkMode) {
        User user = getCurrentUser();
        user.setDarkMode(isDarkMode);
        save(user);
    }

    // Additional methods for avatar and character name

    public void updateAvatar(User user, Avatar avatar) {
        user.setAvatar(avatar);
        save(user);
    }

    public void updateCharacterName(User user, String characterName) {
        user.setCharacterName(characterName);
        save(user);
    }
}