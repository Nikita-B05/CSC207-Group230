package use_case.game_over;

import entity.Assets;
import entity.Avatar;

public class GameOverOutputData {
    private final String username;
    private final String characterName;
    private boolean isDarkMode;
    private final Assets assets;
    private final Avatar avatar;
    private final int age;

    private final int happiness;

    public GameOverOutputData(String username, String characterName, boolean isDarkMode, Assets assets, Avatar avatar, int age, int happiness) {
        this.username = username;
        this.characterName = characterName;
        this.isDarkMode = isDarkMode;
        this.assets = assets;
        this.avatar = avatar;
        this.age = age;
        this.happiness = happiness;
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

    public Assets getAssets() {
        return assets;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public int getAge() {
        return age;
    }

    public int getHappiness() {
        return happiness;
    }
}
