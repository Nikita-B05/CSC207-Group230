package interface_adapter.choose_avatar;

import entity.Avatar;

/**
 * The state for the Choose Avatar ViewModel.
 */
public class ChooseAvatarState {
    private String username;
    private Avatar avatar;
    private boolean isDarkMode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }
}
