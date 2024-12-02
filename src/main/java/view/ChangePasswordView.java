package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordState;
import interface_adapter.change_password.ChangePasswordViewModel;

/**
 * The View for the Change Password screen.
 */
public class ChangePasswordView extends JPanel implements PropertyChangeListener {

    private static final int BORDER_TOP = 20;
    private static final int BORDER_LEFT = 50;
    private static final int BORDER_BOTTOM = 20;
    private static final int BORDER_RIGHT = 50;
    private static final int PASSWORD_FIELD_WIDTH = 200;
    private static final int PASSWORD_FIELD_HEIGHT = 30;
    private static final int TITLE_SPACING = 20;
    private static final int BUTTON_SPACING = 20;
    private static final int BUTTON_MARGIN = 10;
    private static final int PASSSWORD_FIELD_WIDTH = 15;

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

        newPasswordField = new JPasswordField(PASSSWORD_FIELD_WIDTH);
        newPasswordField.setMaximumSize(new Dimension(PASSWORD_FIELD_WIDTH, PASSWORD_FIELD_HEIGHT));

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePasswordButton.addActionListener(event -> {
            final String newPassword = new String(newPasswordField.getPassword());
            changePasswordController.execute(newPassword, changePasswordViewModel.getState().getUsername());
        });

        cancelButton = new JButton("Cancel");
        cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelButton.addActionListener(event -> changePasswordController.switchToSettingsView());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(BORDER_TOP, BORDER_LEFT, BORDER_BOTTOM, BORDER_RIGHT));

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(Box.createRigidArea(new Dimension(0, TITLE_SPACING)));
        this.add(newPasswordField);
        this.add(Box.createRigidArea(new Dimension(0, BUTTON_SPACING)));
        this.add(changePasswordButton);
        this.add(Box.createRigidArea(new Dimension(0, BUTTON_MARGIN)));
        this.add(cancelButton);
        this.add(Box.createVerticalGlue());

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(changePasswordViewModel.getState().isDarkModeEnabled());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void updateTheme(boolean isDarkMode) {
        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
        }
        else {
            ColorTheme.applyLightMode(this);
        }
        SwingUtilities.updateComponentTreeUI(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            if (state.getPasswordError() != null) {
                JOptionPane.showMessageDialog(this, state.getPasswordError());
            }
            updateTheme(state.isDarkModeEnabled());
        }
        else if ("password".equals(evt.getPropertyName())) {
            final ChangePasswordState state = (ChangePasswordState) evt.getNewValue();
            JOptionPane.showMessageDialog(null, "Password updated for " + state.getUsername());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }
}
