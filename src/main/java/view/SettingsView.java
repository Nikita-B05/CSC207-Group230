package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.settings.SettingsController;
import interface_adapter.settings.SettingsViewModel;
import interface_adapter.dark_mode.DarkModeController;
import interface_adapter.logout.LogoutController;
import interface_adapter.settings.SettingsPresenter;

/**
 * The View for the Settings screen.
 */
public class SettingsView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "settings";
    private final SettingsViewModel settingsViewModel;
    private final DarkModeController darkModeController;
    private LogoutController logoutController;
    private final SettingsPresenter settingsPresenter;
    private ChangePasswordController changePasswordController;
    private SettingsController settingsController;

    private final JCheckBox darkModeCheckBox;
    private final JButton changePasswordButton;
    private final JButton logOutButton;
    private final JButton cancelButton;

    public SettingsView(SettingsViewModel settingsViewModel,
                        DarkModeController darkModeController,
                        LogoutController logoutController,
                        SettingsPresenter settingsPresenter) {
        this.settingsViewModel = settingsViewModel;
        this.darkModeController = darkModeController;
        this.logoutController = logoutController;
        this.settingsPresenter = settingsPresenter;

        this.settingsViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Settings");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        darkModeCheckBox = new JCheckBox("Enable Dark Mode");
        darkModeCheckBox.addActionListener(this);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(this);

        logOutButton = new JButton("Log Out");
        logOutButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(darkModeCheckBox);
        this.add(changePasswordButton);
        this.add(logOutButton);
        this.add(cancelButton);
    }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(darkModeCheckBox)) {
            boolean darkModeEnabled = darkModeCheckBox.isSelected();
            darkModeController.toggleDarkMode(darkModeEnabled);
        } else if (evt.getSource().equals(changePasswordButton)) {
            if (changePasswordController != null) {
                String username = settingsViewModel.getState().getUsername();
                String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");  // Collect new password from user
                if (newPassword != null && !newPassword.isEmpty()) {
                    changePasswordController.execute(newPassword, username);  // Pass both new password and username
                } else {
                    JOptionPane.showMessageDialog(this, "Password cannot be empty.");
                }
            }
        } else if (evt.getSource().equals(logOutButton)) {
            String username = settingsViewModel.getState().getUsername();
            logoutController.execute(username);
        } else if (evt.getSource().equals(cancelButton)) {
            JOptionPane.showMessageDialog(this, "Cancel button pressed (no action assigned yet).");
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("darkMode".equals(evt.getPropertyName())) {
            boolean darkModeEnabled = settingsViewModel.getState().isDarkModeEnabled();
            darkModeCheckBox.setSelected(darkModeEnabled);
        }
    }

    public String getViewName() {
        return viewName;
    }
}
