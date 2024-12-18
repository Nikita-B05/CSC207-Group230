package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import interface_adapter.dark_mode.DarkModeController;
import interface_adapter.logout.LogoutController;
import interface_adapter.settings.SettingsController;
import interface_adapter.settings.SettingsViewModel;

/**
 * The View for the Settings screen.
 */
public class SettingsView extends JPanel implements ActionListener, PropertyChangeListener {

    public static final int TEXT_SIZE = 24;
    public static final int TWENTY = 20;
    public static final int FIFTEEN = 15;
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
        title.setFont(new Font("Arial", Font.BOLD, TEXT_SIZE));

        darkModeCheckBox = new JCheckBox("Enable Dark Mode");
        darkModeCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        darkModeCheckBox.addActionListener(this);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePasswordButton.addActionListener(this);

        logOutButton = new JButton("Log Out");
        logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logOutButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(TWENTY, TWENTY, TWENTY, TWENTY));

        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, TWENTY)));
        this.add(darkModeCheckBox);
        this.add(Box.createRigidArea(new Dimension(0, FIFTEEN)));
        this.add(changePasswordButton);
        this.add(Box.createRigidArea(new Dimension(0, FIFTEEN)));
        this.add(logOutButton);
        this.add(Box.createRigidArea(new Dimension(0, FIFTEEN)));
        this.add(cancelButton);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(settingsViewModel.getState().isDarkModeEnabled());
        }
        catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    public void setSettingsController(SettingsController settingsController) {
        this.settingsController = settingsController;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    private void updateTheme(boolean isDarkMode) {
        darkModeCheckBox.setSelected(isDarkMode);

        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
        }
        else {
            ColorTheme.applyLightMode(this);
        }

        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(darkModeCheckBox)) {
            final boolean darkModeEnabled = darkModeCheckBox.isSelected();
            darkModeController.toggleDarkMode(darkModeEnabled);
        }
        else if (evt.getSource().equals(changePasswordButton)) {
            if (settingsController != null) {
                final String username = settingsViewModel.getState().getUsername();
                settingsController.changePassword(username, darkModeCheckBox.isSelected());
            }
        }
        else if (evt.getSource().equals(logOutButton)) {
            final String username = settingsViewModel.getState().getUsername();
            logoutController.execute(username);
        }
        else if (evt.getSource().equals(cancelButton)) {
            final String username = settingsViewModel.getState().getUsername();
            settingsController.changeToHomePage(username, darkModeCheckBox.isSelected());
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final boolean darkModeEnabled = settingsViewModel.getState().isDarkModeEnabled();
        darkModeCheckBox.setSelected(darkModeEnabled);
        updateTheme(darkModeEnabled);
    }

    public String getViewName() {
        return viewName;
    }
}
