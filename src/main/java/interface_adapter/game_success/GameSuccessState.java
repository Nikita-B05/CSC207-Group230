package interface_adapter.game_success;

import entity.Avatar;

public class GameSuccessState {
    private String username;
    private String characterName;
    private Avatar avatar = new Avatar();
    private double netWorth;
    private int happiness;
    private boolean isDarkMode;

    public String getUsername() {
        return username;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public int getHappiness() {
        return happiness;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setDarkMode(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
    }
}
