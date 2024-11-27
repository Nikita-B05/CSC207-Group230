package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String username;
    private final String password;
    private boolean isDarkMode;

    private final String characterName;
    private final Avatar avatar;
    private final int happiness;
    private final int salary;
    private final Assets assets;
    private final Liabilities liabilities;
    private final ArrayList<Decision> decisions;

    public CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.isDarkMode = false;
        this.characterName = null;
        this.avatar = null;
        this.happiness = 100;
        this.salary = 0;
        this.assets = null;
        this.liabilities = null;
        this.decisions = new ArrayList<>();
    }

    public CommonUser(
            String username,
            String password,
            boolean isDarkMode,
            String characterName,
            Avatar avatar,
            int happiness,
            int salary,
            Assets assets,
            Liabilities liabilities,
            ArrayList<Decision> decisions
    ) {
        this.username = username;
        this.password = password;
        this.isDarkMode = isDarkMode;
        this.characterName = characterName;
        this.avatar = avatar;
        this.happiness = happiness;
        this.salary = salary;
        this.assets = assets;
        this.liabilities = liabilities;
        this.decisions = decisions;
    }

    public CommonUser(String name, String password, boolean darkMode) {
        this.username = name;
        this.password = password;
        this.isDarkMode = darkMode;
        this.characterName = null;
        this.avatar = new Avatar();
        this.happiness = 100;
        this.salary = 0;
        this.assets = null;
        this.liabilities = null;
        this.decisions = new ArrayList<>();
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isDarkMode() {
        return isDarkMode;
    }

    @Override
    public String getCharacterName() {
        return characterName;
    }

    @Override
    public Avatar getAvatar() {
        return avatar;
    }

    @Override
    public int getHappiness() {
        return happiness;
    }

    @Override
    public int getSalary() {
        return salary;
    }

    @Override
    public Assets getAssets() {
        return assets;
    }

    @Override
    public Liabilities getLiabilities() {
        return liabilities;
    }

    @Override
    public ArrayList<Decision> getDecisions() {
        return decisions;
    }

    @Override
    public int getNetWork() {
        if (assets == null || liabilities == null) {
            return 0;
        }
        return assets.getTotal() - liabilities.getTotal();
    }

    @Override
    public void setDarkMode(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
    }
}
