package interface_adapter.change_password;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChangePasswordViewModel {

    private final PropertyChangeSupport support;
    private final ChangePasswordState state;

    public ChangePasswordViewModel(ChangePasswordState initialState) {
        this.state = initialState;
        this.support = new PropertyChangeSupport(this);
    }

    public void setPassword(String newPassword) {
        if (newPassword.length() < 8) {
            support.firePropertyChange("passwordError", null, "Password must be at least 8 characters long.");
        } else {
            state.setPassword(newPassword);
            support.firePropertyChange("passwordChanged", null, state);
        }
    }

    public ChangePasswordState getState() {
        return state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void firePropertyChanged(String propertyName) {
        support.firePropertyChange(propertyName, null, null);
    }
}
