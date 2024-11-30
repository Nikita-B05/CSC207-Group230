package interface_adapter.manage_home;

public class ManageHomeState {
    private double home;
    private boolean isDarkMode;
    private String errorMessage;
    private String successMessage;
    private double availableCash;

    public double getHome() {
        return home;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public double getAvailableCash() {
        return availableCash;
    }

    public void setHome(double home) {
        this.home = home;
    }

    public void setDarkMode(boolean isDarkMode) {
        this.isDarkMode = isDarkMode;
    }

    public boolean hasHome() {
        return home != 0;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public void setAvailableCash(double availableCash) {
        this.availableCash = availableCash;
    }
}
