package use_case.manage_home;

/**
 * The Output Data for the Manage Home Use Case.
 */
public class ManageHomeOutputData {
    private boolean isDarkMode;

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void setDarkMode(boolean darkMode) {
        this.isDarkMode = darkMode;
    }
}
