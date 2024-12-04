package interface_adapter.homepage;

import entity.Avatar;

/**
 * The state for the Signup View Model.
 */
public class HomepageState {

    private String username;
    private Avatar avatar = new Avatar();
    private String name;
    private boolean isDarkMode;

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }
}
