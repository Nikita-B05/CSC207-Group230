package view;

import entity.Decision;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import interface_adapter.decision_log.DecisionLogController;
import interface_adapter.decision_log.DecisionLogViewModel;
import interface_adapter.dark_mode.DarkModeController;
import use_case.decision_log.DecisionLogInputBoundary;

public class DecisionLogView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "decisionLog";
    private DecisionLogController decisionLogController;
    private final DecisionLogViewModel decisionLogViewModel;
    private final JCheckBox darkModeCheckBox;

    private JTable historyTable;
    private JScrollPane scrollPane;

    private final JLabel statsValues;
    private final JButton backButton;

    public DecisionLogView(DecisionLogViewModel decisionLogViewModel,
                           DecisionLogController decisionLogController,
                           DarkModeController darkModeController,
                           JCheckBox darkModeCheckBox) {
        this.decisionLogViewModel = decisionLogViewModel;
        this.decisionLogController = decisionLogController;
        this.darkModeCheckBox = darkModeCheckBox;

        // Register property change listener
        this.decisionLogViewModel.addPropertyChangeListener(this);

        setLayout(new BorderLayout());

        // Top Panel for Title
        JLabel titleLabel = new JLabel("Decision Log", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0)); // Add spacing
        add(titleLabel, BorderLayout.NORTH);

        // Columns for the decision log table
        String[] columns = {"Time", "Decision", "Your Answer", "Net Worth Change", "Happiness Change"};

        // Prepare data for the table
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        updateTableModel(model, decisionLogViewModel.getDecisions());

        // Create the decision log table
        historyTable = new JTable(model);
        scrollPane = new JScrollPane(historyTable);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom Panel for Stats and Button
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

        // Stats section
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel statsHeader = new JLabel("Current Stats:");
        statsHeader.setFont(new Font("Arial", Font.BOLD, 14));
        statsHeader.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsValues = new JLabel(getStatsText());
        statsValues.setFont(new Font("Arial", Font.PLAIN, 14));
        statsValues.setAlignmentX(Component.CENTER_ALIGNMENT);

        statsPanel.add(statsHeader);
        statsPanel.add(Box.createVerticalStrut(5)); // Add spacing between header and values
        statsPanel.add(statsValues);

        // Button section
        backButton = new JButton("Return to Homepage");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(backButton.getMinimumSize()); // Adjust button size to fit text
        backButton.addActionListener(this); // Add action listener

        // Add stats and button to the bottom panel
        bottomPanel.add(statsPanel);
        bottomPanel.add(Box.createVerticalStrut(10)); // Spacing between stats and button
        bottomPanel.add(backButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void updateTheme(boolean isDarkMode) {
        darkModeCheckBox.setSelected(isDarkMode);

        if (isDarkMode) {
            ColorTheme.applyDarkMode(this);
        } else {
            ColorTheme.applyLightMode(this);
        }

        SwingUtilities.updateComponentTreeUI(this);
    }

    private void updateTableModel(DefaultTableModel model, List<Decision> decisions) {
        model.setRowCount(0); // Clear existing rows
        for (Decision decision : decisions) {
            Object[] row = {decision.getTimestamp(), decision.getDecisionText(), decision.getDecisionResponse(),
                    decision.getNetWorthChange(), decision.getHappinessChange()};
            model.addRow(row);
        }
    }

    private String getStatsText() {
        return String.format("Net Worth: %.2f, Happiness: %.2f",
                decisionLogViewModel.getTotalNetWorthChange(),
                decisionLogViewModel.getTotalHappinessChange());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(backButton)) {
            String username = decisionLogViewModel.getUsername();
            decisionLogController.switchToHomepageView(username);
        }
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateTableModel((DefaultTableModel) historyTable.getModel(), decisionLogViewModel.getDecisions());
        statsValues.setText(getStatsText());
        updateTheme(decisionLogViewModel.isDarkModeEnabled());
    }

    public static void main(String[] args) {
        // Create sample decisions for testing
        List<Decision> decisions = new ArrayList<>();
        decisions.add(new Decision(LocalDateTime.now(), "Invest in stocks", "Yes", 500, 20));
        decisions.add(new Decision(LocalDateTime.now(), "Buy a car", "No", -10000, -5));
        decisions.add(new Decision(LocalDateTime.now(), "Start a business", "Yes", 20000, 50));

        // Create a DecisionLogViewModel and populate it with sample data
        DecisionLogViewModel decisionLogViewModel = new DecisionLogViewModel();
        decisionLogViewModel.setDecisions(decisions);
        decisionLogViewModel.setDarkModeEnabled(false); // Default to light mode

        // Create dummy controllers
        DecisionLogController decisionLogController = new DecisionLogController(null, null);
        DarkModeController darkModeController = new DarkModeController(null);

        // Create a JCheckBox for dark mode
        JCheckBox darkModeCheckBox = new JCheckBox("Dark Mode");

        // Create the DecisionLogView
        DecisionLogView decisionLogView = new DecisionLogView(decisionLogViewModel, decisionLogController, darkModeController, darkModeCheckBox);

        // Set up the JFrame to display the view
        JFrame frame = new JFrame("Decision Log Preview");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(decisionLogView);
        frame.setSize(800, 600); // Set the window size
        frame.setVisible(true); // Make the window visible
    }

    public void DecisionLogController(DecisionLogController decisionLogController) {
        this.decisionLogController = decisionLogController;
    }
}
