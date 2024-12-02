package use_case.game_success;

import entity.Avatar;

public class GameSuccessOutputData {
    private final String username;
    private final String characterName;
    private boolean isDarkMode;
    private final Avatar avatar;

    public GameSuccessOutputData(String username, String characterName, boolean isDarkMode, Avatar avatar) {
        this.username = username;
        this.characterName = characterName;
        this.isDarkMode = isDarkMode;
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public String getCharacterName() {
        return characterName;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
