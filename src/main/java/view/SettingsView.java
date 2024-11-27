package view;

import java.awt.*;
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
    private SettingsController settingsController;

    private final JCheckBox darkModeCheckBox;
    private final JButton changePasswordButton;
    private final JButton logOutButton;
    private final JButton cancelButton;

    public SettingsView(SettingsViewModel settingsViewModel,
                        DarkModeController darkModeController,
                        LogoutController logoutController) {
        this.settingsViewModel = settingsViewModel;
        this.darkModeController = darkModeController;
        this.logoutController = logoutController;

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


    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(darkModeCheckBox)) {
            boolean darkModeEnabled = darkModeCheckBox.isSelected();
            darkModeController.toggleDarkMode(darkModeEnabled);
        } else if (evt.getSource().equals(changePasswordButton)) {
            if (settingsController != null) {
                String username = settingsViewModel.getState().getUsername();
                settingsController.changePassword(username);
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

            if (darkModeEnabled) {
                setBackground(Color.DARK_GRAY);
                darkModeCheckBox.setBackground(Color.DARK_GRAY);
                darkModeCheckBox.setForeground(Color.WHITE);
                changePasswordButton.setBackground(Color.GRAY);
                changePasswordButton.setForeground(Color.WHITE);
                logOutButton.setBackground(Color.GRAY);
                logOutButton.setForeground(Color.WHITE);
                cancelButton.setBackground(Color.GRAY);
                cancelButton.setForeground(Color.WHITE);
            } else {
                setBackground(Color.LIGHT_GRAY);
                darkModeCheckBox.setBackground(Color.LIGHT_GRAY);
                darkModeCheckBox.setForeground(Color.BLACK);
                changePasswordButton.setBackground(Color.WHITE);
                changePasswordButton.setForeground(Color.BLACK);
                logOutButton.setBackground(Color.WHITE);
                logOutButton.setForeground(Color.BLACK);
                cancelButton.setBackground(Color.WHITE);
                cancelButton.setForeground(Color.BLACK);
            }
        }
    }


    public String getViewName() {
        return viewName;
    }
}
