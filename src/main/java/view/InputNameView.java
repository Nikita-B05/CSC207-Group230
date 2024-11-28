package view;

import interface_adapter.input_name.InputNameController;
import interface_adapter.input_name.InputNameState;
import interface_adapter.input_name.InputNameViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The View for the Input Name Use Case.
 */
public class InputNameView extends JPanel implements ActionListener {

    private final String viewName = "inputName";
    private final InputNameViewModel viewModel;
    private InputNameController controller;

    private final JTextField nameInputField;
    private final JButton submitButton;

    public InputNameView(InputNameViewModel viewModel) {
        this.viewModel = viewModel;

        JLabel titleLabel = new JLabel(InputNameViewModel.TITLE_LABEL);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Name input
        JLabel nameLabel = new JLabel("Character Name:");
        nameInputField = new JTextField(15);

        // Submit button
        submitButton = new JButton(InputNameViewModel.SUBMIT_BUTTON_LABEL);
        submitButton.addActionListener(this);

        // Layout setup
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        this.add(titleLabel);
        this.add(Box.createVerticalStrut(20));
        this.add(nameLabel);
        this.add(nameInputField);
        this.add(Box.createVerticalStrut(20));
        this.add(submitButton);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            updateTheme(false); // Assuming default is light mode
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

    public void setController(InputNameController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(submitButton)) {
            String characterName = nameInputField.getText();

            InputNameState state = viewModel.getState();
            controller.inputCharacterName(state.getUsername(), characterName);
        }
    }

    public String getViewName() {
        return viewName;
    }
}