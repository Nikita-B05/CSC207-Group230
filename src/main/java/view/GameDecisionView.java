package view;

import entity.Decision;
import interface_adapter.game_decision.GameDecisionController;
import interface_adapter.game_decision.GameDecisionViewModel;
import interface_adapter.game_decision.GameDecisionState;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameDecisionView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "game decision";
    private final GameDecisionViewModel gameDecisionViewModel;
    private GameDecisionController gameDecisionController;

    private JLabel questionLabel;
    private JLabel avatarLabel;
    private JLabel netWorthLabel;
    private JLabel happinessLabel;
    private JLabel salaryLabel;
    private JButton[] decisionButtons;
    private JButton confirmButton;
    private JButton cancelButton;

    private int selectedDecisionIndex = -1;

    public GameDecisionView(GameDecisionViewModel gameDecisionViewModel) {
        this.gameDecisionViewModel = gameDecisionViewModel;
        this.gameDecisionViewModel.addPropertyChangeListener(this);

        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        avatarLabel = new JLabel("User Avatar Img or Abstract");
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avatarLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        netWorthLabel = new JLabel("Net Worth: $happiness is true wealth");
        netWorthLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        happinessLabel = new JLabel("Happiness: 0");
        happinessLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        salaryLabel = new JLabel("Salary: $0.00");
        salaryLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        decisionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            decisionButtons[i] = new JButton("Option " + (i + 1));
            decisionButtons[i].addActionListener(this);
            decisionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            decisionButtons[i].setPreferredSize(new Dimension(150, 50)); // Make the buttons vertically taller
        }

        confirmButton = new JButton("Submit");
        confirmButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(20, 20));

        JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        add(questionPanel, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control over positioning
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some space between components

        // Avatar panel
        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BorderLayout());
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avatarPanel.add(avatarLabel, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0; // Place avatar at (0, 0)
        bottomPanel.add(avatarPanel, gbc);

        // Stats panel (aligned vertically with avatar)
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS)); // Stack vertically
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the stats horizontally

        netWorthLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centered text
        statsPanel.add(netWorthLabel);

        happinessLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centered text
        statsPanel.add(happinessLabel);

        salaryLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centered text
        statsPanel.add(salaryLabel);

        gbc.gridx = 1; // Place stats panel next to avatar (at (1, 0))
        bottomPanel.add(statsPanel, gbc);

        add(bottomPanel, BorderLayout.CENTER);

        JPanel decisionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        for (JButton button : decisionButtons) {
            decisionsPanel.add(button);
        }

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        actionPanel.add(cancelButton);
        actionPanel.add(confirmButton);

        JPanel finalPanel = new JPanel(new BorderLayout(10, 10));
        finalPanel.add(decisionsPanel, BorderLayout.CENTER);
        finalPanel.add(actionPanel, BorderLayout.SOUTH);

        add(finalPanel, BorderLayout.SOUTH);
    }

    public void setController(GameDecisionController gameDecisionController) {
        this.gameDecisionController = gameDecisionController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameDecisionState state = gameDecisionViewModel.getState();

        // Remove highlighting from previously selected button
        if (selectedDecisionIndex != -1) {
            decisionButtons[selectedDecisionIndex].setBackground(null); // Reset background color
        }

        // Check for selected decision
        for (int i = 0; i < decisionButtons.length; i++) {
            if (e.getSource() == decisionButtons[i]) {
                // Highlight the selected button by changing its background to yellow
                decisionButtons[i].setBackground(Color.YELLOW);
                selectedDecisionIndex = i; // Store the index of the selected button

                if (i < state.getQuestion().getDecisions().size()) {
                    Decision decision = state.getQuestion().getDecisions().get(i);
                    state.setDecisionPicked(decision);
                    JOptionPane.showMessageDialog(this, decision.getDecisionText(), "Decision Details", JOptionPane.INFORMATION_MESSAGE);
                }
                return;
            }
        }

        if (e.getSource() == confirmButton && selectedDecisionIndex != -1) {
            gameDecisionController.pickDecision(
                    state.getUsername(),
                    state.getAge(),
                    state.getCharacterName(),
                    state.isDarkModeEnabled(),
                    state.getQuestion(),
                    state.getAssets(),
                    state.getAvatar(),
                    state.getDecisionPicked(),
                    state.getHappiness(),
                    state.getSalary()
            );
        }

        if (e.getSource() == cancelButton) {
            gameDecisionController.switchToGameOver(
                    state.getUsername(),
                    state.getAge(),
                    state.getCharacterName(),
                    state.isDarkModeEnabled(),
                    state.getQuestion(),
                    state.getAssets(),
                    state.getAvatar(),
                    state.getDecisionPicked(),
                    state.getHappiness(),
                    state.getSalary()
            );
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GameDecisionState state = gameDecisionViewModel.getState();

        questionLabel.setText(state.getQuestion() != null ? state.getQuestion().getQuestionText() : "");

        if (state.getAvatar() != null && state.getAvatar().getIcon() != null) {
            avatarLabel.setIcon(state.getAvatar().getIcon());
            avatarLabel.setText("");
        } else {
            avatarLabel.setIcon(null);
            avatarLabel.setText("No Avatar Available");
        }

        netWorthLabel.setText("Net Worth: $" + "happiness is true wealth");
        happinessLabel.setText(String.format("Happiness: %d", state.getHappiness()));
        salaryLabel.setText(String.format("Salary: $%.2f", state.getSalary()));

        // Update decision buttons
        List<Decision> decisions = state.getQuestion() != null ? state.getQuestion().getDecisions() : List.of();
        for (int i = 0; i < decisionButtons.length; i++) {
            if (i < decisions.size()) {
                decisionButtons[i].setEnabled(true);
                decisionButtons[i].setText("Option " + (i + 1));
                decisionButtons[i].setBackground(null); // Reset background color when decisions are updated
            } else {
                decisionButtons[i].setEnabled(false);
                decisionButtons[i].setText("");
            }
        }

        // Apply light or dark mode
        if (state.isDarkModeEnabled()) {
            ColorTheme.applyDarkMode(this);
        } else {
            ColorTheme.applyLightMode(this);
        }

        // Display error if any
        if (state.getDecisionError() != null) {
            JOptionPane.showMessageDialog(this, state.getDecisionError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getViewName() {
        return viewName;
    }
}
