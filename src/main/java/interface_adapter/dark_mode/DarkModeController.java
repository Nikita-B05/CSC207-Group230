package interface_adapter.dark_mode;

import use_case.dark_mode.DarkModeInputBoundary;
import use_case.dark_mode.DarkModeInputData;

/**
 * Controller for the Dark Mode Use Case.
 */
public class DarkModeController {
    private final DarkModeInputBoundary darkModeInteractor;

    public DarkModeController(DarkModeInputBoundary darkModeInteractor) {
        this.darkModeInteractor = darkModeInteractor;
    }

    /**
     * Toggles the dark mode setting.
     * @param darkMode toggles dark mode on the product
     */
    public void toggleDarkMode(boolean darkMode) {
        DarkModeInputData inputData = new DarkModeInputData(darkMode);
        darkModeInteractor.toggleDarkMode(inputData);
    }
}
