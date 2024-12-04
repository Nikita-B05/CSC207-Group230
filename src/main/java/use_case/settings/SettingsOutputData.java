package use_case.settings;

import entity.Avatar;

/**
 * Output Data for the Settings Use Case.
 */
public class SettingsOutputData {
    private final String username;
    private boolean isDarkMode;
    private final Avatar avatar;
    private final String name;

    public SettingsOutputData(String username, boolean isDarkMode, Avatar avatar, String name) {
        this.username = username;
        this.isDarkMode = isDarkMode;
        this.avatar = avatar;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }
    
    public boolean isDarkMode() {
        return isDarkMode;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }
}
