package use_case.homepage;

import entity.Avatar;
import entity.Decision;

import java.util.ArrayList;

public class HomepageOutputData {
    private final String username;
    private final Avatar avatar;
    private final boolean isDarkMode;
    private final ArrayList<Decision> decisions;

    // If needed, add stats: network, quality of life, happiness

    public HomepageOutputData(String username, Avatar avatar, boolean isDarkMode, ArrayList<Decision> decisions) {
        this.username = username;
        this.avatar = avatar;
        this.isDarkMode = isDarkMode;
        this.decisions = decisions;
    }

    public String getUsername() {
        return username;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public ArrayList<Decision> getDecisions() {
        return decisions;
    }
}
