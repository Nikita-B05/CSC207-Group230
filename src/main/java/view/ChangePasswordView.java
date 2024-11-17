package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordViewModel;
import interface_adapter.change_password.ChangePasswordState;

/**
 * The View for the Change Password screen.
 */
public class ChangePasswordView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "changePassword";
    private final ChangePasswordViewModel changePasswordViewModel;
    private final ChangePasswordController changePasswordController;

    private final JPasswordField newPasswordField;
    private final JButton changePasswordButton;
    private final JButton cancelButton;

    public ChangePasswordView(ChangePasswordViewModel changePasswordViewModel,
                              ChangePasswordController changePasswordController) {
        this.changePasswordViewModel = changePasswordViewModel;
        this.changePasswordController = changePasswordController;

        this.changePasswordViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("Change Password");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        newPasswordField = new JPasswordField(15);

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(newPasswordField);
        this.add(changePasswordButton);
        this.add(cancelButton);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource().equals(changePasswordButton)) {
            String newPassword = new String(newPasswordField.getPassword());
            changePasswordViewModel.setPassword(newPassword);
            changePasswordController.execute(newPassword, changePasswordViewModel.getState().getUsername());
        } else if (evt.getSource().equals(cancelButton)) {
            changePasswordViewModel.firePropertyChanged("navigateBack");
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("passwordError".equals(evt.getPropertyName())) {
            String errorMessage = (String) evt.getNewValue();
            JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("passwordChanged".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            changePasswordViewModel.firePropertyChanged("navigateBack");
        }
    }

    public String getViewName() {
        return viewName;
    }
}
