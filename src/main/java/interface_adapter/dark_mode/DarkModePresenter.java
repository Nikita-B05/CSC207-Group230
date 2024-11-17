package interface_adapter.dark_mode;

import use_case.dark_mode.DarkModeOutputBoundary;
import use_case.dark_mode.DarkModeOutputData;
import interface_adapter.settings.SettingsViewModel;

/**
 * Presenter for the Dark Mode Use Case.
 */
public class DarkModePresenter implements DarkModeOutputBoundary {
    private final SettingsViewModel settingsViewModel;

    public DarkModePresenter(SettingsViewModel settingsViewModel) {
        this.settingsViewModel = settingsViewModel;
    }

    @Override
    public void updateUIMode(DarkModeOutputData outputData) {
        settingsViewModel.getState().setDarkModeEnabled(outputData.isDarkMode());
        settingsViewModel.firePropertyChanged("darkMode");
    }
}
