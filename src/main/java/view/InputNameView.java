package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;

import com.google.gson.JsonObject;
import data_access.config.ConfigLoader;
import interface_adapter.input_name.InputNameController;
import interface_adapter.input_name.InputNameState;
import interface_adapter.input_name.InputNameViewModel;
import name_api.NameApiClient;

/**
 * The View for the Input Name Use Case.
 */
public class InputNameView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "inputName";
    private final InputNameViewModel viewModel;
    private InputNameController controller;

    private final JTextField nameInputField;
    private final JButton submitButton;

    // Add the NameApiClient
    private final NameApiClient nameApiClient;

    public InputNameView(InputNameViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.addPropertyChangeListener(this);

        final String apiKey = ConfigLoader.getProperty("nameapi.key");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new RuntimeException("API key for NameAPI is not configured. Please check config.properties.");
        }

        this.nameApiClient = new NameApiClient(apiKey);

        // Set layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Title Label
        JLabel titleLabel = new JLabel(InputNameViewModel.TITLE_LABEL);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Name input
        JLabel nameLabel = new JLabel("Character Name:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameInputField = new JTextField(20);
        nameInputField.setMaximumSize(new Dimension(200, 25));
        nameInputField.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Submit button
        submitButton = new JButton(InputNameViewModel.SUBMIT_BUTTON_LABEL);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(this);

        // Add components to the panel
        this.add(Box.createVerticalGlue());
        this.add(titleLabel);
        this.add(Box.createVerticalStrut(20)); // Space between components
        this.add(nameLabel);
        this.add(Box.createVerticalStrut(10)); // Space between label and input
        this.add(nameInputField);
        this.add(Box.createVerticalStrut(20)); // Space between input and button
        this.add(submitButton);
        this.add(Box.createVerticalGlue());

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(false); // Assuming default is light mode
        }
        catch (Exception exp) {
            exp.printStackTrace();
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

    public void setController(InputNameController controller) {
        this.controller = controller;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final InputNameState state = (InputNameState) evt.getNewValue();
        updateTheme(state.isDarkMode());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submitButton)) {
            String characterName = nameInputField.getText();

            // Call the Name API to validate the character name
            try {
                JsonObject response = nameApiClient.validateName(characterName);
                double riskScore = response.get("score").getAsDouble(); // Access the top-level "score"

                if (riskScore > 0) {
                    JOptionPane.showMessageDialog(
                            this,
                            "That's an invalid name. Please try again.",
                            "Invalid Name",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
                else {
                    // Proceed if the name is valid
                    InputNameState state = viewModel.getState();
                    controller.inputCharacterName(state.getUsername(), characterName);
                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "That's an invalid name. Please try again.",
                        "Invalid Name",
                        JOptionPane.WARNING_MESSAGE
                );
                ex.printStackTrace();
            }
        }
    }

    public String getViewName() {
        return viewName;
    }
}
