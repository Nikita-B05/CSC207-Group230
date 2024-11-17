package use_case.dark_mode;

/**
 * Output Data for the Dark Mode Use Case.
 */
public class DarkModeOutputData {
    private final boolean darkMode;

    public DarkModeOutputData(boolean darkMode) {
        this.darkMode = darkMode;
    }

    public boolean isDarkMode() {
        return darkMode;
    }
}
