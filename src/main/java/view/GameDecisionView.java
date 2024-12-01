package view;

import entity.Decision;
import interface_adapter.game_decision.GameDecisionController;
import interface_adapter.game_decision.GameDecisionViewModel;
import interface_adapter.game_decision.GameDecisionState;
import interface_adapter.dark_mode.DarkModeController;

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
    private DarkModeController darkModeController;

    // UI Components
    private JLabel ageLabel;
    private JLabel questionLabel;
    private JButton[] decisionButtons;
    private JButton submitButton;
    private JButton endGameButton;

    // State tracking
    private int selectedDecisionIndex = -1;

    public GameDecisionView(GameDecisionViewModel gameDecisionViewModel) {
        this.gameDecisionViewModel = gameDecisionViewModel;
        this.gameDecisionViewModel.addPropertyChangeListener(this);

        initializeComponents();
        setupLayout();
    }

    private void initializeComponents() {
        // Age Label
        ageLabel = new JLabel();
        ageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        ageLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Question Label
        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Decision Buttons
        decisionButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            decisionButtons[i] = new JButton();
            decisionButtons[i].addActionListener(this);
            decisionButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
        }

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);

        // End Game Button
        endGameButton = new JButton("End Game");
        endGameButton.addActionListener(this);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(10, 10));

        // Age at top right
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(ageLabel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Question in center
        add(questionLabel, BorderLayout.CENTER);

        // Decision Buttons in a grid
        JPanel decisionsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        for (JButton button : decisionButtons) {
            decisionsPanel.add(button);
        }
        add(decisionsPanel, BorderLayout.WEST);

        // Bottom panel with Submit and End Game buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(submitButton);
        bottomPanel.add(endGameButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void setController(GameDecisionController gameDecisionController) {
        this.gameDecisionController = gameDecisionController;
    }

    public void setDarkModeController(DarkModeController darkModeController) {
        this.darkModeController = darkModeController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameDecisionController == null) return;

        // Decision Button Selection
        for (int i = 0; i < decisionButtons.length; i++) {
            if (e.getSource() == decisionButtons[i]) {
                // Deselect previous button
                if (selectedDecisionIndex != -1) {
                    decisionButtons[selectedDecisionIndex].setBackground(null);
                }

                // Select current button
                decisionButtons[i].setBackground(Color.LIGHT_GRAY);
                selectedDecisionIndex = i;
                break;
            }
        }

        // Submit Button
        if (e.getSource() == submitButton && selectedDecisionIndex != -1) {
            GameDecisionState state = gameDecisionViewModel.getState();
            gameDecisionController.pickDecision(
                    state.getUsername(),
                    state.getAge(),
                    state.getCharacterName(),
                    state.isDarkModeEnabled(),
                    state.getQuestion(),
                    state.getAssets(),
                    null  // Avatar parameter needs to be handled
            );
        }

        // End Game Button
        if (e.getSource() == endGameButton) {
            GameDecisionState state = gameDecisionViewModel.getState();
            gameDecisionController.switchToGameOver(
                    state.getUsername(),
                    state.getAge(),
                    state.getCharacterName(),
                    state.isDarkModeEnabled(),
                    state.getQuestion(),
                    state.getAssets(),
                    state.getAvatar()
            );
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GameDecisionState state = gameDecisionViewModel.getState();

        ageLabel.setText("Age: " + state.getAge());

        if (state.getQuestion() != null) {
            questionLabel.setText(state.getQuestion().getQuestionText());
        }

        if (state.getQuestion() != null) {
            List<Decision> decisions = state.getQuestion().getDecisions();
            for (int i = 0; i < decisions.size() && i < decisionButtons.length; i++) {
                decisionButtons[i].setText(decisions.get(i).getDecisionText());
            }
        }

        if (state.isDarkModeEnabled()) {
            ColorTheme.applyDarkMode(this);
        } else {
            ColorTheme.applyLightMode(this);
        }

        if (state.getDecisionError() != null) {
            JOptionPane.showMessageDialog(this, state.getDecisionError(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String getViewName() {
        return viewName;
    }
}