package entity;

import java.util.ArrayList;

public class GameUser implements User{
    private final String name;
    private final String password;

    private String characterName;
    private Avatar avatar;
    private int happiness;
    private int qualiftyOfLife;
    private Assets assets;
    private Liabilities liabilities;
    private ArrayList<Decision> decisions;

    public GameUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public int getHappiness() {
        return happiness;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public int getQualiftyOfLife() {
        return qualiftyOfLife;
    }

    public void setQualiftyOfLife(int qualiftyOfLife) {
        this.qualiftyOfLife = qualiftyOfLife;
    }

    public Assets getAssets() {
        return assets;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public Liabilities getLiabilities() {
        return liabilities;
    }

    public void setLiabilities(Liabilities liabilities) {
        this.liabilities = liabilities;
    }

    public ArrayList<Decision> getDecisions() {
        return decisions;
    }

    public void setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
    }

    public int getNetWork() {
        return assets.getTotal() - liabilities.getTotal();
    }
}
