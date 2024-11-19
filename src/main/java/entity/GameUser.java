package entity;

import java.util.ArrayList;

public class GameUser implements User{
    private String name;
    private String password;

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

    public GameUser(
            String name,
            String password,
            String characterName,
            Avatar avatar,
            int happiness,
            int qualiftyOfLife,
            Assets assets,
            Liabilities liabilities,
            ArrayList<Decision> decisions
    ) {
        this.name = name;
        this.password = password;
        this.characterName = characterName;
        this.avatar = avatar;
        this.happiness = happiness;
        this.qualiftyOfLife = qualiftyOfLife;
        this.assets = assets;
        this.liabilities = liabilities;
        this.decisions = decisions;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
