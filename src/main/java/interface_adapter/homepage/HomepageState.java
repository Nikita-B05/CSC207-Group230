package interface_adapter.homepage;

import entity.Avatar;
import entity.Decision;

import java.util.ArrayList;

/**
 * The state for the Signup View Model.
 */
public class HomepageState {

    private String username = null;
    private Avatar avatar = new Avatar("default", "/images/rickroll.png");
    private String name = null;
    private boolean isDarkMode = false;;
    private ArrayList<Decision> decisions = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public ArrayList<Decision> getDecisions() {
        return decisions;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

    public void setDecisions(ArrayList<Decision> decisions) {
        this.decisions = decisions;
    }
}
