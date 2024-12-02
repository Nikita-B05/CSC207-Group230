package view;

import java.awt.*;
import java.beans.PropertyChangeEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import data_access.config.ConfigLoader;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import name_api.NameApiClient;

/**
 * The view for signing up in the application.
 * Displays a question with options and allows the user to make decisions.
 *
 * <p>Some methods and parameters may accept or return <code>null</code> values.</p>
 */

public class SignupView extends JPanel {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 120;
    private final String viewName = "sign up";

    private final SignupViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private SignupController signupController;

    private final JButton signUp;
    private final JButton toLogin;

    // NameApiClient for validation
    private final NameApiClient nameApiClient;

    public SignupView(SignupViewModel signupViewModel) {
        this.signupViewModel = signupViewModel;
        this.signupViewModel.addPropertyChangeListener(evt -> handlePropertyChange(evt));

        // Initialize NameApiClient
        final String apiKey = ConfigLoader.getProperty("nameapi.key");
        this.nameApiClient = new NameApiClient(apiKey);

        final JLabel title = new JLabel(SignupViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.USERNAME_LABEL), usernameInputField);
        final LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.PASSWORD_LABEL), passwordInputField);
        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

        final JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        inputPanel.setMaximumSize(new Dimension(WIDTH, HEIGHT));

        inputPanel.add(usernameInfo);
        inputPanel.add(passwordInfo);
        inputPanel.add(repeatPasswordInfo);

        final JPanel buttons = new JPanel();
        toLogin = new JButton(SignupViewModel.TO_LOGIN_BUTTON_LABEL);
        buttons.add(toLogin);
        signUp = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);

        signUp.addActionListener(evt -> handleSignup());

        toLogin.addActionListener(evt -> signupController.switchToLoginView());

        addUsernameListener();
        addPasswordListener();
        addRepeatPasswordListener();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(inputPanel);
        this.add(buttons);
        this.add(Box.createVerticalGlue());
    }

    private void addUsernameListener() {
        usernameInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                signupViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addPasswordListener() {
        passwordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setPassword(new String(passwordInputField.getPassword()));
                signupViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void addRepeatPasswordListener() {
        repeatPasswordInputField.getDocument().addDocumentListener(new DocumentListener() {
            private void documentListenerHelper() {
                final SignupState currentState = signupViewModel.getState();
                currentState.setRepeatPassword(new String(repeatPasswordInputField.getPassword()));
                signupViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });
    }

    private void handleSignup() {
        final SignupState currentState = signupViewModel.getState();
        final String username = currentState.getUsername();
        try {
            if (nameApiClient.validateName(username).get("score").getAsDouble() > 0) {
                JOptionPane.showMessageDialog(this,
                        "The username is invalid. Please try again.", "Validation Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (Exception exp) {
            JOptionPane.showMessageDialog(this, "Error validating username: " + exp.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        signupController.execute(currentState.getUsername(), currentState.getPassword(),
                currentState.getRepeatPassword());
    }

    private void handlePropertyChange(PropertyChangeEvent evt) {
        final SignupState state = (SignupState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public String getViewName() {
        return viewName;
    }

    public void setSignupController(SignupController controller) {
        this.signupController = controller;
    }
}
