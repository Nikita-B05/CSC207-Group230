package interface_adapter.input_name;

import entity.Avatar;

/**
 * The state for the Input Name ViewModel.
 */
public class InputNameState {
    private String username;
    private String characterName;
    private boolean isDarkMode;
    private Avatar avatar = new Avatar();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
