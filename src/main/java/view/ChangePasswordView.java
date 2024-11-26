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

/**
 * The View for the Change Password screen.
 */
public class ChangePasswordView extends JPanel implements ActionListener, PropertyChangeListener {

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
            changePasswordViewModel.getState().setPassword(newPassword);
            System.out.println(changePasswordViewModel.getState().getUsername());
            changePasswordController.execute(newPassword, changePasswordViewModel.getState().getUsername());
        } else if (evt.getSource().equals(cancelButton)) {
            changePasswordViewModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("passwordChanged".equals(evt.getPropertyName())) {
            JOptionPane.showMessageDialog(this, "Password changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            changePasswordViewModel.firePropertyChanged();
        }
    }

    public void setChangePasswordController(ChangePasswordController changePasswordController) {
        this.changePasswordController = changePasswordController;
    }


    public String getViewName() {
        return viewName;
    }
}
