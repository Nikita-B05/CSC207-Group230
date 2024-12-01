package use_case.game_decision;

import entity.*;

/**
 * Output Data for the Game Decision Use Case.
 */
public class GameDecisionOutputData {
    private final String username;
    private boolean isDarkMode;
    private final String name;
    private final Assets assets;
    private final Avatar avatar;

    private final int age;


    public GameDecisionOutputData(String username, boolean isDarkMode, String name, Assets assets, Avatar avatar, int age) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.name = name;
        this.assets = assets;
        this.avatar = avatar;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public Assets getAssets() {
        return assets;
    }

    public String getName() {
        return name;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }
}
