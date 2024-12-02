package use_case.choose_avatar;

import entity.Avatar;

/**
 * Output Data for the Choose Avatar Use Case.
 */
public class ChooseAvatarOutputData {
    private final String username;
    private final Avatar avatar;
    private final String characterName;
    private boolean darkMode;

    public ChooseAvatarOutputData(String username, Avatar avatar, String characterName, boolean darkMode) {
        this.username = username;
        this.avatar = avatar;
        this.characterName = characterName;
        this.darkMode = darkMode;
    }

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getCharacterName() { return characterName; }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.darkMode = darkMode;
    }
}