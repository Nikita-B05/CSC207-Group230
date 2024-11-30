package entity;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private String username;
    private String password;
    private boolean isDarkMode;


    private String characterName;
    private int age;
    private Avatar avatar;
    private int happiness;
    private int salary;
    private Assets assets;
    private Liabilities liabilities;
    private ArrayList<Decision> decisions;

    public CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.age = 18;
        this.isDarkMode = false;
        this.characterName = null;
        this.avatar = new Avatar();
        this.happiness = 100;
        this.salary = 0;
        this.assets = null;
        this.liabilities = null;
        this.decisions = new ArrayList<>();
    }

    public CommonUser(
            String username,
            String password,
            int age,
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
        this.age = age;
        this.isDarkMode = isDarkMode;
        this.characterName = characterName;
        this.avatar = avatar;
        this.happiness = happiness;
        this.salary = salary;
        this.assets = assets;
        this.liabilities = liabilities;
        this.decisions = decisions;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public void setAge(int Age) {
        this.age = Age;
    }

    @Override
    public void changeHappiness(double happiness) {
        this.happiness += happiness;
    }

    @Override
    public void addDecision(Decision decision) {
        this.decisions.add(decision);
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setAssets(Assets assets) {
        this.assets = assets;
    }

    public void setLiabilities(Liabilities liabilities) {
        this.liabilities = liabilities;
    }

    public void setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
    }



    @Override
    public int getNetWork() {
        if (assets == null || liabilities == null) {
            return 0;
        }
        return assets.getTotal() - liabilities.getTotal();
    }
}
