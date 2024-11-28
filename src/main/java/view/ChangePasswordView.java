package view;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.*;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;
import use_case.settings.SettingsOutputData;

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

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.changePasswordViewModel.addPropertyChangeListener(this);

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

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(changePasswordViewModel.getState().isDarkModeEnabled());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTheme(boolean isDarkMode) {
        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
        } else {
            ColorTheme.applyLightMode(this);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            if (state.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, state.getPasswordError());
            }
            updateTheme(state.isDarkModeEnabled());
        }
        else if (evt.getPropertyName().equals("password")) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "password updated for " + state.getUsername());
        }

    }


    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}