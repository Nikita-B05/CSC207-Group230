package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.*;

import entity.Decision;
import interface_adapter.game_decision.GameDecisionController;
import interface_adapter.game_decision.GameDecisionState;
import interface_adapter.game_decision.GameDecisionViewModel;

/**
 * The view for making game decisions in the application.
 * Displays a question with options and allows the user to make decisions.
 *
 * <p>Some methods and parameters may accept or return <code>null</code> values.</p>
 */
public class GameDecisionView extends JPanel implements ActionListener, PropertyChangeListener {

    public static final int GREEN = 110;
    public static final int BLUE = 250;
    private static final String FONT_NAME = "Arial";
    private static final int FONT_SIZE_20 = 20;
    private static final int FONT_SIZE_16 = 16;
    private static final int FONT_SIZE_14 = 14;
    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 50;
    private static final int GRID_GAP_X = 10;
    private static final int GRID_GAP_Y = 10;
    private static final int GRID_ROWS = 2;
    private static final int GRID_COLS = 2;
    private static final int PREFERRED_WIDTH = 10;
    private static final int PREFERRED_HEIGHT = 30;
    private static final int SPACER_PANEL_HEIGHT = 30;
    private static final int DECISION_BUTTON_OPTIONS = 4;
    private static final int EMPTY_BORDER = 10;
    private static final int AGE_OUT_AGE = 28;
    private static final int AGE_IN_AGE = 22;
    private static final int RED = 80;

    private final String viewName = "game decision";
    private final GameDecisionViewModel gameDecisionViewModel;
    private GameDecisionController gameDecisionController;

    private JLabel questionLabel;
    private JLabel avatarLabel;
    private JLabel statsTitleLabel;
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
        questionLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_20));
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);

        avatarLabel = new JLabel("User Avatar Img or Abstract");
        avatarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        avatarLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_14));

        statsTitleLabel = new JLabel();
        statsTitleLabel.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_16));
        statsTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        netWorthLabel = new JLabel("Net Worth: $happiness is true wealth");
        netWorthLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_14));

        happinessLabel = new JLabel("Happiness: 0");
        happinessLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_14));

        salaryLabel = new JLabel("Salary: $0.00");
        salaryLabel.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_14));

        decisionButtons = new JButton[DECISION_BUTTON_OPTIONS];
        for (int i = 0; i < DECISION_BUTTON_OPTIONS; i++) {
            decisionButtons[i] = new JButton("Option " + (i + 1));
            decisionButtons[i].addActionListener(this);
            decisionButtons[i].setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_14));
            decisionButtons[i].setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        }

        confirmButton = new JButton("Submit");
        confirmButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
    }

    private void setupLayout() {
        setLayout(new BorderLayout(GRID_GAP_X, GRID_GAP_Y));

        final JPanel spacerPanel = new JPanel();
        spacerPanel.setPreferredSize(new Dimension(PREFERRED_WIDTH, SPACER_PANEL_HEIGHT));
        add(spacerPanel, BorderLayout.NORTH);

        final JPanel questionPanel = new JPanel(new BorderLayout());
        questionPanel.setBorder(BorderFactory.createEmptyBorder(EMPTY_BORDER, EMPTY_BORDER,
                EMPTY_BORDER, EMPTY_BORDER));
        questionPanel.add(questionLabel, BorderLayout.CENTER);
        add(questionPanel, BorderLayout.NORTH);

        // Bottom Panel for avatar and stats
        final JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(GRID_GAP_X, GRID_GAP_X, GRID_GAP_X, GRID_GAP_X);

        // Avatar Panel
        final JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new BorderLayout());
        avatarPanel.add(avatarLabel, BorderLayout.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        bottomPanel.add(avatarPanel, gbc);

        // Stats Panel
        final JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statsPanel.add(statsTitleLabel);
        statsPanel.add(Box.createRigidArea(new Dimension(0, PREFERRED_WIDTH)));
        statsPanel.add(netWorthLabel);
        statsPanel.add(happinessLabel);
        statsPanel.add(salaryLabel);

        gbc.gridx = 1;
        bottomPanel.add(statsPanel, gbc);
        add(bottomPanel, BorderLayout.CENTER);

        // Decisions Panel
        final JPanel decisionsPanel = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS, GRID_GAP_X, GRID_GAP_Y));
        for (JButton button : decisionButtons) {
            decisionsPanel.add(button);
        }

        // Action Panel for confirm and cancel buttons
        final JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, GRID_GAP_X, GRID_GAP_Y));
        actionPanel.add(cancelButton);
        actionPanel.add(confirmButton);

        // Final Panel to hold everything together
        final JPanel finalPanel = new JPanel(new BorderLayout(GRID_GAP_X, GRID_GAP_Y));
        finalPanel.add(decisionsPanel, BorderLayout.CENTER);
        finalPanel.add(actionPanel, BorderLayout.SOUTH);

        add(finalPanel, BorderLayout.SOUTH);
    }

    public void setController(GameDecisionController gameDecisionController) {
        this.gameDecisionController = gameDecisionController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final GameDecisionState state = gameDecisionViewModel.getState();

        // Remove highlighting from previously selected button
        if (selectedDecisionIndex != -1) {
            decisionButtons[selectedDecisionIndex].setBackground(null);
        }

        // Check for selected decision
        for (int i = 0; i < decisionButtons.length; i++) {
            if (e.getSource() == decisionButtons[i]) {
                // Highlight the selected button by changing its background to dark blue
                decisionButtons[i].setBackground(new Color(RED, GREEN, BLUE));
                selectedDecisionIndex = i;

                if (i < state.getQuestion().getDecisions().size()) {
                    final Decision decision = state.getQuestion().getDecisions().get(i);
                    state.setDecisionPicked(decision);
                }
            }
        }

        // Handle Confirm and Cancel actions
        if (e.getSource() == confirmButton && selectedDecisionIndex != -1) {
            handleConfirmAction(state);
        }

        if (e.getSource() == cancelButton) {
            handleCancelAction(state);
        }
    }

    private void handleConfirmAction(GameDecisionState state) {
        final int age = state.getAge();
        final int happiness = state.getHappiness();
        final double netWorth = state.getAssets().getTotal(state.getStockPrices());

        if (age >= AGE_IN_AGE && (happiness <= 0 || netWorth <= 0)) {
            gameDecisionController.switchToGameOver(
                    state.getUsername(),
                    age,
                    state.getCharacterName(),
                    state.isDarkModeEnabled(),
                    state.getQuestion(),
                    state.getAssets(),
                    state.getAvatar(),
                    state.getDecisionPicked(),
                    happiness,
                    state.getSalary()
            );
        }

        if (age >= AGE_OUT_AGE) {
            gameDecisionController.switchToGameSuccess(
                    state.getUsername(),
                    age,
                    state.getCharacterName(),
                    state.isDarkModeEnabled(),
                    state.getQuestion(),
                    state.getAssets(),
                    state.getAvatar(),
                    state.getDecisionPicked(),
                    happiness,
                    state.getSalary()
            );
        }

        gameDecisionController.pickDecision(
                state.getUsername(),
                age,
                state.getCharacterName(),
                state.isDarkModeEnabled(),
                state.getQuestion(),
                state.getAssets(),
                state.getAvatar(),
                state.getDecisionPicked(),
                happiness,
                state.getSalary()
        );
        gameDecisionController.switchToAssetsManager(
                state.getUsername(),
                age,
                state.getCharacterName(),
                state.isDarkModeEnabled(),
                state.getQuestion(),
                state.getAssets(),
                state.getAvatar(),
                state.getDecisionPicked(),
                happiness,
                state.getSalary()
        );
    }

    private void handleCancelAction(GameDecisionState state) {
        gameDecisionController.switchToHomepage(
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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final GameDecisionState state = gameDecisionViewModel.getState();

        if (state.getQuestion() != null) {
            questionLabel.setText(state.getQuestion().getQuestionText());
        }
        else {
            questionLabel.setText("");
        }

        if (state.getAvatar() != null && state.getAvatar().getIcon() != null) {
            avatarLabel.setIcon(state.getAvatar().getIcon());
            avatarLabel.setText("");
        }
        else {
            avatarLabel.setIcon(null);
            avatarLabel.setText("No Avatar Available");
        }

        statsTitleLabel.setText(state.getCharacterName() + "'s Stats");
        netWorthLabel.setText("Net Worth: $happiness is true wealth");
        happinessLabel.setText(String.format("Happiness: %d", state.getHappiness()));
        salaryLabel.setText(String.format("Salary: $%.2f", state.getSalary()));

        final List<Decision> decisions;
        if (state.getQuestion() != null) {
            decisions = state.getQuestion().getDecisions();
        }
        else {
            decisions = List.of();
        }
        for (int i = 0; i < decisionButtons.length; i++) {
            if (i < decisions.size()) {
                decisionButtons[i].setEnabled(true);
                decisionButtons[i].setText(decisions.get(i).getDecisionText());
                decisionButtons[i].setBackground(null);
            }
            else {
                decisionButtons[i].setEnabled(false);
                decisionButtons[i].setText("");
            }
        }

        if (state.isDarkModeEnabled()) {
            ColorTheme.applyDarkMode(this);
        }
        else {
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
