package use_case.dark_mode;

/**
 * Input Data for the Dark Mode Use Case.
 */
public class DarkModeInputData {
    private final boolean darkMode;

    public DarkModeInputData(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isDarkMode() {
        return darkMode;
    }
}
