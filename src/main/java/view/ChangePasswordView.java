package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.settings.SettingsViewModel;

/**
 * The View for the Change Password screen.
 */
public class ChangePasswordView extends JPanel implements PropertyChangeListener {

    private final String viewName = "changePassword";
    private final ChangePasswordViewModel changePasswordViewModel;
    private ChangePasswordController changePasswordController;

    private final JPasswordField newPasswordField;
    private final JButton changePasswordButton;
    private final JButton cancelButton;
    private final SettingsViewModel settingsViewModel;

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel,
                              SettingsViewModel settingsViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.settingsViewModel = settingsViewModel;
        this.changePasswordViewModel.addPropertyChangeListener(this);
        this.settingsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Change Password");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        newPasswordField = new JPasswordField(15);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(e -> {
            String newPassword = new String(newPasswordField.getPassword());
            changePasswordController.execute(newPassword, changePasswordViewModel.getState().getUsername());
        });

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> changePasswordController.switchToSettingsView());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(newPasswordField);
        this.add(changePasswordButton);
        this.add(cancelButton);

        applyTheme(settingsViewModel.getState().isDarkModeEnabled()); // Initial Theme Setup
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("darkMode".equals(evt.getPropertyName())) {
            boolean darkModeEnabled = settingsViewModel.getState().isDarkModeEnabled();
            applyTheme(darkModeEnabled);
        }
        if (evt.getPropertyName().equals("state")) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            if (state.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, state.getPasswordError());
            }
        }
        else if (evt.getPropertyName().equals("password")) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }

    private void applyTheme(boolean darkModeEnabled) {
        if (darkModeEnabled) {
            setBackground(Color.DARK_GRAY);
            changePasswordButton.setBackground(Color.GRAY);
            changePasswordButton.setForeground(Color.WHITE);
            cancelButton.setBackground(Color.GRAY);
            cancelButton.setForeground(Color.WHITE);
            newPasswordField.setBackground(Color.GRAY);
            newPasswordField.setForeground(Color.WHITE);
        } else {
            setBackground(Color.LIGHT_GRAY);
            changePasswordButton.setBackground(Color.WHITE);
            changePasswordButton.setForeground(Color.BLACK);
            cancelButton.setBackground(Color.WHITE);
            cancelButton.setForeground(Color.BLACK);
            newPasswordField.setBackground(Color.WHITE);
            newPasswordField.setForeground(Color.BLACK);
        }
    }



    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}
