package use_case.game_over;

import entity.Assets;
import entity.Avatar;

/**
 * Input Data for the Game Over use case.
 */
public class GameOverInputData {
    private final String username;
    private final String characterName;
    private final int age;
    private final boolean darkMode;
    private final Avatar avatar;
    private final int happiness;
    private final Assets assets;

    public GameOverInputData(Assets assets, int happiness, Avatar avatar, boolean darkMode, int age, String characterName, String username) {
        this.assets = assets;
        this.happiness = happiness;
        this.avatar = avatar;
        this.darkMode = darkMode;
        this.age = age;
        this.characterName = characterName;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getCharacterName() {
        return characterName;
    }

    public int getAge() {
        return age;
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public int getHappiness() {
        return happiness;
    }

    public Assets getAssets() {
        return assets;
    }
}
