package use_case.dark_mode;

import entity.User;

public interface DarkModeUserDataAccessInterface {
    User getCurrentUser();
    void updateUserDarkMode(User user);
}
