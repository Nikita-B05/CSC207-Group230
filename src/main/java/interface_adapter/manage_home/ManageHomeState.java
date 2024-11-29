package interface_adapter.manage_home;

public class ManageHomeState {
    private boolean hasHome;
    private boolean isDarkMode;

    public boolean hasHome() {
        return hasHome;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void setHasHome(boolean hasHome) {
        this.hasHome = hasHome;
    }

    public void setDarkMode(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
    }
}
