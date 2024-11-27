package interface_adapter.settings;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Settings View.
 */
public class SettingsViewModel extends ViewModel<SettingsState> {

    public SettingsViewModel() {
        super("settings");
        setState(new SettingsState());
    }

    public void setDarkMode(boolean isDarkMode) {
        getState().setDarkModeEnabled(isDarkMode);
        firePropertyChanged("darkMode");
    }
}
